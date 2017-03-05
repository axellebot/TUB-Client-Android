package fr.bourgmapper.tub.data.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import fr.bourgmapper.tub.data.entity.mapper.LineEntityDataMapper;
import fr.bourgmapper.tub.data.repository.datasource.LineDataStore;
import fr.bourgmapper.tub.data.repository.datasource.LineDataStoreFactory;
import fr.bourgmapper.tub.domain.Line;
import fr.bourgmapper.tub.domain.repository.LineRepository;
import io.reactivex.Observable;

/**
 * {@link LineRepository} for retrieving line data.
 */
@Singleton
public class LineDataRepository implements LineRepository {

    private final LineDataStoreFactory lineDataStoreFactory;
    private final LineEntityDataMapper lineEntityDataMapper;

    /**
     * Constructs a {@link LineRepository}.
     *
     * @param lineDataStoreFactory A factory to construct different data source implementations.
     * @param lineEntityDataMapper {@link LineEntityDataMapper}.
     */
    @Inject
    LineDataRepository(LineDataStoreFactory lineDataStoreFactory, LineEntityDataMapper lineEntityDataMapper) {
        this.lineDataStoreFactory = lineDataStoreFactory;
        this.lineEntityDataMapper = lineEntityDataMapper;
    }

    @Override
    public Observable<List<Line>> lines() {
        //we always get all lines from the cloud
        final LineDataStore lineDataStore = this.lineDataStoreFactory.createCloudDataStore();
        return lineDataStore.lineEntityList().map(this.lineEntityDataMapper::transform);
    }

    @Override
    public Observable<Line> line(long lineId) {
        //TODO : Enable LineDataStore cache after fixing DBFlow
        // final LineDataStore lineDataStore = this.lineDataStoreFactory.create(lineId);
        final LineDataStore lineDataStore = this.lineDataStoreFactory.createCloudDataStore();
        return lineDataStore.lineEntityDetails(lineId).map(this.lineEntityDataMapper::transform);
    }
}
