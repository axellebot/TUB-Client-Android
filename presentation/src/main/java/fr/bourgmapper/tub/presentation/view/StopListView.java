package fr.bourgmapper.tub.presentation.view;


import android.content.Context;

import java.util.Collection;

import fr.bourgmapper.tub.presentation.model.StopModel;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a list of {@link StopModel}.
 */
public interface StopListView {
    /**
     * Render a stop list in the UI.
     *
     * @param stopModelCollection The collection of {@link StopModel} that will be shown.
     */
    void renderStopList(Collection<StopModel> stopModelCollection);

    /**
     * View a {@link StopModel} profile/details.
     *
     * @param stopModel The stop that will be shown.
     */
    void viewStop(StopModel stopModel);

    /**
     * Show a view with a progress bar indicating a loading process.
     */
    void showLoadingStopList();

    /**
     * Hide a loading view.
     */
    void hideLoadingStopList();

    /**
     * Show a retry view in case of an error when retrieving data.
     */
    void showRetryStopList();

    /**
     * Hide a retry view shown if there was an error when retrieving data.
     */
    void hideRetryStopList();

    /**
     * Show an error message
     *
     * @param message A string representing an error.
     */
    void showErrorStopList(String message);

    /**
     * Get a {@link android.content.Context}.
     */
    Context context();
}
