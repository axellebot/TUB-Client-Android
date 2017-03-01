package fr.bourgmapper.tub.presentation.presenter;

import javax.inject.Inject;

import fr.bourgmapper.tub.domain.interactor.GetLineDetails;
import fr.bourgmapper.tub.domain.interactor.GetLineList;
import fr.bourgmapper.tub.domain.interactor.GetStopDetails;
import fr.bourgmapper.tub.domain.interactor.GetStopList;

public class MainActivityPresenter implements Presenter {

    private final GetLineList getLineListUseCase;
    private final GetLineDetails getLineDetailsUseCase;
    private final GetStopList getStopListUseCase;
    private final GetStopDetails getStopDetailsUseCase;

    @Inject
    public MainActivityPresenter(GetLineList getLineListUseCase, GetLineDetails getLineDetailsUseCase, GetStopList getStopListUseCase, GetStopDetails getStopDetailsUseCase) {
        this.getLineListUseCase = getLineListUseCase;
        this.getLineDetailsUseCase = getLineDetailsUseCase;
        this.getStopListUseCase = getStopListUseCase;
        this.getStopDetailsUseCase = getStopDetailsUseCase;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.getLineListUseCase.dispose();
        this.getLineDetailsUseCase.dispose();
        this.getStopListUseCase.dispose();
        this.getStopDetailsUseCase.dispose();
    }
}
