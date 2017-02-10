package fr.bourgmapper.tub.data.repository.datasource;

import java.util.List;

import fr.bourgmapper.tub.data.entity.LineEntity;
import io.reactivex.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface LineDataStore {
    /**
     * Get an {@link Observable} which will emit a List of {@link LineEntity}.
     */
    Observable<List<LineEntity>> lineEntityList();

    /**
     * Get an {@link Observable} which will emit a {@link LineEntity} by its id.
     *
     * @param lineId The id to retrieve line data.
     */
    Observable<LineEntity> lineEntityDetails(final String lineId);
}
