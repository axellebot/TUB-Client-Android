package fr.bourgmapper.tub.presentation.ui.activity;

/**
 * Created by axell on 01/02/2017.
 */

public interface BaseActivityLifeCycle {
    void start();

    void resume();

    void pause();

    void stop();

    void destroy();
}