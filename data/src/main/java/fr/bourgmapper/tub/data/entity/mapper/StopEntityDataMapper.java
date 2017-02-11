package fr.bourgmapper.tub.data.entity.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import fr.bourgmapper.tub.data.entity.StopEntity;
import fr.bourgmapper.tub.domain.Stop;

/**
 * Mapper class used to transform {@link StopEntity} (in the data layer) to {@link Stop} in the
 * domain layer.
 */
@Singleton
public class StopEntityDataMapper {
    @Inject
    StopEntityDataMapper() {
    }

    public Stop transform(StopEntity stopEntity) {
        Stop stopModel = new Stop(stopEntity.stopId);
        stopModel.setLabel(stopEntity.label);
        stopModel.setLatitude(stopEntity.latitude);
        stopModel.setLongitude(stopEntity.longitude);
        return stopModel;
    }

    public List<Stop> transform(List<StopEntity> stopEntities) {
        List<Stop> stopModels = new ArrayList<>();
        for (StopEntity stopEntity : stopEntities) {
            stopModels.add(transform(stopEntity));
        }
        return stopModels;
    }
}
