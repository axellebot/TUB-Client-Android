package com.axel_nicolas.tub.data.manager;

import com.axel_nicolas.tub.data.model.LineModel;
import com.axel_nicolas.tub.data.model.StopModel;

import java.util.List;

/**
 * Created by axell on 04/11/2016.
 */

public interface CacheManager {
    List<LineModel> getLines();

    void setLines(List<LineModel> lineModels);

    LineModel getLine(String id);

    void setLine(LineModel lineModel);

    List<StopModel> getStops();

    void setStops(List<StopModel> stopModels);

    StopModel getStop(String id);

    void setStop(StopModel stopModel);
}
