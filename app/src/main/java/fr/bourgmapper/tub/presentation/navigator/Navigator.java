package fr.bourgmapper.tub.presentation.navigator;

/**
 * Created by axellebot on 07/11/2016.
 */

public interface Navigator {
    void initPartHome();

    void initPartLine();

    void initPartStop();

    void navigateToPartHome();

    void navigateToPartLine();

    void navigateToPartStop();

    void navigateBack();

    void navigateToLineDetail(String lineId);
}
