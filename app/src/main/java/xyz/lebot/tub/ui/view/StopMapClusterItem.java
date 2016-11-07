package xyz.lebot.tub.ui.view;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by axell on 06/11/2016.
 */

public class StopMapClusterItem implements ClusterItem {

    private final LatLng mPosition;
    private final String title;

    public StopMapClusterItem(LatLng mPosition, String title) {
        this.mPosition = mPosition;
        this.title = title;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    public String getTitle() {
        return title;
    }
}
