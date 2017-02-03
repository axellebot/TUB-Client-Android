package fr.bourgmapper.tub.presentation.presenter;

import android.util.Log;

import java.util.List;

import fr.bourgmapper.tub.TubApp;
import fr.bourgmapper.tub.data.repository.DataRepository;
import fr.bourgmapper.tub.presentation.model.LineModel;
import fr.bourgmapper.tub.presentation.ui.activity.BaseActivityLifeCycle;
import fr.bourgmapper.tub.presentation.ui.fragment.LineListFragment;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by axell on 05/11/2016.
 */

public class LineListFragmentPresenter implements BaseActivityLifeCycle {
    private static String TAG = "LineListFragmentPrstr";

    private final LineListFragment view;
    private DataRepository dataRepository;

    public LineListFragmentPresenter(final LineListFragment view) {
        this.view = view;
    }

    @Override
    public void start() {
        this.dataRepository = TubApp.app().getDataRepository();

        this.dataRepository.getAllLinesCall()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<LineModel>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        view.initList(TubApp.app().getDataRepository().getAllLinesCache());
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
