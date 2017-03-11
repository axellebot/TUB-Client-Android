package fr.bourgmapper.tub.presentation.internal.di.components;

import dagger.Component;
import fr.bourgmapper.tub.presentation.internal.di.PerFragment;
import fr.bourgmapper.tub.presentation.internal.di.modules.CoreFragmentModule;
import fr.bourgmapper.tub.presentation.internal.di.modules.FragmentModule;
import fr.bourgmapper.tub.presentation.view.fragment.InfoFragment;
import fr.bourgmapper.tub.presentation.view.fragment.LineDetailsFragment;
import fr.bourgmapper.tub.presentation.view.fragment.LineListFragment;
import fr.bourgmapper.tub.presentation.view.fragment.MapFragment;
import fr.bourgmapper.tub.presentation.view.fragment.StopListFragment;

/**
 * A scope {@link PerFragment} component.
 * Injects line specific Fragments.
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = {FragmentModule.class, CoreFragmentModule.class})
public interface CoreFragmentComponent extends FragmentComponent {

    void inject(InfoFragment infoFragment);

    void inject(MapFragment mapFragment);

    void inject(StopListFragment stopListFragment);

    void inject(LineListFragment lineListFragment);

    void inject(LineDetailsFragment lineDetailsFragment);
}
