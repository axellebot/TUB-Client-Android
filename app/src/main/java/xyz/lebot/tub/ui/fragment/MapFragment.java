package xyz.lebot.tub.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import xyz.lebot.tub.R;
import xyz.lebot.tub.data.model.LineModel;
import xyz.lebot.tub.data.model.StopModel;
import xyz.lebot.tub.ui.adapter.StopDetailLineListAdapter;
import xyz.lebot.tub.ui.adapter.StopMapClusterItemInfoWindowAdapter;
import xyz.lebot.tub.ui.navigator.Navigator;
import xyz.lebot.tub.ui.presenter.MapFragmentPresenter;
import xyz.lebot.tub.ui.renderer.StopClusterRenderer;
import xyz.lebot.tub.ui.view.StopMapClusterItem;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    @BindView(R.id.fragment_map_map_view)
    MapView mMapView;

    private Navigator navigator;

    private GoogleMap googleMap;
    private LayoutInflater inflater;
    private MapFragmentPresenter presenter;

    private StopDetailLineListAdapter adapter;


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
}
