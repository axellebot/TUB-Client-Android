package fr.bourgmapper.tub.data.repository;

import java.io.InputStream;
import java.util.List;

import fr.bourgmapper.tub.data.model.LineModel;
import fr.bourgmapper.tub.data.model.StopModel;
import rx.Observable;

/**
 * Created by axell on 04/11/2016.
 */

public interface DataRepository {
    //Api
    Observable<List<LineModel>> getAllLinesCall();

    Observable<LineModel> getLineCall(String line_id);

    Observable<List<LineModel>> getLinesFromStop(String stop_id);

    Observable<List<StopModel>> getAllStopsCall();

    Observable<StopModel> getStopCall(String stop_id);

    Observable<List<StopModel>> getStopsFromLine(String line_id);

    //Cache
    List<LineModel> getAllLinesCache();

    List<StopModel> getAllStopsCache();

    //Download
    Observable<InputStream> getLineKMLCall(String id);
}
