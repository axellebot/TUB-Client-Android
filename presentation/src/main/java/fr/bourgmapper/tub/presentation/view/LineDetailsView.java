package fr.bourgmapper.tub.presentation.view;

import android.content.Context;

import fr.bourgmapper.tub.presentation.model.LineModel;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a line profile.
 */
public interface LineDetailsView {
    /**
     * Render a line in the UI.
     *
     * @param line The {@link LineModel} that will be shown.
     */
    void renderLine(LineModel line);

    /**
     * Show a view with a progress bar indicating a loading process.
     */
    void showLoadingLineDetails();

    /**
     * Hide a loading view.
     */
    void hideLoadingLineDetails();

    /**
     * Show a retry view in case of an error when retrieving data.
     */
    void showRetryLineDetails();

    /**
     * Hide a retry view shown if there was an error when retrieving data.
     */
    void hideRetryLineDetails();

    /**
     * Show an error message
     *
     * @param message A string representing an error.
     */
    void showErrorLineDetails(String message);

    /**
     * Get a {@link Context}.
     */
    Context context();
}
