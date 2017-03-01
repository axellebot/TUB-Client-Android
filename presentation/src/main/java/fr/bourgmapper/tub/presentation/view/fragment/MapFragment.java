package fr.bourgmapper.tub.presentation.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.kml.KmlLayer;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.bourgmapper.tub.R;
import fr.bourgmapper.tub.presentation.internal.di.components.CoreComponent;
import fr.bourgmapper.tub.presentation.listener.LineListListener;
import fr.bourgmapper.tub.presentation.listener.StopListListener;
import fr.bourgmapper.tub.presentation.model.LineModel;
import fr.bourgmapper.tub.presentation.model.StopModel;
import fr.bourgmapper.tub.presentation.presenter.MapFragmentPresenter;
import fr.bourgmapper.tub.presentation.view.MainMapView;

public class MapFragment extends BaseFragment implements OnMapReadyCallback, MainMapView {

    @Inject
    MapFragmentPresenter mapFragmentPresenter;

    @BindView(R.id.fragment_map_map_view)
    MapView mMapView;

    private StopListListener stopListListener;
    private LineListListener lineListListener;

    private Bundle savedInstanceState;

    //GoogleMap
    private GoogleMap googleMap;

    public MapFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof StopListListener) {
            this.stopListListener = (StopListListener) activity;
        }
        if (activity instanceof LineListListener) {
            this.lineListListener = (LineListListener) activity;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(CoreComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        ButterKnife.bind(this, view);

        this.savedInstanceState = savedInstanceState;
        setupMap();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mapFragmentPresenter.setView(this);
        if (savedInstanceState == null) {
            //load things
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.mapFragmentPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.mapFragmentPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //TODO : removed map adapter
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mapFragmentPresenter.destroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.lineListListener = null;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        this.googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        mMapView.onResume();
        this.loadMap();
    }

    @Override
    public void renderStopList(Collection<StopModel> stopModelCollection) {
    }

    @Override
    public void renderLineList(Collection<LineModel> lineModelCollection) {
    }

    @Override
    public void viewStop(StopModel stopModel) {
        this.stopListListener.onStopClicked(stopModel);
    }

    @Override
    public void showLoadingStopList() {

    }

    @Override
    public void hideLoadingStopList() {

    }

    @Override
    public void showRetryStopList() {

    }

    @Override
    public void hideRetryStopList() {

    }

    @Override
    public void showErrorStopList(String message) {

    }

    @Override
    public void showLoadingLineList() {

    }

    @Override
    public void hideLoadingLineList() {

    }

    @Override
    public void showRetryLineList() {

    }

    @Override
    public void hideRetryLineList() {

    }

    @Override
    public void showErrorLineList(String message) {

    }

    @Override
    public void moveCamera(LatLng latLng, float zoom) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }

    @Override
    public Context context() {
        return this.getContext();
    }

    private void setupMap() {
        mMapView.onCreate(this.savedInstanceState);
        mMapView.getMapAsync(this);
    }


    /**
     * Loads map.
     */
    private void loadMap() {
        this.mapFragmentPresenter.initialize();
    }

    //-----------------------------------------------


    public void addLineKMLToMap(InputStream inputStream) {
        try {
            KmlLayer layer = new KmlLayer(googleMap, inputStream, getContext().getApplicationContext());
            layer.addLayerToMap();
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }
    }
}
