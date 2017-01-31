package fr.bourgmapper.tub.presentation.presenter;

import android.util.Log;

import fr.bourgmapper.tub.TubApp;
import fr.bourgmapper.tub.presentation.model.LineModel;
import fr.bourgmapper.tub.data.repository.DataRepository;
import fr.bourgmapper.tub.presentation.ui.fragment.LineDetailFragment;
import fr.bourgmapper.tub.presentation.navigator.Navigator;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by axell on 05/11/2016.
 */

public class LineDetailFragmentPresenter implements Presenter {
    private static String TAG = "LineDetailPresenter";

    private final LineDetailFragment view;
    private final Navigator navigator;
    private DataRepository dataRepository;

    public LineDetailFragmentPresenter(final LineDetailFragment view, final Navigator navigator) {

        this.dataRepository = TubApp.getInstance().getDataRepository();

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
        this.dataRepository.getLineCall(lineId)
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
