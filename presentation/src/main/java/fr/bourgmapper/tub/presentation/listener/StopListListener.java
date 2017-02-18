package fr.bourgmapper.tub.presentation.listener;

import fr.bourgmapper.tub.presentation.model.StopModel;

/**
 * Interface for listening Stop list events.
 */
public interface StopListListener {
    void onStopClicked(final StopModel userModel);
}