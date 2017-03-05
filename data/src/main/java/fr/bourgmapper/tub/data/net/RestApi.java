package fr.bourgmapper.tub.data.net;


import java.util.List;

import fr.bourgmapper.tub.data.entity.LineEntity;
import fr.bourgmapper.tub.data.entity.StopEntity;
import io.reactivex.Observable;

/**
 * RestApi for retrieving data from the network.
 */
public interface RestApi {
    String API_BASE_URL = "https://tub.bourgmapper.fr/api/";

    /**
     * Api url for getting all lines
     */
    String API_URL_GET_LINE_LIST = "lines";
    /**
     * Api url for getting a line profile: Remember to concatenate stopId + 'json'
     */
    String API_URL_GET_LINE_DETAILS = "lines/{line_id}";

    /**
     * Api url for getting all stops
     */
    String API_URL_GET_STOP_LIST = "stops";
    /**
     * Api url for getting a stop profile: Remember to concatenate stopId + 'json'
     */
    String API_URL_GET_STOP_DETAILS = "stops/{stop_id}";

    /**
     * Retrieves an {@link Observable} which will emit a List of {@link LineEntity}.
     */
    Observable<List<LineEntity>> lineEntityList();

    /**
     * Retrieves an {@link Observable} which will emit a {@link LineEntity}.
     *
     * @param lineId The line stopId used to get line data.
     */
    Observable<LineEntity> lineEntityById(final long lineId);

    /**
     * Retrieves an {@link Observable} which will emit a List of {@link StopEntity}.
     */
    Observable<List<StopEntity>> stopEntityList();

    /**
     * Retrieves an {@link Observable} which will emit a {@link StopEntity}.
     *
     * @param stopId The stop stopId used to get stop data.
     */
    Observable<StopEntity> stopEntityById(final long stopId);
}
