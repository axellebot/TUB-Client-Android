package fr.bourgmapper.tub.presentation.internal.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fr.bourgmapper.tub.data.cache.LineCache;
import fr.bourgmapper.tub.data.cache.LineCacheImpl;
import fr.bourgmapper.tub.data.cache.StopCache;
import fr.bourgmapper.tub.data.cache.StopCacheImpl;
import fr.bourgmapper.tub.data.executor.JobExecutor;
import fr.bourgmapper.tub.data.repository.LineDataRepository;
import fr.bourgmapper.tub.data.repository.StopDataRepository;
import fr.bourgmapper.tub.domain.executor.PostExecutionThread;
import fr.bourgmapper.tub.domain.executor.ThreadExecutor;
import fr.bourgmapper.tub.domain.repository.LineRepository;
import fr.bourgmapper.tub.domain.repository.StopRepository;
import fr.bourgmapper.tub.presentation.AndroidApplication;
import fr.bourgmapper.tub.presentation.UIThread;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {
    private final AndroidApplication application;

    public ApplicationModule(AndroidApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    LineCache provideLineCache(LineCacheImpl lineCache) {
        return lineCache;
    }

    @Provides
    @Singleton
    LineRepository provideLineRepository(LineDataRepository lineDataRepository) {
        return lineDataRepository;
    }

    @Provides
    @Singleton
    StopCache provideStopCache(StopCacheImpl stopCache) {
        return stopCache;
    }

    @Provides
    @Singleton
    StopRepository provideStopRepository(StopDataRepository stopDataRepository) {
        return stopDataRepository;
    }
}
