package fr.bourgmapper.tub.presentation.internal.di.components;

import dagger.Component;
import fr.bourgmapper.tub.presentation.internal.di.PerActivity;
import fr.bourgmapper.tub.presentation.internal.di.modules.ActivityModule;
import fr.bourgmapper.tub.presentation.internal.di.modules.LineModule;
import fr.bourgmapper.tub.presentation.view.fragment.LineDetailsFragment;
import fr.bourgmapper.tub.presentation.view.fragment.LineListFragment;

/**
 * A scope {@link PerActivity} component.
 * Injects line specific Fragments.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, LineModule.class})
public interface LineComponent extends ActivityComponent {
    void inject(LineListFragment lineListFragment);

    void inject(LineDetailsFragment lineDetailsFragment);
}
