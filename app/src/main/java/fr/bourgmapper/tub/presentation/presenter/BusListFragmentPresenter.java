package fr.bourgmapper.tub.presentation.presenter;

import android.util.Log;

import java.util.List;

import fr.bourgmapper.tub.TubApp;
import fr.bourgmapper.tub.data.repository.DataRepository;
import fr.bourgmapper.tub.presentation.model.LineModel;
import fr.bourgmapper.tub.presentation.ui.activity.BaseActivityLifeCycle;
import fr.bourgmapper.tub.presentation.ui.fragment.BusListFragment;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by axell on 05/11/2016.
 */

public class BusListFragmentPresenter implements BaseActivityLifeCycle {
    private static String TAG = "LineListFragmentPrstr";

    private final BusListFragment view;
    private DataRepository dataRepository;

    public BusListFragmentPresenter(final BusListFragment view) {
        this.view = view;
    }

    @Override
    public void start() {
        this.dataRepository = TubApp.app().getDataRepository();

        this.dataRepository.getLineListCall()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<LineModel>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<LineModel> lineModels) {
                        Log.i(TAG, lineModels.toString());
                        view.initList(lineModels);
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

    public void onLineItemClick(String lineId) {
        //TODO display line detail
    }
}
