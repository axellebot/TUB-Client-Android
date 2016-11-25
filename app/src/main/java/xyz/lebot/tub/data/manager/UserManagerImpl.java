package xyz.lebot.tub.data.manager;

import android.app.Application;
import android.content.Context;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

/**
 * Created by axell on 25/11/2016.
 */

public class UserManagerImpl implements UserManager {
    public UserManagerImpl(Application application) {
        FacebookSdk.sdkInitialize(application.getApplicationContext());
        AppEventsLogger.activateApp(application);
    }
}
