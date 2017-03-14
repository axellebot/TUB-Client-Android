package fr.bourgmapper.tub.presentation.view;

import android.content.Context;

import fr.bourgmapper.tub.presentation.model.UserModel;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a navigation drawer.
 */
public interface HomeView {

    /**
     * Rendering User info in
     * hamburger menu
     */
    void renderUser(UserModel user);

    /**
     * Open the menu.
     */
    void openMenu();

    /**
     * Close the menu.
     */
    void closeMenu();

    /**
     * Display Map
     */
    void displayMap();

    /**
     * Display Info
     */
    void displayInfo();

    /**
     * Display Line List
     */
    void displayLineList();

    /**
     * Display Stop List
     */
    void displayStopList();

    /**
     * Get a {@link Context}.
     */
    Context context();
}
