package fr.bourgmapper.tub.presentation.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import fr.bourgmapper.tub.domain.Stop;
import fr.bourgmapper.tub.presentation.model.StopModel;

/**
 * Mapper class used to transform {@link Stop} (in the domain layer) to {@link StopModel} in the
 * presentation layer.
 */
public class StopModelDataMapper {

    /**
     * Transform a {@link Stop} into an {@link StopModel}.
     *
     * @param stop Object to be transformed.
     * @return {@link StopModel}.
     */
    public StopModel transform(Stop stop) {
        if (stop == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final StopModel stopModel = new StopModel(stop.getStopId());
        stopModel.setLabel(stop.getLabel());
        stopModel.setLatitude(stop.getLatitude());
        stopModel.setLongitude(stop.getLongitude());

        return stopModel;
    }

    /**
     * Transform a Collection of {@link Stop} into a Collection of {@link StopModel}.
     *
     * @param stopCollection Objects to be transformed.
     * @return List of {@link StopModel}.
     */
    public Collection<StopModel> transform(Collection<Stop> stopCollection) {
        Collection<StopModel> stopModelCollection;

        if (stopCollection != null && !stopCollection.isEmpty()) {
            stopModelCollection = new ArrayList<>();
            for (Stop user : stopCollection) {
                stopModelCollection.add(transform(user));
            }
        } else {
            stopModelCollection = Collections.emptyList();
        }

        return stopModelCollection;
    }
}
