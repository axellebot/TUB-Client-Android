package fr.bourgmapper.tub.data.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import fr.bourgmapper.tub.data.entity.mapper.StopEntityDataMapper;
import fr.bourgmapper.tub.data.repository.datasource.StopDataStore;
import fr.bourgmapper.tub.data.repository.datasource.StopDataStoreFactory;
import fr.bourgmapper.tub.domain.Stop;
import fr.bourgmapper.tub.domain.repository.StopRepository;
import io.reactivex.Observable;

/**
 * {@link StopRepository} for retrieving stop data.
 */
@Singleton
public class StopDataRepository implements StopRepository {

    private final StopDataStoreFactory stopDataStoreFactory;
    private final StopEntityDataMapper stopEntityDataMapper;

    /**
     * Constructs a {@link StopRepository}.
     *
     * @param stopDataStoreFactory A factory to construct different data source implementations.
     * @param stopEntityDataMapper {@link StopEntityDataMapper}.
     */
    @Inject
    StopDataRepository(StopDataStoreFactory stopDataStoreFactory, StopEntityDataMapper stopEntityDataMapper) {
        this.stopDataStoreFactory = stopDataStoreFactory;
        this.stopEntityDataMapper = stopEntityDataMapper;
    }


    @Override
    public Observable<List<Stop>> stops() {
        //we always get all stops from the cloud
        final StopDataStore stopDataStore = this.stopDataStoreFactory.createCloudDataStore();
        return stopDataStore.stopEntityList().map(this.stopEntityDataMapper::transform);
    }

    @Override
    public Observable<Stop> stop(String stopId) {
        final StopDataStore stopDataStore = this.stopDataStoreFactory.create(stopId);
        return stopDataStore.stopEntityDetails(stopId).map(this.stopEntityDataMapper::transform);
    }
}
