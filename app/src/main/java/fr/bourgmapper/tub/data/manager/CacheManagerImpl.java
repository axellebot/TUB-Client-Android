package fr.bourgmapper.tub.data.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.bourgmapper.tub.presentation.model.LineModel;
import fr.bourgmapper.tub.presentation.model.StopModel;

/**
 * Created by axell on 04/11/2016.
 */

public class CacheManagerImpl implements CacheManager {

    private HashMap<String, LineModel> lineModels;
    private HashMap<String, StopModel> stopModels;


    public CacheManagerImpl() {
        this.lineModels = new HashMap<>();
        this.stopModels = new HashMap<>();
    }

    @Override
    public List<LineModel> getLines() {
        List<LineModel> lineModels = new ArrayList<>();
        lineModels.addAll(this.lineModels.values());
        return lineModels;
    }


    @Override
    public void setLines(List<LineModel> lineModels) {
        for (LineModel lineModel : lineModels) {
            setLine(lineModel);
        }
    }

    @Override
    public LineModel getLine(String id) {
        return this.lineModels.get(id);
    }

    @Override
    public void setLine(LineModel lineModel) {
        this.lineModels.put(lineModel.getId(), lineModel);
    }


    @Override
    public List<StopModel> getStops() {
        List<StopModel> stopModels = new ArrayList<>();
        stopModels.addAll(this.stopModels.values());
        return stopModels;
    }

    @Override
    public void setStops(List<StopModel> stopModels) {
        for (StopModel stopModel : stopModels) {
            setStop(stopModel);
        }
    }

    @Override
    public StopModel getStop(String id) {
        return this.stopModels.get(id);
    }

    @Override
    public void setStop(StopModel stopModel) {
        this.stopModels.put(stopModel.getId(), stopModel);
    }

}
