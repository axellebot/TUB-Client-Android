package fr.bourgmapper.tub.presentation.presenter;

import android.support.design.widget.BottomSheetBehavior;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.io.InputStream;
import java.util.List;

import fr.bourgmapper.tub.R;
import fr.bourgmapper.tub.TubApp;
import fr.bourgmapper.tub.data.repository.DataRepository;
import fr.bourgmapper.tub.presentation.model.LineModel;
import fr.bourgmapper.tub.presentation.model.StopModel;
import fr.bourgmapper.tub.presentation.ui.activity.BaseActivityLifeCycle;
import fr.bourgmapper.tub.presentation.ui.fragment.HomeMapFragment;
import fr.bourgmapper.tub.presentation.ui.view.StopMapClusterItem;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by axell on 05/11/2016.
 */

public class HomeMapFragmentPresenter implements BaseActivityLifeCycle {
    private static String TAG = "HomeFragmentPresenter";

    private final HomeMapFragment view;
    private DataRepository dataRepository;

    public HomeMapFragmentPresenter(final HomeMapFragment view) {
        this.view = view;
    }

    @Override
    public void start() {
        this.dataRepository = TubApp.getInstance().getDataRepository();

        view.getGoogleMap().getUiSettings().setMyLocationButtonEnabled(true);
        view.getGoogleMap().setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                onMapClicked(latLng);
            }
        });
        view.getGoogleMap().setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                onMapDraged();
            }
        });

        int peekHeight = (int) view.getResources().getDimension(R.dimen.bottom_sheet_journey_search_top_bar_height);
        view.getmBottomSheetBehavior().setPeekHeight(peekHeight);
        view.getmBottomSheetBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);

        view.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        view.moveCamera(new LatLng(46.205539, 5.227177), 13f);
        addStopsClusterToMap();
        addLinesKMLToMap();
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    public void onStopClusterItemClicked(final StopMapClusterItem stopMapClusterItem) {
        view.getmClusterAdapter().setCurrentClusterItem(stopMapClusterItem);
    }

    public void onMapClicked(LatLng latLng) {
        this.view.getmBottomSheetBehavior().setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    public void onMapDraged() {
        this.view.getmBottomSheetBehavior().setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    private void addStopsClusterToMap() {
        //initMapWithStopsCLuster
        this.dataRepository.getAllStopsCall()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<StopModel>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        view.addStopsToMapWithCluster(TubApp.getInstance().getDataRepository().getAllStopsCache());
                    }

                    @Override
                    public void onNext(List<StopModel> stopModels) {
                        view.addStopsToMapWithCluster(stopModels);
                    }
                });
    }

    private void addLinesKMLToMap() {
        this.dataRepository.getAllLinesCall()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<LineModel>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<LineModel> lineModels) {

                        for (LineModel lineModel : lineModels) {
                            addLineKMLToMap(lineModel.getId());
                        }
                    }
                });
    }

    private void addLineKMLToMap(String id) {
        this.dataRepository.getLineKMLCall(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<InputStream>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(InputStream inputStream) {
                        view.addLineKMLToMap(inputStream);
                    }
                });
    }

}
