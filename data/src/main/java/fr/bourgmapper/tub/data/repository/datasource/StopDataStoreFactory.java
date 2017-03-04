package fr.bourgmapper.tub.data.repository.datasource;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import fr.bourgmapper.tub.data.cache.StopCache;
import fr.bourgmapper.tub.data.net.RestApi;
import fr.bourgmapper.tub.data.net.RestApiImpl;

/**
 * Factory that creates different implementations of {@link StopDataStore}.
 */
@Singleton
public class StopDataStoreFactory {

    private final Context context;
    private final StopCache stopCache;

    @Inject
    StopDataStoreFactory(@NonNull Context context, @NonNull StopCache stopCache) {
        this.context = context.getApplicationContext();
        this.stopCache = stopCache;
    }

    /**
     * Create {@link StopDataStore} from a stop stopId.
     */
    public StopDataStore create(int stopId) {
        StopDataStore stopDataStore;

        if (!this.stopCache.isExpired() && this.stopCache.isCached(stopId)) {
            stopDataStore = new DiskStopDataStore(this.stopCache);
        } else {
            stopDataStore = createCloudDataStore();
        }

        return stopDataStore;
    }

    /**
     * Create {@link StopDataStore} to retrieve data from the Cloud.
     */
    public StopDataStore createCloudDataStore() {
        final RestApi restApi = new RestApiImpl();

        return new CloudStopDataStore(restApi, this.stopCache);
    }
}
