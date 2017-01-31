package fr.bourgmapper.tub.presentation.presenter;

/**
 * Created by axell on 06/11/2016.
 */

public interface Presenter {
    /**
     * Called when the presenter is initialized, this method represents the start of the presenter
     * lifecycle.
     */
    void initialize();

    /**
     * Called when the presenter is resumed. After the initialization and when the presenter comes
     * from a pause state.
     */
    void resume();

    /**
     * Called when the presenter is paused.
     */
    void pause();
}
