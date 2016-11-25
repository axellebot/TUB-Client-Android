package xyz.lebot.tub.data.manager;

import android.app.Application;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import rx.Observable;

/**
 * Created by axell on 25/11/2016.
 */

public class UserManagerImpl implements UserManager {
    private Application application;

    //facebook
    private CallbackManager facebookCallbackManager;
    private AccessTokenTracker facebookAccessTokenTracker;
    private AccessToken facebookAccessToken;
    private ProfileTracker facebbokProfileTracker;
    private Profile facebookProfile;

    public UserManagerImpl(Application application) {
        this.application = application;

        initFacebook();
    }

    private void initFacebook() {
        FacebookSdk.sdkInitialize(application.getApplicationContext());
        AppEventsLogger.activateApp(application);

        facebookCallbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(facebookCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });


        //Facebook Token
        facebookAccessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                facebookAccessToken = currentAccessToken;
            }
        };
        facebookAccessToken = AccessToken.getCurrentAccessToken();

        //Facebook Profile
        facebbokProfileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                facebookProfile = currentProfile;
            }
        };
        facebookProfile = Profile.getCurrentProfile();
    }

    @Override
    public CallbackManager getFacebookCallbackManager() {
        return this.facebookCallbackManager;
    }

    @Override
    public Observable<Profile> getFacebookProfile() {
        return Observable.just(facebookProfile);
    }
}
