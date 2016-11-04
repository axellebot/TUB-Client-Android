package com.axel_nicolas.tub.data.manager;


import com.axel_nicolas.tub.data.entity.LineEntity;

import java.util.List;

import rx.Observable;

/**
 * Created by axell on 04/11/2016.
 */

public interface ApiManager {
    String API_PROD_URL = "http://tub.lebot.xyz/api/";
    String API_DEV_URL = "http://dev.tub.lebot.xyz/api/";

    Observable<List<LineEntity>> getAllLines();
}
