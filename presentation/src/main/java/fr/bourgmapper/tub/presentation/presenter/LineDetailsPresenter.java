package fr.bourgmapper.tub.presentation.presenter;

import android.util.Log;

import javax.inject.Inject;

import fr.bourgmapper.tub.data.repository.DataRepository;
import fr.bourgmapper.tub.presentation.AndroidApplication;
import fr.bourgmapper.tub.presentation.internal.di.PerActivity;
import fr.bourgmapper.tub.presentation.model.LineModel;
import fr.bourgmapper.tub.presentation.view.fragment.LineDetailsFragment;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class LineDetailsPresenter implements Presenter {
    private static String TAG = "LineDetailPresenter";

    private final LineDetailsFragment view;
    private DataRepository dataRepository;

    @Inject
    public LineDetailsPresenter(final LineDetailsFragment view) {
        this.dataRepository = AndroidApplication.app().getDataRepository();
        this.view = view;
    }

    @Override
    public void start() {

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

    public void initView(String lineId) {
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
