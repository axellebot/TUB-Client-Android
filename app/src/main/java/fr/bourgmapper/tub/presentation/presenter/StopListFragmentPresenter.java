package fr.bourgmapper.tub.presentation.presenter;

import android.util.Log;

import java.util.List;

import fr.bourgmapper.tub.TubApp;
import fr.bourgmapper.tub.data.repository.DataRepository;
import fr.bourgmapper.tub.presentation.model.StopModel;
import fr.bourgmapper.tub.presentation.ui.activity.BaseActivityLifeCycle;
import fr.bourgmapper.tub.presentation.ui.fragment.StopListFragment;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by axell on 05/11/2016.
 */

public class StopListFragmentPresenter implements BaseActivityLifeCycle {
    private static String TAG = "StopListFragmentPrstr";

    private final StopListFragment view;
    private DataRepository dataRepository;

    public StopListFragmentPresenter(final StopListFragment view) {
        this.view = view;
    }

    @Override
    public void start() {
        this.dataRepository = TubApp.app().getDataRepository();

        this.dataRepository.getAllStopsCall()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<StopModel>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        view.initList(TubApp.app().getDataRepository().getAllStopsCache());
                    }

                    @Override
                    public void onNext(List<StopModel> stopModels) {
                        Log.i(TAG, stopModels.toString());
                        view.initList(stopModels);
                    }
                });
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
