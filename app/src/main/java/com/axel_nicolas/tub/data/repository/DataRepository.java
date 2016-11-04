package com.axel_nicolas.tub.data.repository;

import com.axel_nicolas.tub.data.entity.LineEntity;
import com.axel_nicolas.tub.data.manager.ApiManager;
import com.axel_nicolas.tub.data.model.LineModel;

import java.util.List;

import rx.Observable;

/**
 * Created by axell on 04/11/2016.
 */

public interface DataRepository {
    Observable<List<LineModel>> getAllLinesCall();
}
