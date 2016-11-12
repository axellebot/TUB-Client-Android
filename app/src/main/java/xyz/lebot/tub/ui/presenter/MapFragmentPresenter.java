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
import xyz.lebot.tub.ui.navigator.Navigator;

/**
 * Created by axell on 05/11/2016.
 */

public class MapFragmentPresenter implements Presenter {
    private static String TAG = "MapFragmentPresenter";

    private final MapFragment view;
    private final Navigator navigator;

    public MapFragmentPresenter(final MapFragment view, final Navigator navigator) {
        this.view = view;
        this.navigator = navigator;
    }

    @Override
    public void initialize() {
        view.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        view.moveCamera(new LatLng(46.205539, 5.227177), 13f);
        addStopsClusterToMap();
    }

    private void addStopsClusterToMap() {
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
