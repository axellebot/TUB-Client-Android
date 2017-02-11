/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.bourgmapper.tub.presentation.internal.di.components;

import dagger.Component;
import fr.bourgmapper.tub.presentation.internal.di.PerActivity;
import fr.bourgmapper.tub.presentation.internal.di.modules.ActivityModule;
import fr.bourgmapper.tub.presentation.internal.di.modules.LineModule;
import fr.bourgmapper.tub.presentation.ui.fragment.LineDetailsFragment;
import fr.bourgmapper.tub.presentation.ui.fragment.LineListFragment;

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
