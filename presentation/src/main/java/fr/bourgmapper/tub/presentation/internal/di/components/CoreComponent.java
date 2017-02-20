package fr.bourgmapper.tub.presentation.internal.di.components;

import dagger.Component;
import fr.bourgmapper.tub.presentation.internal.di.PerActivity;
import fr.bourgmapper.tub.presentation.internal.di.modules.ActivityModule;
import fr.bourgmapper.tub.presentation.internal.di.modules.CoreModule;
import fr.bourgmapper.tub.presentation.view.fragment.LineDetailsFragment;
import fr.bourgmapper.tub.presentation.view.fragment.LineListFragment;
import fr.bourgmapper.tub.presentation.view.fragment.MapFragment;
import fr.bourgmapper.tub.presentation.view.fragment.StopListFragment;

/**
 * A scope {@link PerActivity} component.
 * Injects line specific Fragments.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, CoreModule.class})
public interface CoreComponent extends ActivityComponent {
    void inject(MapFragment mapFragment);

    void inject(StopListFragment stopListFragment);

    void inject(LineListFragment lineListFragment);

    void inject(LineDetailsFragment lineDetailsFragment);
}
