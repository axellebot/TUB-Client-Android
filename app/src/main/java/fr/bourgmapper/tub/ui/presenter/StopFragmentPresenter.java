package fr.bourgmapper.tub.ui.presenter;

import android.util.Log;

import java.util.List;

import fr.bourgmapper.tub.data.model.StopModel;
import fr.bourgmapper.tub.ui.fragment.StopFragment;
import fr.bourgmapper.tub.ui.navigator.Navigator;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import fr.bourgmapper.tub.TubApp;
import fr.bourgmapper.tub.data.repository.DataRepository;

/**
 * Created by axell on 05/11/2016.
 */

public class StopFragmentPresenter implements Presenter {
    private static String TAG = "StopFragmentPresenter";

    private final StopFragment view;
    private final Navigator navigator;
    private DataRepository dataRepository;

    public StopFragmentPresenter(final StopFragment view, final Navigator navigator) {
        this.view = view;
        this.navigator = navigator;
    }


    @Override
    public void initialize() {

        this.dataRepository = TubApp.getInstance().getDataRepository();

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
                        view.initList(TubApp.getInstance().getDataRepository().getAllStopsCache());
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
}
