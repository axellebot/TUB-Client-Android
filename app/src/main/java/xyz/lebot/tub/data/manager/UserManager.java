package xyz.lebot.tub.data.manager;

import com.facebook.CallbackManager;
import com.facebook.Profile;

import rx.Observable;

/**
 * Created by axell on 25/11/2016.
 */

public interface UserManager {

    //Facebook
    CallbackManager getFacebookCallbackManager();
    Observable<Profile> getFacebookProfile();
}
