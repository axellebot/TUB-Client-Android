package fr.bourgmapper.tub.domain.interactor;

import com.fernandocejas.arrow.checks.Preconditions;

import javax.inject.Inject;

import fr.bourgmapper.tub.domain.Stop;
import fr.bourgmapper.tub.domain.executor.PostExecutionThread;
import fr.bourgmapper.tub.domain.executor.ThreadExecutor;
import fr.bourgmapper.tub.domain.repository.StopRepository;
import io.reactivex.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving data related to an specific {@link Stop}.
 */
public class GetStopDetails extends UseCase<Stop, GetStopDetails.Params> {

    private final StopRepository stopRepository;

    @Inject
    GetStopDetails(StopRepository stopRepository, ThreadExecutor threadExecutor,
                   PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.stopRepository = stopRepository;
    }

    @Override
    Observable<Stop> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params);
        return this.stopRepository.stop(params.stopId);
    }

    public static final class Params {

        private final long stopId;

        private Params(long stopId) {
            this.stopId = stopId;
        }

        public static Params forStop(long stopId) {
            return new Params(stopId);
        }
    }
}
