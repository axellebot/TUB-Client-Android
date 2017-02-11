package fr.bourgmapper.tub.presentation.internal.di.components;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import fr.bourgmapper.tub.domain.executor.PostExecutionThread;
import fr.bourgmapper.tub.domain.executor.ThreadExecutor;
import fr.bourgmapper.tub.domain.repository.LineRepository;
import fr.bourgmapper.tub.domain.repository.StopRepository;
import fr.bourgmapper.tub.presentation.internal.di.modules.ApplicationModule;
import fr.bourgmapper.tub.presentation.view.activity.BaseActivity;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
  void inject(BaseActivity baseActivity);

  //Exposed to sub-graphs.
  Context context();
  ThreadExecutor threadExecutor();
  PostExecutionThread postExecutionThread();
  LineRepository lineRepository();
  StopRepository stopRepository();
}
