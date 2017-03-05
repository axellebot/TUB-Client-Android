package fr.bourgmapper.tub.domain.interactor;

import java.util.List;

import javax.inject.Inject;

import fr.bourgmapper.tub.domain.Line;
import fr.bourgmapper.tub.domain.executor.PostExecutionThread;
import fr.bourgmapper.tub.domain.executor.ThreadExecutor;
import fr.bourgmapper.tub.domain.repository.LineRepository;
import io.reactivex.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link Line}.
 */
public class GetLineList extends UseCase<List<Line>, Void> {

    private final LineRepository lineRepository;

    @Inject
    GetLineList(LineRepository lineRepository, ThreadExecutor threadExecutor,
                PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.lineRepository = lineRepository;
    }

    @Override
    Observable<List<Line>> buildUseCaseObservable(Void unused) {
        return this.lineRepository.lines();
    }
}
