package fr.bourgmapper.tub.presentation.internal.di.components;

import dagger.Component;
import fr.bourgmapper.tub.presentation.internal.di.PerActivity;
import fr.bourgmapper.tub.presentation.internal.di.modules.ActivityModule;
import fr.bourgmapper.tub.presentation.internal.di.modules.LineModule;
import fr.bourgmapper.tub.presentation.internal.di.modules.StopModule;
import fr.bourgmapper.tub.presentation.view.fragment.MapFragment;

/**
 * A scope {@link PerActivity} component.
 * Injects stop specific Fragments.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, StopModule.class, LineModule.class})
public interface MapComponent extends ActivityComponent {
    void inject(MapFragment mapFragment);
}
