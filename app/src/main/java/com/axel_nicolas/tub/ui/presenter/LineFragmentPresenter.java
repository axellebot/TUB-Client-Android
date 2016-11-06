package com.axel_nicolas.tub.ui.presenter;

import android.util.Log;

import com.axel_nicolas.tub.App;
import com.axel_nicolas.tub.data.model.LineModel;
import com.axel_nicolas.tub.ui.fragment.LineFragment;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by axell on 05/11/2016.
 */

public class LineFragmentPresenter implements Presenter {
    private static String TAG = "LineFragmentPresenter";

    private final LineFragment view;

    public LineFragmentPresenter(final LineFragment view) {
        this.view = view;
    }

    @Override
    public void initialize() {
        App.getInstance().getDataRepository().getAllLinesCall()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<LineModel>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        view.initList(App.getInstance().getDataRepository().getAllLinesCache());
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
}
