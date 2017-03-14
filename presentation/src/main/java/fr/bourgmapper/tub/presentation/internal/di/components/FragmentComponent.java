package fr.bourgmapper.tub.presentation.internal.di.components;

import dagger.Component;
import fr.bourgmapper.tub.presentation.internal.di.PerFragment;
import fr.bourgmapper.tub.presentation.internal.di.modules.FragmentModule;
import fr.bourgmapper.tub.presentation.view.fragment.BaseFragment;

/**
 * Fragment-level components should extend this component.
 * <p>
 * Subtypes of FragmentComponent should be decorated with annotation:
 * {@link PerFragment}
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
interface FragmentComponent {
    //Exposed to sub-graphs.
    BaseFragment fragment();
}
