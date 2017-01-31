package fr.bourgmapper.tub.presentation.presenter;

import android.util.Log;

import java.util.List;

import fr.bourgmapper.tub.TubApp;
import fr.bourgmapper.tub.presentation.model.LineModel;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import fr.bourgmapper.tub.data.repository.DataRepository;
import fr.bourgmapper.tub.presentation.ui.fragment.LineFragment;
import fr.bourgmapper.tub.presentation.navigator.Navigator;

/**
 * Created by axell on 05/11/2016.
 */

public class LineFragmentPresenter implements Presenter {
    private static String TAG = "LineFragmentPresenter";

    private final LineFragment view;
    private final Navigator navigator;
    private DataRepository dataRepository;

    public LineFragmentPresenter(final LineFragment view, final Navigator navigator) {
        this.view = view;
        this.navigator = navigator;
    }

    @Override
    public void initialize() {
        this.dataRepository= TubApp.getInstance().getDataRepository();

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
                        view.initList(TubApp.getInstance().getDataRepository().getAllLinesCache());
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

    public void onLineItemClick(String lineId) {
        navigator.navigateToLineDetail(lineId);
    }
}
