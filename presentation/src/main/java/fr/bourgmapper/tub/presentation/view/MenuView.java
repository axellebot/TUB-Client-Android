package fr.bourgmapper.tub.presentation.view;

import android.content.Context;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a navigation drawer.
 */
public interface MenuView {

    /**
     * Get a {@link Context}.
     */
    Context context();
}
