package fr.bourgmapper.tub.data.repository.datasource;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import fr.bourgmapper.tub.data.cache.LineCache;
import fr.bourgmapper.tub.data.net.RestApi;
import fr.bourgmapper.tub.data.net.RestApiImpl;

/**
 * Factory that creates different implementations of {@link LineDataStore}.
 */
@Singleton
public class LineDataStoreFactory {

    private final Context context;
    private final LineCache lineCache;

    @Inject
    LineDataStoreFactory(@NonNull Context context, @NonNull LineCache lineCache) {
        this.context = context.getApplicationContext();
        this.lineCache = lineCache;
    }

    /**
     * Create {@link LineDataStore} from a line stopId.
     */
    public LineDataStore create(int lineId) {
        LineDataStore lineDataStore;

        if (!this.lineCache.isExpired() && this.lineCache.isCached(lineId)) {
            lineDataStore = new DiskLineDataStore(this.lineCache);
        } else {
            lineDataStore = createCloudDataStore();
        }

        return lineDataStore;
    }

    /**
     * Create {@link LineDataStore} to retrieve data from the Cloud.
     */
    public LineDataStore createCloudDataStore() {
        final RestApi restApi = new RestApiImpl();

        return new CloudLineDataStore(restApi, this.lineCache);
    }
}
