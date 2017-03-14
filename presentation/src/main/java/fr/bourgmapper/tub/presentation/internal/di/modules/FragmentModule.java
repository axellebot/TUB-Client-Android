package fr.bourgmapper.tub.presentation.internal.di.modules;

import dagger.Module;
import dagger.Provides;
import fr.bourgmapper.tub.presentation.internal.di.PerFragment;
import fr.bourgmapper.tub.presentation.view.fragment.BaseFragment;

/**
 * A module to wrap the Fragment state and expose it to the graph.
 */
@Module
public class FragmentModule {
    private final BaseFragment fragment;

    public FragmentModule(BaseFragment fragment) {
        this.fragment = fragment;
    }

    /**
     * Expose the fragment to dependents in the graph.
     */
    @Provides
    @PerFragment
    BaseFragment fragment() {
        return this.fragment;
    }
}
