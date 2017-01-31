package fr.bourgmapper.tub.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.kml.KmlLayer;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.bourgmapper.tub.data.model.StopModel;
import fr.bourgmapper.tub.ui.adapter.StopMapClusterItemInfoWindowAdapter;
import fr.bourgmapper.tub.ui.navigator.Navigator;
import fr.bourgmapper.tub.R;
import fr.bourgmapper.tub.ui.presenter.HomeFragmentPresenter;
import fr.bourgmapper.tub.ui.renderer.StopClusterRenderer;
import fr.bourgmapper.tub.ui.view.StopMapClusterItem;

public class HomeFragment extends Fragment implements OnMapReadyCallback {

    @BindView(R.id.fragment_map_map_view)
    MapView mMapView;

    private Navigator navigator;

    private LayoutInflater inflater;
    private HomeFragmentPresenter presenter;


    //GoogleMap
    private GoogleMap googleMap;
    private ClusterManager<StopMapClusterItem> mClusterManager;
    private StopMapClusterItemInfoWindowAdapter mClusterAdapter;
    private StopClusterRenderer mClusterRenderer;

    //BottomSheet
    private BottomSheetBehavior mBottomSheetBehavior;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        this.navigator = (Navigator) args.get("NAVIGATOR");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflater = inflater;
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        ButterKnife.bind(this, view);


        View bottomSheetDialog = view.findViewById(R.id.bottom_sheet_overview);
        this.mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheetDialog);

        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        presenter = new HomeFragmentPresenter(this, navigator);

        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;


        mMapView.onResume();
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

    public void moveCamera(LatLng latLng, float zoom) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }

    public void setMapType(int type) {
        googleMap.setMapType(type);
    }

    public void addStopsToMapWithCluster(List<StopModel> stopModels) {
        this.mClusterManager = new ClusterManager<>(this.getContext(), googleMap);
        this.mClusterAdapter = new StopMapClusterItemInfoWindowAdapter(this.getContext());
        this.mClusterRenderer = new StopClusterRenderer(this.getContext(), googleMap, mClusterManager);

        mClusterManager.setRenderer(mClusterRenderer);
        mClusterManager.getMarkerCollection().setOnInfoWindowAdapter(mClusterAdapter);
        mClusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<StopMapClusterItem>() {
            @Override
            public boolean onClusterItemClick(StopMapClusterItem stopMapClusterItem) {
                presenter.onStopClusterItemClicked(stopMapClusterItem);
                return false;
            }
        });
        mClusterManager.setOnClusterClickListener(new ClusterManager.OnClusterClickListener<StopMapClusterItem>() {
            @Override
            public boolean onClusterClick(Cluster<StopMapClusterItem> cluster) {
                return true;
            }
        });

        for (StopModel stopModel : stopModels) {
            mClusterManager.addItem(new StopMapClusterItem(
                    new LatLng(Double.parseDouble(stopModel.getLatitude()), Double.parseDouble(stopModel.getLongitude())),
                    stopModel.getId(),
                    stopModel.getLabel()
            ));
        }

        googleMap.setInfoWindowAdapter(mClusterAdapter);
        googleMap.setOnCameraIdleListener(mClusterManager);
        googleMap.setOnMarkerClickListener(mClusterManager);
    }

    public void addLineKMLToMap(InputStream inputStream) {
        try {
            KmlLayer layer = new KmlLayer(googleMap, inputStream, getContext().getApplicationContext());
            layer.addLayerToMap();
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }
    }


    public ClusterManager<StopMapClusterItem> getmClusterManager() {
        return mClusterManager;
    }

    public StopMapClusterItemInfoWindowAdapter getmClusterAdapter() {
        return mClusterAdapter;
    }

    public StopClusterRenderer getmClusterRenderer() {
        return mClusterRenderer;
    }

    public BottomSheetBehavior getmBottomSheetBehavior() {
        return mBottomSheetBehavior;
    }

    public GoogleMap getGoogleMap() {
        return googleMap;
    }
}
