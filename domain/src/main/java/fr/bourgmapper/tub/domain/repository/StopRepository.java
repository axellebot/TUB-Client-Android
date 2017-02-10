package fr.bourgmapper.tub.domain.repository;

import java.util.List;

import fr.bourgmapper.tub.domain.Stop;
import io.reactivex.Observable;

/**
 * Interface that represents a Repository for getting {@link Stop} related data.
 */
public interface StopRepository {
    /**
     * Get an {@link Observable} which will emit a List of {@link Stop}.
     */
    Observable<List<Stop>> stops();

    /**
     * Get an {@link Observable} which will emit a {@link Stop}.
     *
     * @param stopId The user id used to retrieve user data.
     */
    Observable<Stop> stop(final String stopId);
}
