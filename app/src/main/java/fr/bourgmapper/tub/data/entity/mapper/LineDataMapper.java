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
        lineModel.setId(lineEntity.id);
        lineModel.setLabel(lineEntity.label);
        lineModel.setNumber(lineEntity.number);
        lineModel.setColor(lineEntity.color);
        lineModel.setKmlPath(lineEntity.kmlPath);
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
