package fr.bourgmapper.tub.data.entity.mapper;

import java.util.ArrayList;
import java.util.List;

import fr.bourgmapper.tub.data.entity.LineEntity;
import fr.bourgmapper.tub.presentation.model.LineModel;

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
        lineModel.setKmlPath(lineEntity.getKmlPath());
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
