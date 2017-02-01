package fr.bourgmapper.tub.data.manager;


import java.util.List;

import fr.bourgmapper.tub.data.entity.LineEntity;
import fr.bourgmapper.tub.data.entity.StopEntity;
import rx.Observable;

/**
 * Created by axell on 04/11/2016.
 */

public interface ApiManager {
    String API_PROD_URL = "https://tub.bourgmapper.fr/api/";
    String API_DEV_URL = "https://dev.tub.bourgmapper.fr/api/";

    Observable<List<LineEntity>> getAllLines();

    Observable<LineEntity> getLine(String line_id);

    Observable<List<LineEntity>> getLinesFromStop(String stop_id);

    Observable<List<StopEntity>> getAllStops();

    Observable<StopEntity> getStop(String stop_id);

    Observable<List<StopEntity>> getStopsFromLine(String line_id);

}
