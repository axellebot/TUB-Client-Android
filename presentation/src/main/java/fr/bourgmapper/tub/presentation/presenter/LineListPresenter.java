package fr.bourgmapper.tub.presentation.presenter;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import fr.bourgmapper.tub.data.repository.DataRepository;
import fr.bourgmapper.tub.presentation.AndroidApplication;
import fr.bourgmapper.tub.presentation.internal.di.PerActivity;
import fr.bourgmapper.tub.presentation.model.LineModel;
import fr.bourgmapper.tub.presentation.view.fragment.LineListFragment;
import rx.Observer;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class LineListPresenter implements Presenter {
    private static String TAG = "LineListPresenter";

    private final LineListFragment view;
    private DataRepository dataRepository;

    @Inject
    public LineListPresenter(final LineListFragment view) {
        this.view = view;
    }

    @Override
    public void start() {
        this.dataRepository = AndroidApplication.app().getDataRepository();

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
