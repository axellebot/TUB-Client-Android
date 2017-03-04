package fr.bourgmapper.tub.data.cache;

import java.util.Collection;

import fr.bourgmapper.tub.data.entity.StopEntity;
import io.reactivex.Observable;

/**
 * An interface representing a stop Cache.
 */
public interface StopCache {

    /**
     * Gets an {@link Observable} which will emit a {@link StopEntity}.
     *
     * @param stopId The stop stopId to retrieve data.
     */
    Observable<StopEntity> get(final String stopId);

    /**
     * Puts and element into the cache.
     *
     * @param stopEntity Element to insert in the cache.
     */
    void put(StopEntity stopEntity);

    /**
     * Puts and elements into the cache.
     *
     * @param stopEntityList Elements to insert in the cache.
     */
    void put(Collection<StopEntity> stopEntityList);

    /**
     * Checks if an element (Stop) exists in the cache.
     *
     * @param stopId The stopId used to look for inside the cache.
     * @return true if the element is cached, otherwise false.
     */
    boolean isCached(final long stopId);

    /**
     * Checks if the cache is expired.
     *
     * @return true, the cache is expired, otherwise false.
     */
    boolean isExpired();

    /**
     * Evict all elements of the cache.
     */
    void evictAll();
}
