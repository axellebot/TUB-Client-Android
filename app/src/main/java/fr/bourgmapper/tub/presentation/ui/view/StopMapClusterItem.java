package fr.bourgmapper.tub.presentation.ui.view;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by axell on 06/11/2016.
 */

public class StopMapClusterItem implements ClusterItem {

    private final LatLng position;
    private final String id;
    private final String title;

    public StopMapClusterItem(LatLng position, String id,String title) {
        this.position = position;
        this.id=id;
        this.title = title;
    }

    @Override
    public LatLng getPosition() {
        return position;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
