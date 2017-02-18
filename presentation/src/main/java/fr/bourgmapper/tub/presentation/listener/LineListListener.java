package fr.bourgmapper.tub.presentation.listener;

import fr.bourgmapper.tub.presentation.model.LineModel;

/**
 * Interface for listening line list events.
 */
public interface LineListListener {
    void onLineClicked(final LineModel lineModel);
}
