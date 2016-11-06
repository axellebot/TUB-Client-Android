package com.axel_nicolas.tub.data.repository;

import com.axel_nicolas.tub.data.entity.LineEntity;
import com.axel_nicolas.tub.data.entity.StopEntity;
import com.axel_nicolas.tub.data.entity.mapper.LineDataMapper;
import com.axel_nicolas.tub.data.entity.mapper.StopDataMapper;
import com.axel_nicolas.tub.data.manager.ApiManager;
import com.axel_nicolas.tub.data.manager.CacheManager;
import com.axel_nicolas.tub.data.model.LineModel;
import com.axel_nicolas.tub.data.model.StopModel;

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
    private StopDataMapper stopDataMapper;

    public DataRepositoryImpl(ApiManager apiManager, CacheManager cacheManager, LineDataMapper lineDataMapper, StopDataMapper stopDataMapper) {
        this.apiManager = apiManager;
        this.cacheManager = cacheManager;
        this.lineDataMapper = lineDataMapper;
        this.stopDataMapper = stopDataMapper;
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

    @Override
    public Observable<LineModel> getLineCall(String id) {
        return apiManager.getLine(id).map(new Func1<LineEntity, LineModel>() {
            @Override
            public LineModel call(LineEntity lineEntity) {
                LineModel lineModel = lineDataMapper.transform(lineEntity);
                cacheManager.setLine(lineModel);
                return lineModel;
            }
        });
    }

    @Override
    public Observable<List<StopModel>> getAllStopsCall() {
        return apiManager.getAllStops().map(new Func1<List<StopEntity>, List<StopModel>>() {
            @Override
            public List<StopModel> call(List<StopEntity> stopEntities) {
                List<StopModel> stopModels = stopDataMapper.transform(stopEntities);
                cacheManager.setStops(stopModels);
                return stopModels;
            }
        });
    }

    @Override
    public Observable<StopModel> getStopCall(String id) {
        return apiManager.getStop(id).map(new Func1<StopEntity, StopModel>() {
            @Override
            public StopModel call(StopEntity stopEntity) {
                StopModel stopModel = stopDataMapper.transform(stopEntity);
                cacheManager.setStop(stopModel);
                return stopModel;
            }
        });
    }

    @Override
    public List<LineModel> getAllLinesCache() {
        return this.cacheManager.getLines();
    }

    @Override
    public List<StopModel> getAllStopsCache() {
        return this.cacheManager.getStops();
    }

}
