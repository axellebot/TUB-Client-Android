package fr.bourgmapper.tub.presentation.internal.di.modules;

import android.support.v4.app.Fragment;

import dagger.Module;
import dagger.Provides;
import fr.bourgmapper.tub.presentation.internal.di.PerFragment;

/**
 * A module to wrap the Fragment state and expose it to the graph.
 */
@Module
public class FragmentModule {
    private final Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    /**
     * Expose the fragment to dependents in the graph.
     */
    @Provides
    @PerFragment
    Fragment fragment() {
        return this.fragment;
    }
}
