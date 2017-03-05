package fr.bourgmapper.tub.presentation.view;


import android.content.Context;

import java.util.Collection;

import fr.bourgmapper.tub.presentation.model.LineModel;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a list of {@link LineModel}.
 */
public interface LineListView {
    /**
     * Render a line list in the UI.
     *
     * @param lineModelCollection The collection of {@link LineModel} that will be shown.
     */
    void renderLineList(Collection<LineModel> lineModelCollection);

    /**
     * View a {@link LineModel} profile/details.
     *
     * @param lineModel The line that will be shown.
     */
    void viewLine(LineModel lineModel);

    /**
     * Show a view with a progress bar indicating a loading process.
     */
    void showLoadingLineList();

    /**
     * Hide a loading view.
     */
    void hideLoadingLineList();

    /**
     * Show a retry view in case of an error when retrieving data.
     */
    void showRetryLineList();

    /**
     * Hide a retry view shown if there was an error when retrieving data.
     */
    void hideRetryLineList();

    /**
     * Show an error message
     *
     * @param message A string representing an error.
     */
    void showErrorLineList(String message);

    /**
     * Get a {@link Context}.
     */
    Context context();
}
