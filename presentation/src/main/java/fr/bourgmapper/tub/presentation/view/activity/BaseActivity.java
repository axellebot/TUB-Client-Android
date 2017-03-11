package fr.bourgmapper.tub.presentation.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import fr.bourgmapper.tub.presentation.AndroidApplication;
import fr.bourgmapper.tub.presentation.internal.di.components.ApplicationComponent;
import fr.bourgmapper.tub.presentation.internal.di.modules.ActivityModule;

/**
 * Base {@link AppCompatActivity} class for every Activity in this application.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationComponent().inject(this);
    }


    /**
     * Get the Main Application component for dependency injection.
     *
     * @return {@link ApplicationComponent}
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    /**
     * Get a Activity module for dependency injection.
     *
     * @return {@link ActivityModule}
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

}