package com.axel_nicolas.tub.data.repository;

import com.axel_nicolas.tub.data.entity.LineEntity;
import com.axel_nicolas.tub.data.entity.mapper.LineDataMapper;
import com.axel_nicolas.tub.data.manager.ApiManager;
import com.axel_nicolas.tub.data.manager.CacheManager;
import com.axel_nicolas.tub.data.model.LineModel;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by axell on 04/11/2016.
 */

public class DataRepositoryImpl implements DataRepository {
    private ApiManager apiManager;
    private CacheManager cacheManager;
    private LineDataMapper lineDataMapper;

    public DataRepositoryImpl(ApiManager apiManager, CacheManager cacheManager, LineDataMapper lineDataMapper) {
        this.apiManager = apiManager;
        this.cacheManager = cacheManager;
        this.lineDataMapper=lineDataMapper;
    }

    @Override
    public Observable<List<LineModel>> getAllLinesCall() {
        return apiManager.getAllLines().map(new Func1<List<LineEntity>, List<LineModel>>() {
            @Override
            public List<LineModel> call(List<LineEntity> lineEntities) {
                List<LineModel> lineModels = lineDataMapper.transform(lineEntities);
                cacheManager.setLines(lineModels);
                return lineModels;
            }
        });
    }
}
