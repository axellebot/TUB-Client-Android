package xyz.lebot.tub.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.lebot.tub.R;
import xyz.lebot.tub.data.model.StopModel;
import xyz.lebot.tub.ui.presenter.MapFragmentPresenter;

public class MapFragment extends android.support.v4.app.Fragment implements OnMapReadyCallback {

    @BindView(R.id.fragment_map_map_view)
    MapView mMapView;

    private GoogleMap googleMap;
    private MapFragmentPresenter presenter;

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_map, container, false);
        ButterKnife.bind(this, view);


        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);
        mMapView.onResume();

        presenter = new MapFragmentPresenter(this);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);

        presenter.initialize();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.resume();
    }

    public void setMapType(int type) {
        googleMap.setMapType(type);
    }

    public void initMapWithStops(List<StopModel> stopModels) {


        for (StopModel stopModel : stopModels) {
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(
                            Double.parseDouble(stopModel.getLatitude()),
                            Double.parseDouble(stopModel.getLongitude())))
                    .title(stopModel.getLabel())
                    .snippet(stopModel.getId() + "-" + stopModel.getLabel())
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_beenhere_color_accent_24dp))
            );
        }


    }

    public void moveCamera(LatLng latLng, float zoom) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }

}
