package xyz.lebot.tub.data.repository;

import java.io.InputStream;
import java.util.List;

import rx.Observable;
import xyz.lebot.tub.data.model.LineModel;
import xyz.lebot.tub.data.model.StopModel;

/**
 * Created by axell on 04/11/2016.
 */

public interface DataRepository {
    //Api
    Observable<List<LineModel>> getAllLinesCall();

    Observable<LineModel> getLineCall(String id);

    Observable<List<StopModel>> getAllStopsCall();

    Observable<StopModel> getStopCall(String id);

    //Cache
    List<LineModel> getAllLinesCache();

    List<StopModel> getAllStopsCache();

    //Download
    Observable<InputStream> getLineKMLCall(String id);
}
