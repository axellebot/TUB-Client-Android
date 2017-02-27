/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.bourgmapper.tub.data.repository.datasource;

import java.util.List;

import fr.bourgmapper.tub.data.cache.StopCache;
import fr.bourgmapper.tub.data.entity.StopEntity;
import fr.bourgmapper.tub.data.net.RestApi;
import io.reactivex.Observable;

/**
 * {@link StopDataStore} implementation based on connections to the api (Cloud).
 */
class CloudStopDataStore implements StopDataStore {

    private final RestApi restApi;
    private final StopCache stopCache;

    /**
     * Construct a {@link StopDataStore} based on connections to the api (Cloud).
     *
     * @param restApi The {@link RestApi} implementation to use.
     * @param stopCache A {@link StopCache} to cache data retrieved from the api.
     */
    CloudStopDataStore(RestApi restApi, StopCache stopCache) {
        this.restApi = restApi;
        this.stopCache = stopCache;
    }

    @Override
    public Observable<List<StopEntity>> stopEntityList() {
        return this.restApi.stopEntityList();
    }

    @Override
    public Observable<StopEntity> stopEntityDetails(final String stopId) {
        return this.restApi.stopEntityById(stopId).doOnNext(CloudStopDataStore.this.stopCache::put);
    }
}
