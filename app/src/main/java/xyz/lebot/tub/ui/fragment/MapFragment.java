package xyz.lebot.tub.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;

import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.lebot.tub.R;
import xyz.lebot.tub.ui.navigator.Navigator;
import xyz.lebot.tub.ui.presenter.MapFragmentPresenter;
import xyz.lebot.tub.R;
import xyz.lebot.tub.data.model.StopModel;
import xyz.lebot.tub.ui.adapter.StopMapClusterItemInfoWindowAdapter;
import xyz.lebot.tub.ui.presenter.MapFragmentPresenter;
import xyz.lebot.tub.ui.renderer.StopClusterRenderer;
import xyz.lebot.tub.ui.view.StopMapClusterItem;
import android.support.v4.app.Fragment;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    @BindView(R.id.fragment_map_map_view)
    MapView mMapView;

    private Navigator navigator;

    private GoogleMap googleMap;
    private LayoutInflater inflater;
    private MapFragmentPresenter presenter;


    private ClusterManager<StopMapClusterItem> mClusterManager;
    private StopMapClusterItemInfoWindowAdapter mClusterAdapter;
    private StopClusterRenderer mClusterRenderer;

    public MapFragment() {
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


        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);

        presenter = new MapFragmentPresenter(this, navigator);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);

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

    public void setMapType(int type) {
        googleMap.setMapType(type);
    }

    public void addStopsToMapWithCluster(List<StopModel> stopModels) {
        final ClusterManager<StopMapClusterItem> mClusterManager = new ClusterManager<>(this.getContext(), googleMap);
        final StopMapClusterItemInfoWindowAdapter mClusterAdapter = new StopMapClusterItemInfoWindowAdapter(this.inflater);
        final StopClusterRenderer mClusterRenderer = new StopClusterRenderer(this.getContext(), googleMap, mClusterManager);

        mClusterManager.setRenderer(mClusterRenderer);
        mClusterManager.getMarkerCollection().setOnInfoWindowAdapter(mClusterAdapter);
        mClusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<StopMapClusterItem>() {
            @Override
            public boolean onClusterItemClick(StopMapClusterItem stopMapClusterItem) {
                mClusterAdapter.setCurrentClusterItem(stopMapClusterItem);
                return false;
            }
        });
        mClusterManager.setOnClusterClickListener(new ClusterManager.OnClusterClickListener<StopMapClusterItem>() {
            @Override
            public boolean onClusterClick(Cluster<StopMapClusterItem> cluster) {
                String title = "";
                Collection<StopMapClusterItem> clusters = cluster.getItems();
                for (StopMapClusterItem clusterX :clusters){
                    title+=clusterX.getTitle()+"\n";
                }
                StopMapClusterItem stopMapClusterItem =  new StopMapClusterItem(cluster.getPosition(),title);
                mClusterAdapter.setCurrentClusterItem(stopMapClusterItem);
                return false;
            }
        });

        for (StopModel stopModel : stopModels) {
            mClusterManager.addItem(new StopMapClusterItem(
                    new LatLng(Double.parseDouble(stopModel.getLatitude()), Double.parseDouble(stopModel.getLongitude())),
                    stopModel.getLabel()
            ));
        }

        googleMap.setInfoWindowAdapter(mClusterAdapter);
        googleMap.setOnCameraIdleListener(mClusterManager);
        googleMap.setOnMarkerClickListener(mClusterManager);
    }

    public void addStopsToMap(List<StopModel> stopModels) {
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
