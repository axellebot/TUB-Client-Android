package fr.bourgmapper.tub.data.repository;

import java.io.InputStream;
import java.util.List;

import fr.bourgmapper.tub.presentation.model.LineModel;
import fr.bourgmapper.tub.presentation.model.StopModel;
import rx.Observable;

/**
 * Created by axell on 04/11/2016.
 */

public interface DataRepository {
    //Api
    Observable<List<LineModel>> getLineListCall();

    Observable<LineModel> getLineCall(String line_id);

    Observable<List<LineModel>> getLineListFromStop(String stop_id);

    Observable<List<StopModel>> getStopListCall();

    Observable<StopModel> getStopCall(String stop_id);

    Observable<List<StopModel>> getStopsFromLine(String line_id);

    //Download
    Observable<InputStream> getLineKMLCall(String id);
}
