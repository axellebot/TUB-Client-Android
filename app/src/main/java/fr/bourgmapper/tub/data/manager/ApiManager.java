package fr.bourgmapper.tub.data.manager;


import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import java.util.List;

import rx.Observable;
import fr.bourgmapper.tub.data.entity.LineEntity;
import fr.bourgmapper.tub.data.entity.StopEntity;

/**
 * Created by axell on 04/11/2016.
 */

public interface ApiManager {
    Observable<List<LineEntity>> getAllLines();

    Observable<LineEntity> getLine(String line_id);

    Observable<List<LineEntity>> getLinesFromStop(String stop_id);

    Observable<List<StopEntity>> getAllStops();

    Observable<StopEntity> getStop(String stop_id);

    Observable<List<StopEntity>> getStopsFromLine(String line_id);

}
