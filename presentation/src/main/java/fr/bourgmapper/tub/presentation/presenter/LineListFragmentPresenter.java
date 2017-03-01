package fr.bourgmapper.tub.presentation.presenter;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import fr.bourgmapper.tub.domain.Line;
import fr.bourgmapper.tub.domain.exception.DefaultErrorBundle;
import fr.bourgmapper.tub.domain.exception.ErrorBundle;
import fr.bourgmapper.tub.domain.interactor.DefaultObserver;
import fr.bourgmapper.tub.domain.interactor.GetLineList;
import fr.bourgmapper.tub.presentation.exception.ErrorMessageFactory;
import fr.bourgmapper.tub.presentation.internal.di.PerActivity;
import fr.bourgmapper.tub.presentation.mapper.LineModelDataMapper;
import fr.bourgmapper.tub.presentation.model.LineModel;
import fr.bourgmapper.tub.presentation.view.LineListView;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class LineListFragmentPresenter implements Presenter {
    private static String TAG = "LineListFragmentPresenter";

    private final GetLineList getLineListUseCase;
    private final LineModelDataMapper lineModelDataMapper;

    private LineListView lineListView;

    @Inject
    LineListFragmentPresenter(GetLineList getLineListUseCase, LineModelDataMapper lineModelDataMapper) {
        this.getLineListUseCase = getLineListUseCase;
        this.lineModelDataMapper = lineModelDataMapper;
    }

    public void setView(@NonNull LineListView view) {
        this.lineListView = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        //this.getLineListUseCase.dispose(); //TODO Dispose from Activity
        this.lineListView = null;
    }

    /**
     * Initializes the presenter by start retrieving the stop list.
     */
    public void initialize() {
        this.loadLineList();
    }

    /**
     * Loads all lines.
     */
    private void loadLineList() {
        this.hideViewRetryLineList();
        this.showViewLoadingLineList();
        this.getLineList();
    }

    public void onLineClicked(LineModel lineModel) {
        this.lineListView.viewLine(lineModel);
    }

    private void showViewLoadingLineList() {
        this.lineListView.showLoadingLineList();
    }

    private void hideViewLoadingLineList() {
        this.lineListView.hideLoadingLineList();
    }

    private void showViewRetryLineList() {
        this.lineListView.showRetryLineList();
    }

    private void hideViewRetryLineList() {
        this.lineListView.hideRetryLineList();
    }

    private void showErrorLineListMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.lineListView.context(),
                errorBundle.getException());
        this.lineListView.showErrorLineList(errorMessage);
    }

    private void showLinesCollectionInView(Collection<Line> lineCollection) {
        final Collection<LineModel> lineModelsCollection =
                this.lineModelDataMapper.transform(lineCollection);
        this.lineListView.renderLineList(lineModelsCollection);
    }

    private void getLineList() {
        this.getLineListUseCase.execute(new LineListObserver(), null);
    }

    private final class LineListObserver extends DefaultObserver<List<Line>> {

        @Override
        public void onComplete() {
            LineListFragmentPresenter.this.hideViewLoadingLineList();
        }

        @Override
        public void onError(Throwable e) {
            LineListFragmentPresenter.this.hideViewLoadingLineList();
            LineListFragmentPresenter.this.showErrorLineListMessage(new DefaultErrorBundle((Exception) e));
            LineListFragmentPresenter.this.showViewRetryLineList();
        }

        @Override
        public void onNext(List<Line> lines) {
            LineListFragmentPresenter.this.showLinesCollectionInView(lines);
        }
    }
}
