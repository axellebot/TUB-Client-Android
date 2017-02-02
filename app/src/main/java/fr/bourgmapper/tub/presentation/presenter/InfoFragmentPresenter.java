package fr.bourgmapper.tub.presentation.presenter;

import fr.bourgmapper.tub.presentation.ui.activity.BaseActivityLifeCycle;
import fr.bourgmapper.tub.presentation.ui.fragment.InfoFragment;
import fr.bourgmapper.tub.presentation.ui.listener.NavigationListener;

/**
 * Created by axell on 05/11/2016.
 */

public class InfoFragmentPresenter implements BaseActivityLifeCycle {
    private static String TAG = "MapOverviewFragmentPrstr";

    private final InfoFragment view;
    private final NavigationListener navigationListener;

    public InfoFragmentPresenter(final InfoFragment view, NavigationListener navigationListener) {
        this.view = view;
        this.navigationListener = navigationListener;
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

    public void topBarBusClicked() {
        navigationListener.onBusButtonSelected();
    }

    public void topBarStopsClicked() {
        navigationListener.onStopsButtonSelected();
    }

}
