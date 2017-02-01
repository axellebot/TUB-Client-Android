package fr.bourgmapper.tub.presentation.presenter;

import fr.bourgmapper.tub.presentation.ui.activity.BaseActivityLifeCycle;
import fr.bourgmapper.tub.presentation.ui.fragment.MapOverviewFragment;

/**
 * Created by axell on 05/11/2016.
 */

public class MapOverviewFragmentPresenter implements BaseActivityLifeCycle {
    private static String TAG = "MapOverviewFragmentPrstr";

    private final MapOverviewFragment view;

    public MapOverviewFragmentPresenter(final MapOverviewFragment view) {
        this.view = view;
    }

    @Override
    public void start() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }
}
