package fr.bourgmapper.tub.presentation.view;

import android.content.Context;

import java.io.InputStream;
import java.util.Collection;

import fr.bourgmapper.tub.presentation.model.LineModel;
import fr.bourgmapper.tub.presentation.model.StopModel;

public interface MainMapView {
    /**
     * Render a map view in the UI.
     *
     * @param stopModelCollection The collection of {@link StopModel} that will be shown.
     */
    void renderStopList(Collection<StopModel> stopModelCollection);

    /**
     * Render a map view in the UI.
     *
     * @param lineModelCollection The collection of {@link LineModel} that will be shown.
     */
    void renderLineList(Collection<LineModel> lineModelCollection);

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
