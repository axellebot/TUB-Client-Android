package xyz.lebot.tub.data.repository;

import java.io.InputStream;
import java.util.List;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.functions.Func1;
import xyz.lebot.tub.data.entity.LineEntity;
import xyz.lebot.tub.data.entity.StopEntity;
import xyz.lebot.tub.data.entity.mapper.LineDataMapper;
import xyz.lebot.tub.data.entity.mapper.StopDataMapper;
import xyz.lebot.tub.data.manager.ApiManager;
import xyz.lebot.tub.data.manager.CacheManager;
import xyz.lebot.tub.data.manager.DownloadManager;
import xyz.lebot.tub.data.model.LineModel;
import xyz.lebot.tub.data.model.StopModel;

/**
 * Created by axell on 04/11/2016.
 */

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
    public Observable<LineModel> getLineCall(String line_id) {
        return apiManager.getLine(line_id).map(new Func1<LineEntity, LineModel>() {
            @Override
            public LineModel call(LineEntity lineEntity) {
                LineModel lineModel = lineDataMapper.transform(lineEntity);
                cacheManager.setLine(lineModel);
                return lineModel;
            }
        });
    }

    @Override
    public Observable<List<LineModel>> getLinesFromStop(String stop_id) {
        return apiManager.getLinesFromStop(stop_id).map(new Func1<List<LineEntity>, List<LineModel>>() {
            @Override
            public List<LineModel> call(List<LineEntity> lineEntities) {
                List<LineModel> lineModels = lineDataMapper.transform(lineEntities);
                cacheManager.setLines(lineModels);
                return lineModels;
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
    public Observable<StopModel> getStopCall(String stop_id) {
        return apiManager.getStop(stop_id).map(new Func1<StopEntity, StopModel>() {
            @Override
            public StopModel call(StopEntity stopEntity) {
                StopModel stopModel = stopDataMapper.transform(stopEntity);
                cacheManager.setStop(stopModel);
                return stopModel;
            }
        });
    }

    @Override
    public Observable<List<StopModel>> getStopsFromLine(String line_id) {
        return apiManager.getStopsFromLine(line_id).map(new Func1<List<StopEntity>, List<StopModel>>() {
            @Override
            public List<StopModel> call(List<StopEntity> stopEntities) {
                List<StopModel> stopModels = stopDataMapper.transform(stopEntities);
                cacheManager.setStops(stopModels);
                return stopModels;
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

    @Override
    public Observable<InputStream> getLineKMLCall(String id) {
        return this.downloadManager.getLineKmlFile(id).map(new Func1<ResponseBody, InputStream>() {
            @Override
            public InputStream call(ResponseBody responseBody) {
                return responseBody.byteStream();
            }
        });
    }

}
