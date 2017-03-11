package fr.bourgmapper.tub.presentation.internal.di.components;

import dagger.Component;
import fr.bourgmapper.tub.presentation.internal.di.PerActivity;
import fr.bourgmapper.tub.presentation.internal.di.modules.ActivityModule;
import fr.bourgmapper.tub.presentation.view.activity.BaseActivity;

/**
 * Activity-level components should extend this component.
 * <p>
 * Subtypes of ActivityComponent should be decorated with annotation:
 * {@link PerActivity}
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
interface ActivityComponent {
    //Exposed to sub-graphs.
    BaseActivity activity();
}
