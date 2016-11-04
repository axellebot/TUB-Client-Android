package com.axel_nicolas.tub.data.manager;

import com.axel_nicolas.tub.data.model.LineModel;

import java.util.List;

/**
 * Created by axell on 04/11/2016.
 */

public interface CacheManager {
    List<LineModel> getLines();
    void setLines(List<LineModel> lineModels);
}
