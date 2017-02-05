package fr.bourgmapper.tub.data.repository;

import java.io.InputStream;
import java.util.List;

import fr.bourgmapper.tub.TubApp;
import fr.bourgmapper.tub.data.entity.LineEntity;
import fr.bourgmapper.tub.data.entity.StopEntity;
import fr.bourgmapper.tub.data.entity.mapper.LineDataMapper;
import fr.bourgmapper.tub.data.entity.mapper.StopDataMapper;
import fr.bourgmapper.tub.data.manager.ApiManager;
import fr.bourgmapper.tub.data.manager.CacheManager;
import fr.bourgmapper.tub.data.manager.DownloadManager;
import fr.bourgmapper.tub.presentation.model.LineModel;
import fr.bourgmapper.tub.presentation.model.StopModel;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by axell on 04/11/2016.
 */

//TODO : Add TTL to cache

public class DataRepositoryImpl implements DataRepository {
    private ApiManager apiManager;
    private CacheManager cacheManager;
    private DownloadManager downloadManager;
    private LineDataMapper lineDataMapper;
    private StopDataMapper stopDataMapper;

    public DataRepositoryImpl(ApiManager apiManager, CacheManager cacheManager, DownloadManager downloadManager, LineDataMapper lineDataMapper, StopDataMapper stopDataMapper) {
        this.apiManager = apiManager;
        this.cacheManager = cacheManager;
        this.downloadManager = downloadManager;
        this.lineDataMapper = lineDataMapper;
        this.stopDataMapper = stopDataMapper;
    }

    @Override
    public Observable<List<LineModel>> getLineListCall() {
        Observable<List<LineModel>> lineModelListObservable = null;

        if (TubApp.app().isNetworkAvailable()) {
            lineModelListObservable = apiManager.getAllLines().map(new Func1<List<LineEntity>, List<LineModel>>() {
                @Override
                public List<LineModel> call(List<LineEntity> lineEntities) {
                    List<LineModel> lineModels = lineDataMapper.transform(lineEntities);
                    cacheManager.saveLineEntityList(lineEntities);
                    return lineModels;
                }
            });
        } else {
            lineModelListObservable = Observable.just(
                    lineDataMapper.transform(cacheManager.getLineEntityList())
            );
        }

        return lineModelListObservable;
    }

    @Override
    public Observable<LineModel> getLineCall(String lineId) {
        Observable<LineModel> lineModelObservable = null;

        if (TubApp.app().isNetworkAvailable()) {
            lineModelObservable = apiManager.getLine(lineId).map(new Func1<LineEntity, LineModel>() {
                @Override
                public LineModel call(LineEntity lineEntity) {
                    LineModel lineModel = lineDataMapper.transform(lineEntity);
                    cacheManager.saveLineEntity(lineEntity);
                    return lineModel;
                }
            });
        } else {
            lineModelObservable = Observable.just(
                    lineDataMapper.transform(cacheManager.getLineEntity(lineId))
            );
        }
        return lineModelObservable;
    }

    @Override
    public Observable<List<LineModel>> getLineListFromStop(String stopId) {
        return apiManager.getLineListFromStop(stopId).map(new Func1<List<LineEntity>, List<LineModel>>() {
            @Override
            public List<LineModel> call(List<LineEntity> lineEntityList) {
                List<LineModel> lineModelList = lineDataMapper.transform(lineEntityList);
                cacheManager.saveLineEntityList(lineEntityList);
                return lineModelList;
            }
        });
    }

    @Override
    public Observable<List<StopModel>> getStopListCall() {
        Observable<List<StopModel>> stopModelListObservable = null;
        if (TubApp.app().isNetworkAvailable()) {
            stopModelListObservable = apiManager.getAllStops().map(new Func1<List<StopEntity>, List<StopModel>>() {
                @Override
                public List<StopModel> call(List<StopEntity> stopEntityList) {
                    List<StopModel> stopModelList = stopDataMapper.transform(stopEntityList);
                    cacheManager.saveStopEntityList(stopEntityList);
                    return stopModelList;
                }
            });
        } else {
            stopModelListObservable = Observable.just(
                    stopDataMapper.transform(cacheManager.getStopEntityList())
            );
        }
        return stopModelListObservable;
    }

    @Override
    public Observable<StopModel> getStopCall(String stopId) {
        Observable<StopModel> stopModelObservable = null;

        if (TubApp.app().isNetworkAvailable()) {
            stopModelObservable = apiManager.getStop(stopId).map(new Func1<StopEntity, StopModel>() {
                @Override
                public StopModel call(StopEntity stopEntity) {
                    StopModel stopModel = stopDataMapper.transform(stopEntity);
                    cacheManager.saveStopEntity(stopEntity);
                    return stopModel;
                }
            });
        } else {
            stopModelObservable = Observable.just(
                    stopDataMapper.transform(cacheManager.getStopEntity(stopId))
            );
        }
        return stopModelObservable;
    }

    @Override
    public Observable<List<StopModel>> getStopListFromLine(String lineId) {
        return apiManager.getStopsFromLine(lineId).map(new Func1<List<StopEntity>, List<StopModel>>() {
            @Override
            public List<StopModel> call(List<StopEntity> stopEntities) {
                List<StopModel> stopModels = stopDataMapper.transform(stopEntities);
                cacheManager.saveStopEntityList(stopEntities);
                return stopModels;
            }
        });
    }

    @Override
    public Observable<InputStream> getLineKMLCall(String lineId) {
        return this.downloadManager.getLineKmlFile(lineId).map(new Func1<ResponseBody, InputStream>() {
            @Override
            public InputStream call(ResponseBody responseBody) {
                return responseBody.byteStream();
            }
        });
    }

}
