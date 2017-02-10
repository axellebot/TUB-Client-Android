package fr.bourgmapper.tub.domain.repository;

import java.util.List;

import fr.bourgmapper.tub.domain.Line;
import io.reactivex.Observable;

/**
 * Interface that represents a Repository for getting {@link Line} related data.
 */
public interface LineRepository {
    /**
     * Get an {@link Observable} which will emit a List of {@link Line}.
     */
    Observable<List<Line>> lines();

    /**
     * Get an {@link Observable} which will emit a {@link Line}.
     *
     * @param lineId The user id used to retrieve user data.
     */
    Observable<Line> line(final String lineId);
}
