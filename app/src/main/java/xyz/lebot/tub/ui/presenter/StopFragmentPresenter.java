package xyz.lebot.tub.ui.presenter;

import android.util.Log;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import xyz.lebot.tub.App;
import xyz.lebot.tub.data.model.StopModel;
import xyz.lebot.tub.ui.fragment.StopFragment;
import xyz.lebot.tub.ui.navigator.Navigator;

/**
 * Created by axell on 05/11/2016.
 */

public class StopFragmentPresenter implements Presenter {
    private static String TAG = "StopFragmentPresenter";

    private final StopFragment view;
    private final Navigator navigator;

    public StopFragmentPresenter(final StopFragment view, final Navigator navigator) {
        this.view = view;
        this.navigator = navigator;
    }


    @Override
    public void initialize() {
        App.getInstance().getDataRepository().getAllStopsCall()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<StopModel>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        view.initList(App.getInstance().getDataRepository().getAllStopsCache());
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
