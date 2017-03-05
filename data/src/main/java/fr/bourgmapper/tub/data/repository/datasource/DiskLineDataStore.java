package fr.bourgmapper.tub.data.repository.datasource;


import java.util.List;

import fr.bourgmapper.tub.data.cache.LineCache;
import fr.bourgmapper.tub.data.entity.LineEntity;
import io.reactivex.Observable;


/**
 * {@link LineDataStore} implementation based on file system data store.
 */
class DiskLineDataStore implements LineDataStore {

    private final LineCache lineCache;

    /**
     * Construct a {@link LineDataStore} based file system data store.
     *
     * @param lineCache A {@link LineCache} to cache data retrieved from the api.
     */
    DiskLineDataStore(LineCache lineCache) {
        this.lineCache = lineCache;
    }

    @Override
    public Observable<List<LineEntity>> lineEntityList() {
        //TODO: implement simple cache for storing/retrieving collections of lines.
        throw new UnsupportedOperationException("Operation is not available!!!");
    }

    @Override
    public Observable<LineEntity> lineEntityDetails(final long lineId) {
        return this.lineCache.get(lineId);
    }
}
