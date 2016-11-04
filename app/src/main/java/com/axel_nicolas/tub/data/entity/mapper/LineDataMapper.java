package com.axel_nicolas.tub.data.entity.mapper;

import com.axel_nicolas.tub.data.entity.LineEntity;
import com.axel_nicolas.tub.data.model.LineModel;

import java.util.ArrayList;
import java.util.List;

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

    public List<LineModel>transform(List<LineEntity> lineEntities){
        List<LineModel> listModels = new ArrayList<>();
        for(LineEntity lineEntitie : lineEntities){
            listModels.add(transform(lineEntitie));
        }
        return  listModels;
    }
}
