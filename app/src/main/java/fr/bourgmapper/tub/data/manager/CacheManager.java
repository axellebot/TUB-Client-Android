package fr.bourgmapper.tub.data.manager;

import java.util.List;

import fr.bourgmapper.tub.presentation.model.LineModel;
import fr.bourgmapper.tub.presentation.model.StopModel;

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
