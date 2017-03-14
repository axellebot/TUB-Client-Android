package fr.bourgmapper.tub.presentation.internal.di.components;

import dagger.Component;
import fr.bourgmapper.tub.presentation.internal.di.PerActivity;
import fr.bourgmapper.tub.presentation.internal.di.modules.ActivityModule;
import fr.bourgmapper.tub.presentation.internal.di.modules.UserActivityModule;
import fr.bourgmapper.tub.presentation.view.activity.MainActivity;

/**
 * A scope {@link PerActivity} component.
 * Injects line specific Activitys.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, UserActivityModule.class})
public interface UserActivityComponent extends ActivityComponent {

    void inject(MainActivity mainActivity);

}
