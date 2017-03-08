package fr.bourgmapper.tub.presentation.presenter;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import fr.bourgmapper.tub.presentation.view.MenuView;

public class MainActivityPresenter implements Presenter {
    private static String TAG = "MainActivityPresenter";

    private MenuView menuView;

    @Inject
    public MainActivityPresenter() {

    }

    public void setView(@NonNull MenuView view) {
        this.menuView = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }
}
