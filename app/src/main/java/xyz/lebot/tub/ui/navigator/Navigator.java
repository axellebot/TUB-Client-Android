package xyz.lebot.tub.ui.navigator;

/**
 * Created by axellebot on 07/11/2016.
 */

public interface Navigator {
    void initMapPart();

    void initLinePart();

    void initStopPart();

    void navigateToPartMap();

    void navigateToPartLine();

    void navigateToPartStop();

    void navigateBack();

    void navigateToLineDetail(String lineId);
}
