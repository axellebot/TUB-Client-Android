package xyz.lebot.tub.ui.presenter;

import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import xyz.lebot.tub.App;
import xyz.lebot.tub.data.model.StopModel;
import xyz.lebot.tub.ui.fragment.MapFragment;

/**
 * Created by axell on 05/11/2016.
 */

public class MapFragmentPresenter implements Presenter {
    private static String TAG = "MapFragmentPresenter";

    private final MapFragment view;

    public MapFragmentPresenter(final MapFragment view) {
        this.view = view;
    }

    @Override
    public void initialize() {
        view.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        view.moveCamera(new LatLng(46.205539, 5.227177), 13f);

        //initMapWithStopsCLuster
        App.getInstance().getDataRepository().getAllStopsCall()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<StopModel>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        view.addStopsToMapWithCluster(App.getInstance().getDataRepository().getAllStopsCache());
                    }

                    @Override
                    public void onNext(List<StopModel> stopModels) {
                        Log.i(TAG, stopModels.toString());
                        view.addStopsToMapWithCluster(stopModels);
                    }
                });
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }
}
