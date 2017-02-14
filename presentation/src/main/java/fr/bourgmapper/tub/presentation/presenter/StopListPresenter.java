package fr.bourgmapper.tub.presentation.presenter;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import fr.bourgmapper.tub.domain.Stop;
import fr.bourgmapper.tub.domain.exception.DefaultErrorBundle;
import fr.bourgmapper.tub.domain.exception.ErrorBundle;
import fr.bourgmapper.tub.domain.interactor.DefaultObserver;
import fr.bourgmapper.tub.domain.interactor.GetStopList;
import fr.bourgmapper.tub.presentation.exception.ErrorMessageFactory;
import fr.bourgmapper.tub.presentation.internal.di.PerActivity;
import fr.bourgmapper.tub.presentation.mapper.StopModelDataMapper;
import fr.bourgmapper.tub.presentation.model.StopModel;
import fr.bourgmapper.tub.presentation.view.StopListView;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class StopListPresenter implements Presenter {
    private static String TAG = "StopListPresenter";

    private StopListView stopListView;

    private final GetStopList getStopListUseCase;
    private final StopModelDataMapper stopModelDataMapper;

    @Inject
    StopListPresenter(GetStopList getStopListUseCase,
                      StopModelDataMapper stopModelDataMapper) {
        this.getStopListUseCase = getStopListUseCase;
        this.stopModelDataMapper = stopModelDataMapper;
    }

    public void setView(@NonNull StopListView view) {
        this.stopListView = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.getStopListUseCase.dispose();
        this.stopListView = null;
    }

    /**
     * Initializes the presenter by start retrieving the stop list.
     */
    public void initialize() {
        this.loadStopList();
    }


    /**
     * Loads all stops.
     */
    private void loadStopList() {
        this.hideViewRetryStopList();
        this.showViewLoadingStopList();
        this.getStopList();
    }

    public void onStopClicked(StopModel stopModel) {
        this.stopListView.viewStop(stopModel);
    }

    private void showViewLoadingStopList() {
        this.stopListView.showLoadingStopList();
    }

    private void hideViewLoadingStopList() {
        this.stopListView.hideLoadingStopList();
    }

    private void showViewRetryStopList() {
        this.stopListView.showRetryStopList();
    }

    private void hideViewRetryStopList() {
        this.stopListView.hideRetryStopList();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.stopListView.context(),
                errorBundle.getException());
        this.stopListView.showErrorStopList(errorMessage);
    }

    private void showStopsCollectionInView(Collection<Stop> stopCollection) {
        final Collection<StopModel> stopModelsCollection =
                this.stopModelDataMapper.transform(stopCollection);
        this.stopListView.renderStopList(stopModelsCollection);
    }

    private void getStopList() {
        this.getStopListUseCase.execute(new StopListObserver(), null);
    }

    private final class StopListObserver extends DefaultObserver<List<Stop>> {

        @Override
        public void onComplete() {
            StopListPresenter.this.hideViewLoadingStopList();
        }

        @Override
        public void onError(Throwable e) {
            StopListPresenter.this.hideViewLoadingStopList();
            StopListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            StopListPresenter.this.showViewRetryStopList();
        }

        @Override
        public void onNext(List<Stop> stops) {
            StopListPresenter.this.showStopsCollectionInView(stops);
        }
    }
}
