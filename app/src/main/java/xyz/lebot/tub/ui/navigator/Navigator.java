package xyz.lebot.tub.ui.navigator;

/**
 * Created by axellebot on 07/11/2016.
 */

public interface Navigator {

    void initLinePart();

    void initStopPart();

    void initMapPart();

    void navigateToPartLine();

    void navigateToPartStop();

    void navigateToPartMap();

    void navigateBack();

    void navigateToLineDetail(String lineId);
}
