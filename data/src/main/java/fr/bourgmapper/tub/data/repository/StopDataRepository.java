package fr.bourgmapper.tub.data.repository;

import java.util.List;

import fr.bourgmapper.tub.data.entity.mapper.StopEntityDataMapper;
import fr.bourgmapper.tub.domain.Stop;
import fr.bourgmapper.tub.domain.repository.StopRepository;
import io.reactivex.Observable;


/**
 * {@link StopRepository} for retrieving stop data.
 */
public class StopDataRepository implements StopRepository {

    private final StopEntityDataMapper stopEntityDataMapper;


    /**
     * Constructs a {@link StopRepository}.
     *
     * @param stopEntityDataMapper {@link StopEntityDataMapper}.
     */
    public StopDataRepository(StopEntityDataMapper stopEntityDataMapper) {
        this.stopEntityDataMapper = stopEntityDataMapper;
    }


    @Override
    public Observable<List<Stop>> stops() {
        return null;
    }

    @Override
    public Observable<Stop> stop(String stopId) {
        return null;
    }
}
