package xyz.lebot.tub.ui.presenter;

import android.util.Log;

import xyz.lebot.tub.App;
import xyz.lebot.tub.data.model.LineModel;
import xyz.lebot.tub.ui.fragment.LineFragment;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import xyz.lebot.tub.ui.navigator.Navigator;

/**
 * Created by axell on 05/11/2016.
 */

public class LineFragmentPresenter implements Presenter {
    private static String TAG = "LineFragmentPresenter";

    private final LineFragment view;
    private final Navigator navigator;

    public LineFragmentPresenter(final LineFragment view,final Navigator navigator) {
        this.view = view;
        this.navigator = navigator;
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
