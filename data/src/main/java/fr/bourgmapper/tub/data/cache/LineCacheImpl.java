package fr.bourgmapper.tub.data.cache;

import android.content.Context;

import fr.bourgmapper.tub.data.cache.serializer.Serializer;
import fr.bourgmapper.tub.data.database.DataBaseWriter;
import fr.bourgmapper.tub.data.database.DatabaseEvictor;
import fr.bourgmapper.tub.data.entity.LineEntity;
import fr.bourgmapper.tub.data.exception.LineNotFoundException;
import fr.bourgmapper.tub.domain.executor.ThreadExecutor;
import io.reactivex.Observable;


/**
 * {@link LineCache} implementation.
 */
public class LineCacheImpl implements LineCache {

    private static final String SETTINGS_FILE_NAME = "fr.bourgmapper.tub.SETTINGS";
    private static final String SETTINGS_KEY_LAST_CACHE_UPDATE_LINE = "last_cache_update_line";

    private static final long EXPIRATION_TIME = 60 * 10 * 1000;

    private final Context context;
    private final FileManager fileManager;
    private final DatabaseManager databaseManager;
    private final ThreadExecutor threadExecutor;

    /**
     * Constructor of the class {@link LineCacheImpl}.
     *
     * @param context         A
     * @param serializer      {@link Serializer} for object serialization.
     * @param fileManager     {@link FileManager} for saving serialized objects to the file system.
     * @param databaseManager {@link DatabaseManager} for saving objects to database.
     */
    public LineCacheImpl(Context context, Serializer serializer,
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
    public Observable<LineEntity> get(final String lineId) {
        return Observable.create(emitter -> {
            final LineEntity lineEntity = this.databaseManager.getEntityById(lineId, LineEntity.class);

            if (lineEntity != null) {
                emitter.onNext(lineEntity);
                emitter.onComplete();
            } else {
                emitter.onError(new LineNotFoundException());
            }
        });
    }

    @Override
    public void put(LineEntity lineEntity) {
        if (lineEntity != null) {
            if (!isCached(lineEntity.id)) {
                this.executeAsynchronously(new DataBaseWriter(this.databaseManager, lineEntity));
                setLastCacheUpdateTimeMillis();
            }
        }
    }

    @Override
    public boolean isCached(String lineId) {
        return this.databaseManager.exists(lineId, LineEntity.class);
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
        this.executeAsynchronously(new DatabaseEvictor(databaseManager));
    }

    /**
     * Set in millis, the last time the cache was accessed.
     */
    private void setLastCacheUpdateTimeMillis() {
        final long currentMillis = System.currentTimeMillis();
        this.fileManager.writeToPreferences(this.context, SETTINGS_FILE_NAME,
                SETTINGS_KEY_LAST_CACHE_UPDATE_LINE, currentMillis);
    }

    /**
     * Get in millis, the last time the cache was accessed.
     */
    private long getLastCacheUpdateTimeMillis() {
        return this.fileManager.getFromPreferences(this.context, SETTINGS_FILE_NAME,
                SETTINGS_KEY_LAST_CACHE_UPDATE_LINE);
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
