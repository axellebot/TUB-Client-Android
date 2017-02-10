/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.bourgmapper.tub.data.repository.datasource;

import java.util.List;

import fr.bourgmapper.tub.data.cache.LineCache;
import fr.bourgmapper.tub.data.entity.LineEntity;
import fr.bourgmapper.tub.data.net.RestApi;
import io.reactivex.Observable;

/**
 * {@link LineDataStore} implementation based on connections to the api (Cloud).
 */
class CloudLineDataStore implements LineDataStore {

  private final RestApi restApi;
  private final LineCache lineCache;

  /**
   * Construct a {@link LineDataStore} based on connections to the api (Cloud).
   *
   * @param restApi The {@link RestApi} implementation to use.
   * @param lineCache A {@link LineCache} to cache data retrieved from the api.
   */
  CloudLineDataStore(RestApi restApi, LineCache lineCache) {
    this.restApi = restApi;
    this.lineCache = lineCache;
  }

  @Override
  public Observable<List<LineEntity>> lineEntityList() {
    return this.restApi.lineEntityList();
  }

  @Override
  public Observable<LineEntity> lineEntityDetails(final String lineId) {
    return this.restApi.lineEntityById(lineId).doOnNext(CloudLineDataStore.this.lineCache::put);
  }
}
