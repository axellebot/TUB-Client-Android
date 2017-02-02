package fr.bourgmapper.tub.presentation.presenter;

import fr.bourgmapper.tub.presentation.ui.activity.BaseActivityLifeCycle;
import fr.bourgmapper.tub.presentation.ui.fragment.InfoFragment;

/**
 * Created by axell on 05/11/2016.
 */

public class InfoFragmentPresenter implements BaseActivityLifeCycle {
    private static String TAG = "MapOverviewFragmentPrstr";

    private final InfoFragment view;

    public InfoFragmentPresenter(final InfoFragment view) {
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
