package fr.bourgmapper.tub.presentation.ui.view;


import java.util.Collection;

import fr.bourgmapper.tub.presentation.model.StopModel;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a list of {@link StopModel}.
 */
public interface StopListView extends LoadDataView {
  /**
   * Render a stop list in the UI.
   *
   * @param stopModelCollection The collection of {@link StopModel} that will be shown.
   */
  void renderStopList(Collection<StopModel> stopModelCollection);

  /**
   * View a {@link StopModel} profile/details.
   *
   * @param stopModel The stop that will be shown.
   */
  void viewStop(StopModel stopModel);
}
