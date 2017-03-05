package fr.bourgmapper.tub.data.repository.datasource;

import java.util.List;

import fr.bourgmapper.tub.data.cache.StopCache;
import fr.bourgmapper.tub.data.entity.StopEntity;
import fr.bourgmapper.tub.data.net.RestApi;
import io.reactivex.Observable;

/**
 * {@link StopDataStore} implementation based on connections to the api (Cloud).
 */
class CloudStopDataStore implements StopDataStore {

    private final RestApi restApi;
    private final StopCache stopCache;

    /**
     * Construct a {@link StopDataStore} based on connections to the api (Cloud).
     *
     * @param restApi   The {@link RestApi} implementation to use.
     * @param stopCache A {@link StopCache} to cache data retrieved from the api.
     */
    CloudStopDataStore(RestApi restApi, StopCache stopCache) {
        this.restApi = restApi;
        this.stopCache = stopCache;
    }

    @Override
    public Observable<List<StopEntity>> stopEntityList() {
        //return this.restApi.stopEntityList();
        return this.restApi.stopEntityList().doOnNext(CloudStopDataStore.this.stopCache::put);
    }

    @Override
    public Observable<StopEntity> stopEntityDetails(final long stopId) {
        //return this.restApi.stopEntityById(stopId);
        return this.restApi.stopEntityById(stopId).doOnNext(CloudStopDataStore.this.stopCache::put);
    }
}
