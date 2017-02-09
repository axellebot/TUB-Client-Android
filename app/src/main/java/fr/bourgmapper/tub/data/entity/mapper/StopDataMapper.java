package fr.bourgmapper.tub.data.entity.mapper;

import java.util.ArrayList;
import java.util.List;

import fr.bourgmapper.tub.data.entity.StopEntity;
import fr.bourgmapper.tub.presentation.model.StopModel;

/**
 * Created by axell on 04/11/2016.
 */

public class StopDataMapper {
    public StopModel transform(StopEntity stopEntity) {
        StopModel stopModel = new StopModel();
        stopModel.setId(stopEntity.getId());
        stopModel.setLabel(stopEntity.getLabel());
        stopModel.setLatitude(stopEntity.getLatitude());
        stopModel.setLongitude(stopEntity.getLongitude());
        return stopModel;
    }

    public List<StopModel> transform(List<StopEntity> stopEntities) {
        List<StopModel> stopModels = new ArrayList<>();
        for (StopEntity stopEntity : stopEntities) {
            stopModels.add(transform(stopEntity));
        }
        return stopModels;
    }
}
