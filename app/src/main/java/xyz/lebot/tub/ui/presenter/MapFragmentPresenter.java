package xyz.lebot.tub.ui.presenter;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;

import xyz.lebot.tub.ui.fragment.MapFragment;

/**
 * Created by axell on 05/11/2016.
 */

public class MapFragmentPresenter implements Presenter {
    private static String TAG = "MapFragmentPresenter";

    private final MapFragment view;

    public MapFragmentPresenter(final MapFragment view) {
        this.view = view;
    }

    @Override
    public void initialize() {
        view.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }
}
