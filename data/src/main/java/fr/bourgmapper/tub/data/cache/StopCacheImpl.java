package fr.bourgmapper.tub.data.cache;

import android.content.Context;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;

import fr.bourgmapper.tub.data.cache.serializer.Serializer;
import fr.bourgmapper.tub.data.database.DatabaseEvictor;
import fr.bourgmapper.tub.data.database.DatabaseManager;
import fr.bourgmapper.tub.data.database.DatabaseWriter;
import fr.bourgmapper.tub.data.entity.StopEntity;
import fr.bourgmapper.tub.data.exception.StopNotFoundException;
import fr.bourgmapper.tub.data.file.FileManager;
import fr.bourgmapper.tub.domain.executor.ThreadExecutor;
import io.reactivex.Observable;


/**
 * {@link StopCache} implementation.
 */
@Singleton
public class StopCacheImpl implements StopCache {
    private static final String SETTINGS_FILE_NAME = "fr.bourgmapper.tub.presentation.SETTINGS";
    private static final String SETTINGS_KEY_LAST_CACHE_UPDATE_STOP = "last_cache_update_stop";
    private static final long EXPIRATION_TIME = 60 * 10 * 1000;
    private final Context context;
    private final FileManager fileManager;
    private final DatabaseManager databaseManager;
    private final ThreadExecutor threadExecutor;
    Class<?> ENTITY_CLASS = StopEntity.class;

    /**
     * Constructor of the class {@link StopCacheImpl}.
     *
     * @param context         A
     * @param serializer      {@link Serializer} for object serialization.
     * @param fileManager     {@link FileManager} for saving serialized objects to the file system.
     * @param databaseManager {@link DatabaseManager} for saving objects to database.
     */
    @Inject
    StopCacheImpl(Context context, Serializer serializer,
                  FileManager fileManager, DatabaseManager databaseManager, ThreadExecutor executor) {
        if (context == null || serializer == null || fileManager == null || databaseManager == null || executor == null) {
            throw new IllegalArgumentException("Invalid null parameter");
        }
        this.context = context.getApplicationContext();
        this.fileManager = fileManager;
        this.databaseManager = databaseManager;
        this.threadExecutor = executor;
    }

    @Override
    public Observable<StopEntity> get(final String stopId) {
        return Observable.create(emitter -> {
            final StopEntity stopEntity = this.databaseManager.getEntityById(StopEntity.class, stopId);

            if (stopEntity != null) {
                emitter.onNext(stopEntity);
                emitter.onComplete();
            } else {
                emitter.onError(new StopNotFoundException());
            }
        });
    }

    @Override
    public void put(StopEntity stopEntity) {
        if (stopEntity != null) {
            if (!isCached(stopEntity.stopId)) {
                this.executeAsynchronously(new DatabaseWriter(this.databaseManager, stopEntity));
                setLastCacheUpdateTimeMillis();
            }
        }
    }

    @Override
    public void put(Collection<StopEntity> stopEntityList) {
        if (stopEntityList != null) {
            for (StopEntity stopEntity : stopEntityList) {
                this.put(stopEntity);
            }
        }
    }

    @Override
    public boolean isCached(String stopId) {
        return this.databaseManager.entityExists(StopEntity.class, stopId);
    }

    @Override
    public boolean isExpired() {
        long currentTime = System.currentTimeMillis();
        long lastUpdateTime = this.getLastCacheUpdateTimeMillis();

        boolean expired = ((currentTime - lastUpdateTime) > EXPIRATION_TIME);

        if (expired) {
            this.evictAll();
        }

        return expired;
    }

    @Override
    public void evictAll() {
        this.executeAsynchronously(new DatabaseEvictor(databaseManager, StopEntity.class));
    }

    /**
     * Set in millis, the last time the cache was accessed.
     */
    private void setLastCacheUpdateTimeMillis() {
        final long currentMillis = System.currentTimeMillis();
        this.fileManager.writeToPreferences(this.context, SETTINGS_FILE_NAME,
                SETTINGS_KEY_LAST_CACHE_UPDATE_STOP, currentMillis);
    }

    /**
     * Get in millis, the last time the cache was accessed.
     */
    private long getLastCacheUpdateTimeMillis() {
        return this.fileManager.getFromPreferences(this.context, SETTINGS_FILE_NAME,
                SETTINGS_KEY_LAST_CACHE_UPDATE_STOP);
    }

    /**
     * Executes a {@link Runnable} in another Thread.
     *
     * @param runnable {@link Runnable} to execute
     */
    private void executeAsynchronously(Runnable runnable) {
        this.threadExecutor.execute(runnable);
    }
}
