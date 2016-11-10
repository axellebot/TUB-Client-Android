package xyz.lebot.tub.ui.presenter;

import android.util.Log;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import xyz.lebot.tub.App;
import xyz.lebot.tub.data.model.LineModel;
import xyz.lebot.tub.ui.fragment.LineDetailFragment;
import xyz.lebot.tub.ui.navigator.Navigator;

/**
 * Created by axell on 05/11/2016.
 */

public class LineDetailFragmentPresenter implements Presenter {
    private static String TAG = "LineDetailPresenter";

    private final LineDetailFragment view;
    private final Navigator navigator;

    public LineDetailFragmentPresenter(final LineDetailFragment view, final Navigator navigator) {
        this.view = view;
        this.navigator = navigator;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }
    public void initView(String lineId){
        App.getInstance().getDataRepository().getLineCall(lineId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LineModel>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();

                    }

                    @Override
                    public void onNext(LineModel lineModel) {
                        Log.i(TAG, lineModel.toString());
                        view.initView(lineModel);
                    }
                });
    }
}
