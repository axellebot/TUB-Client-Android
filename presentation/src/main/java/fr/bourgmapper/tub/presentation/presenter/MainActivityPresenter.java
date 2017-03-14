package fr.bourgmapper.tub.presentation.presenter;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import fr.bourgmapper.tub.presentation.navigation.Navigator;
import fr.bourgmapper.tub.presentation.view.HomeView;

public class MainActivityPresenter implements Presenter {
    private static String TAG = "MainActivityPresenter";

    private Navigator navigator;

    private HomeView homeView;

    @Inject
    public MainActivityPresenter(Navigator navigator) {
        this.navigator = navigator;
    }

    public void setView(@NonNull HomeView view) {
        this.homeView = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        setView(null);
    }

    public void onConnectClicked(){
        this.navigator.navigateToConnectionDialog(homeView.context());
    }

    public void onContactUsClicked(){
        this.navigator.navigateToContactIntent(homeView.context());
    }

    public void onShareClicked(){
        this.navigator.navigateToShareIntent(homeView.context());
    }
}
