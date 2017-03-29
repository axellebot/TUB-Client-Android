package fr.bourgmapper.tub.presentation;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.squareup.leakcanary.LeakCanary;

import fr.bourgmapper.tub.presentation.internal.di.components.ApplicationComponent;
import fr.bourgmapper.tub.presentation.internal.di.components.DaggerApplicationComponent;
import fr.bourgmapper.tub.presentation.internal.di.modules.ApplicationModule;

/**
 * Android Main Application
 */
public class AndroidApplication extends MultiDexApplication {
    private ApplicationComponent applicationComponent;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
        this.initializeLeakDetection();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    private void initializeLeakDetection() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }
}
