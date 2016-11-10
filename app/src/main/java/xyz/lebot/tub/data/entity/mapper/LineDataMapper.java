package xyz.lebot.tub.data.entity.mapper;

import java.util.ArrayList;
import java.util.List;

import xyz.lebot.tub.data.entity.LineEntity;
import xyz.lebot.tub.data.model.LineModel;

/**
 * Created by axell on 04/11/2016.
 */

public class LineDataMapper {
    public LineModel transform(LineEntity lineEntity) {
        LineModel lineModel = new LineModel();
        lineModel.setId(lineEntity.getId());
        lineModel.setLabel(lineEntity.getLabel());
        lineModel.setNumber(lineEntity.getNumber());
        lineModel.setColor(lineEntity.getColor());
        return lineModel;
    }

    public List<LineModel> transform(List<LineEntity> lineEntities) {
        List<LineModel> listModels = new ArrayList<>();
        for (LineEntity lineEntitie : lineEntities) {
            listModels.add(transform(lineEntitie));
        }
        return listModels;
    }
}
