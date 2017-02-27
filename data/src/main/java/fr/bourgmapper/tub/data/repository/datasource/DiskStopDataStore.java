package fr.bourgmapper.tub.data.repository.datasource;

import java.util.List;

import fr.bourgmapper.tub.data.cache.StopCache;
import fr.bourgmapper.tub.data.entity.StopEntity;
import io.reactivex.Observable;


/**
 * {@link StopDataStore} implementation based on file system data store.
 */
class DiskStopDataStore implements StopDataStore {

    private final StopCache stopCache;

    /**
     * Construct a {@link StopDataStore} based file system data store.
     *
     * @param stopCache A {@link StopCache} to cache data retrieved from the api.
     */
    DiskStopDataStore(StopCache stopCache) {
        this.stopCache = stopCache;
    }

    @Override
    public Observable<List<StopEntity>> stopEntityList() {
        //TODO: implement simple cache for storing/retrieving collections of stops.
        throw new UnsupportedOperationException("Operation is not available!!!");
    }

    @Override
    public Observable<StopEntity> stopEntityDetails(final String stopId) {
        return this.stopCache.get(stopId);
    }
}
