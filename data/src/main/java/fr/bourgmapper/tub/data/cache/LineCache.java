package fr.bourgmapper.tub.data.cache;

import fr.bourgmapper.tub.data.entity.LineEntity;

import io.reactivex.Observable;

/**
 * An interface representing a line Cache.
 */
public interface LineCache {
    /**
     * Gets an {@link Observable} which will emit a {@link LineEntity}.
     *
     * @param lineId The line id to retrieve data.
     */
    Observable<LineEntity> get(final String lineId);

    /**
     * Puts and element into the cache.
     *
     * @param lineEntity Element to insert in the cache.
     */
    void put(LineEntity lineEntity);

    /**
     * Checks if an element (Line) exists in the cache.
     *
     * @param lineId The id used to look for inside the cache.
     * @return true if the element is cached, otherwise false.
     */
    boolean isCached(final String lineId);

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
