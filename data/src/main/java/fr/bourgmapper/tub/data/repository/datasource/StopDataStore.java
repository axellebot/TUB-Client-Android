package fr.bourgmapper.tub.data.repository.datasource;

import java.util.List;

import fr.bourgmapper.tub.data.entity.StopEntity;
import io.reactivex.Observable;


/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface StopDataStore {
    /**
     * Get an {@link Observable} which will emit a List of {@link StopEntity}.
     */
    Observable<List<StopEntity>> stopEntityList();

    /**
     * Get an {@link Observable} which will emit a {@link StopEntity} by its stopId.
     *
     * @param stopId The stopId to retrieve stop data.
     */
    Observable<StopEntity> stopEntityDetails(final long stopId);
}
