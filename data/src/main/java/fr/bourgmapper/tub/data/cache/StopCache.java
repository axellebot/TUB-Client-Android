package fr.bourgmapper.tub.data.cache;

import fr.bourgmapper.tub.data.entity.StopEntity;
import io.reactivex.Observable;

/**
 * An interface representing a stop Cache.
 */
public interface StopCache {
    /**
     * Gets an {@link Observable} which will emit a {@link StopEntity}.
     *
     * @param stopId The stop id to retrieve data.
     */
    Observable<StopEntity> get(final String stopId);

    /**
     * Puts and element into the cache.
     *
     * @param stopEntity Element to insert in the cache.
     */
    void put(StopEntity stopEntity);

    /**
     * Checks if an element (Stop) exists in the cache.
     *
     * @param stopId The id used to look for inside the cache.
     * @return true if the element is cached, otherwise false.
     */
    boolean isCached(final String stopId);

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
