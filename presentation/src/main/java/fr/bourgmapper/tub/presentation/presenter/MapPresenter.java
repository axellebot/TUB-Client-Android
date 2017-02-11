package fr.bourgmapper.tub.presentation.presenter;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

import fr.bourgmapper.tub.data.repository.DataRepository;
import fr.bourgmapper.tub.presentation.AndroidApplication;
import fr.bourgmapper.tub.presentation.internal.di.PerActivity;
import fr.bourgmapper.tub.presentation.model.LineModel;
import fr.bourgmapper.tub.presentation.model.StopModel;
import fr.bourgmapper.tub.presentation.view.fragment.MapFragment;
import fr.bourgmapper.tub.presentation.view.StopMapClusterItem;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class MapPresenter implements Presenter {
    private static String TAG = "MapPresenter";

    private final MapFragment view;
    private DataRepository dataRepository;

    @Inject
    public MapPresenter(final MapFragment view) {
        this.view = view;
    }

    @Override
    public void start() {
        this.dataRepository = AndroidApplication.app().getDataRepository();

        view.getGoogleMap().getUiSettings().setMyLocationButtonEnabled(true);
        view.getGoogleMap().setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
            }
        });
        view.getGoogleMap().setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
            }
        });


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
    public void destroy() {

    }

    public void onStopClusterItemClicked(final StopMapClusterItem stopMapClusterItem) {
        view.getmClusterAdapter().setCurrentClusterItem(stopMapClusterItem);
    }

    private void addStopsClusterToMap() {
        //initMapWithStopsCLuster
        this.dataRepository.getStopListCall()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<StopModel>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<StopModel> stopModels) {
                        view.addStopsToMapWithCluster(stopModels);
                    }
                });
    }

    private void addLinesKMLToMap() {
        this.dataRepository.getLineListCall()
                .subscribeOn(Schedulers.io())
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
                .subscribeOn(Schedulers.io())
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
