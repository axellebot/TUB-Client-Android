package fr.bourgmapper.tub.domain.interactor;


import java.util.List;

import fr.bourgmapper.tub.domain.Stop;
import fr.bourgmapper.tub.domain.executor.PostExecutionThread;
import fr.bourgmapper.tub.domain.executor.ThreadExecutor;
import fr.bourgmapper.tub.domain.repository.StopRepository;
import io.reactivex.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link Stop}.
 */
public class GetStopList extends UseCase<List<Stop>, Void> {

    private final StopRepository stopRepository;

    GetStopList(StopRepository stopRepository, ThreadExecutor threadExecutor,
                PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.stopRepository = stopRepository;
    }

    @Override
    Observable<List<Stop>> buildUseCaseObservable(Void unused) {
        return this.stopRepository.stops();
    }
}
