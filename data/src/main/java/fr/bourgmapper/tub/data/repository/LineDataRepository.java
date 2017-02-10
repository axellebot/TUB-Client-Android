package fr.bourgmapper.tub.data.repository;

import java.util.List;

import fr.bourgmapper.tub.data.entity.mapper.LineEntityDataMapper;
import fr.bourgmapper.tub.domain.Line;
import fr.bourgmapper.tub.domain.repository.LineRepository;
import io.reactivex.Observable;

/**
 * {@link LineRepository} for retrieving line data.
 */
public class LineDataRepository implements LineRepository {

    private final LineEntityDataMapper lineEntityDataMapper;

    /**
     * Constructs a {@link LineRepository}.
     *
     * @param lineEntityDataMapper {@link LineEntityDataMapper}.
     */
    public LineDataRepository(LineEntityDataMapper lineEntityDataMapper) {
        this.lineEntityDataMapper = lineEntityDataMapper;
    }


    @Override
    public Observable<List<Line>> lines() {
        return null;
    }

    @Override
    public Observable<Line> line(String lineId) {
        return null;
    }
}
