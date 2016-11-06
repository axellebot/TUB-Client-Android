package xyz.lebot.tub.data.entity.mapper;

import xyz.lebot.tub.data.entity.StopEntity;
import xyz.lebot.tub.data.model.StopModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by axell on 04/11/2016.
 */

public class StopDataMapper {
    public StopModel transform(StopEntity stopEntity) {
        StopModel stopModel = new StopModel();
        stopModel.setId(stopEntity.getId());
        stopModel.setName(stopEntity.getName());
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
