package xyz.lebot.tub.ui.renderer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;

import xyz.lebot.tub.R;
import xyz.lebot.tub.ui.view.StopMapClusterItem;

/**
 * Created by axell on 06/11/2016.
 */

public class StopClusterRenderer extends DefaultClusterRenderer<StopMapClusterItem> {


    private Context context;
    private IconGenerator mClusterIconGenerator;

    public StopClusterRenderer(Context context, GoogleMap map,
                               ClusterManager<StopMapClusterItem> clusterManager) {
        super(context, map, clusterManager);
        this.context = context;
        this.mClusterIconGenerator = new IconGenerator(context.getApplicationContext());
    }

    @Override
    protected void onBeforeClusterItemRendered(StopMapClusterItem item,
                                               MarkerOptions markerOptions) {

        BitmapDescriptor markerDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.ic_beenhere_color_accent_24dp);

        markerOptions.icon(markerDescriptor);
    }

    @Override
    protected void onClusterItemRendered(StopMapClusterItem clusterItem, Marker marker) {
        super.onClusterItemRendered(clusterItem, marker);
    }

    @Override
    protected void onBeforeClusterRendered(Cluster<StopMapClusterItem> cluster, MarkerOptions markerOptions) {

        final Drawable clusterIcon = context.getDrawable(R.drawable.ic_beenhere_color_primary_24dp);

        mClusterIconGenerator.setBackground(clusterIcon);

        //modify padding for one or two digit numbers
        if (cluster.getSize() < 10) {
            mClusterIconGenerator.setContentPadding(40, 20, 0, 0);
        } else {
            mClusterIconGenerator.setContentPadding(30, 20, 0, 0);
        }

        Bitmap icon = mClusterIconGenerator.makeIcon(String.valueOf(cluster.getSize()));
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));
    }
}