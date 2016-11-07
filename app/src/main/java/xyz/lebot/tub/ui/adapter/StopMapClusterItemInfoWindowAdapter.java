package xyz.lebot.tub.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.clustering.ClusterItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.lebot.tub.R;
import xyz.lebot.tub.ui.view.StopMapClusterItem;

/**
 * Created by axell on 06/11/2016.
 */

public class StopMapClusterItemInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {


    private final View view;

    private ClusterItem currentClusterItem;

    @BindView(R.id.item_map_window_stop_info_title)
    TextView tvTitle;

    public StopMapClusterItemInfoWindowAdapter(LayoutInflater layoutInflater) {
        view = layoutInflater.inflate(R.layout.item_map_window_stop_info, null);
        ButterKnife.bind(this, view);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        if (currentClusterItem instanceof StopMapClusterItem) {
            StopMapClusterItem stopMapClusterItem = (StopMapClusterItem) currentClusterItem;
            tvTitle.setText(stopMapClusterItem.getTitle());
        }
        return view;
    }

    public void setCurrentClusterItem(ClusterItem currentClusterItem) {
        this.currentClusterItem = currentClusterItem;
    }
}
