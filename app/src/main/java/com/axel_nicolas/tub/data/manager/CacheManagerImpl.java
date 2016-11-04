package com.axel_nicolas.tub.data.manager;

import com.axel_nicolas.tub.data.model.LineModel;

import java.util.List;

/**
 * Created by axell on 04/11/2016.
 */

public class CacheManagerImpl implements CacheManager {

    private List<LineModel> lineModels;

    @Override
    public List<LineModel> getLines() {
        return lineModels;
    }

    @Override
    public void setLines(List<LineModel> lineModels) {
        this.lineModels = lineModels;
    }
}
