package com.axel_nicolas.tub.data.manager;


import com.axel_nicolas.tub.data.entity.LineEntity;
import com.axel_nicolas.tub.data.entity.StopEntity;

import java.util.List;

import rx.Observable;

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
