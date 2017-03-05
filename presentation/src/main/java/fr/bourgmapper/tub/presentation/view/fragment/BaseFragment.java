package fr.bourgmapper.tub.presentation.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import fr.bourgmapper.tub.presentation.AndroidApplication;
import fr.bourgmapper.tub.presentation.internal.di.HasComponent;
import fr.bourgmapper.tub.presentation.internal.di.components.ApplicationComponent;
import fr.bourgmapper.tub.presentation.internal.di.modules.FragmentModule;

/**
 * Base {@link Fragment} class for every fragment in this application.
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
    }

    /**
     * Shows a {@link android.widget.Toast} message.
     *
     * @param message An string representing a message to be shown.
     */
    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Get the Main Application component for dependency injection.
     *
     * @return {@link ApplicationComponent}
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getActivity().getApplication()).getApplicationComponent();
    }

    /**
     * Get a Fragment module for dependency injection.
     *
     * @return {@link FragmentModule}
     */
    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }
}
