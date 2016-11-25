package xyz.lebot.tub.data.repository;

import com.facebook.CallbackManager;
import com.facebook.Profile;

import java.io.InputStream;
import java.util.List;

import rx.Observable;
import xyz.lebot.tub.data.model.LineModel;
import xyz.lebot.tub.data.model.ProfileModel;
import xyz.lebot.tub.data.model.StopModel;

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

    //Facebook
    CallbackManager getFacebookCallBackManager();

    //UserProfile
    Observable<ProfileModel> getProfil();
}
