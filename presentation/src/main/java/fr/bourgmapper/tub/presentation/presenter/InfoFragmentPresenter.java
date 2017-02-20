package fr.bourgmapper.tub.presentation.presenter;

import javax.inject.Inject;

import fr.bourgmapper.tub.presentation.internal.di.PerActivity;
import fr.bourgmapper.tub.presentation.view.fragment.InfoFragment;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class InfoFragmentPresenter implements Presenter {
    private static String TAG = "MapOverviewFragmentPrstr";

    private InfoFragment infoFragment;

    @Inject
    InfoFragmentPresenter() {
    }

    public void setView(InfoFragment infoFragment) {
        this.infoFragment = infoFragment;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.infoFragment = null;
    }

    /**
     * Initializes the presenter.
     */
    public void initialize() {
    }
}
