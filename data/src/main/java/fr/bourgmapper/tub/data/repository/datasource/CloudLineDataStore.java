package fr.bourgmapper.tub.data.repository.datasource;

import java.util.List;

import fr.bourgmapper.tub.data.cache.LineCache;
import fr.bourgmapper.tub.data.entity.LineEntity;
import fr.bourgmapper.tub.data.net.RestApi;
import io.reactivex.Observable;

/**
 * {@link LineDataStore} implementation based on connections to the api (Cloud).
 */
class CloudLineDataStore implements LineDataStore {

    private final RestApi restApi;
    private final LineCache lineCache;

    /**
     * Construct a {@link LineDataStore} based on connections to the api (Cloud).
     *
     * @param restApi   The {@link RestApi} implementation to use.
     * @param lineCache A {@link LineCache} to cache data retrieved from the api.
     */
    CloudLineDataStore(RestApi restApi, LineCache lineCache) {
        this.restApi = restApi;
        this.lineCache = lineCache;
    }

    @Override
    public Observable<List<LineEntity>> lineEntityList() {
        //TODO : Enable LineEntity List cache after fixing DBFlow
        //return this.restApi.lineEntityList().doOnNext(CloudLineDataStore.this.lineCache::put);
        return this.restApi.lineEntityList();
    }

    @Override
    public Observable<LineEntity> lineEntityDetails(final String lineId) {
        //TODO : Enable LineEntity cache after fixing DBFlow
        //return this.restApi.lineEntityById(lineId).doOnNext(CloudLineDataStore.this.lineCache::put);
        return this.restApi.lineEntityById(lineId);
    }
}
