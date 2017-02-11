package fr.bourgmapper.tub.presentation.presenter;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import fr.bourgmapper.tub.data.repository.DataRepository;
import fr.bourgmapper.tub.presentation.AndroidApplication;
import fr.bourgmapper.tub.presentation.internal.di.PerActivity;
import fr.bourgmapper.tub.presentation.model.StopModel;
import fr.bourgmapper.tub.presentation.ui.fragment.StopListFragment;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class StopListFragmentPresenter implements Presenter {
    private static String TAG = "StopListFragmentPrstr";

    private final StopListFragment view;
    private DataRepository dataRepository;

    @Inject
    public StopListFragmentPresenter(final StopListFragment view) {
        this.view = view;
    }

    @Override
    public void start() {
        this.dataRepository = AndroidApplication.app().getDataRepository();

        this.dataRepository.getStopListCall()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<StopModel>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
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
