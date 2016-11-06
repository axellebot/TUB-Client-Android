package com.axel_nicolas.tub.data.repository;

import com.axel_nicolas.tub.data.entity.LineEntity;
import com.axel_nicolas.tub.data.manager.ApiManager;
import com.axel_nicolas.tub.data.model.LineModel;
import com.axel_nicolas.tub.data.model.StopModel;

import java.util.List;

import rx.Observable;

/**
 * Created by axell on 04/11/2016.
 */

public interface DataRepository {
    Observable<List<LineModel>> getAllLinesCall();
    Observable<LineModel> getLineCall(String id);

    Observable<List<StopModel>> getAllStopsCall();
    Observable<StopModel> getStopCall(String id);


    List<LineModel> getAllLinesCache();
    List<StopModel> getAllStopsCache();
}
