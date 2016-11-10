package xyz.lebot.tub.ui.presenter;

import xyz.lebot.tub.ui.fragment.MapFragment;
import xyz.lebot.tub.ui.navigator.Navigator;

/**
 * Created by axell on 05/11/2016.
 */

public class MapFragmentPresenter implements Presenter {
    private static String TAG = "MapFragmentPresenter";

    private final MapFragment view;
    private final Navigator navigator;

    public MapFragmentPresenter(final MapFragment view, final Navigator navigator) {
        this.view = view;
        this.navigator=navigator;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }
}
