package xyz.lebot.tub.data.manager;


import java.util.List;

import rx.Observable;
import xyz.lebot.tub.data.entity.LineEntity;
import xyz.lebot.tub.data.entity.StopEntity;

/**
 * Created by axell on 04/11/2016.
 */

public interface ApiManager {
    String API_PROD_URL = "http://tub.lebot.xyz/api/";
    String API_DEV_URL = "http://dev.tub.lebot.xyz/api/";

    Observable<List<LineEntity>> getAllLines();

    Observable<LineEntity> getLine(String id);

    Observable<List<StopEntity>> getAllStops();

    Observable<StopEntity> getStop(String id);
}