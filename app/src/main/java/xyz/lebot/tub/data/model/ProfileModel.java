package xyz.lebot.tub.data.model;

import android.net.Uri;

/**
 * Created by axell on 25/11/2016.
 */

public class ProfileModel {
    private String name;
    private Uri profileUri;
    private Uri profilePictureUri;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Uri getProfileUri() {
        return profileUri;
    }

    public void setProfileUri(Uri profileUri) {
        this.profileUri = profileUri;
    }

    public Uri getProfilePictureUri() {
        return profilePictureUri;
    }

    public void setProfilePictureUri(Uri profilePictureUri) {
        this.profilePictureUri = profilePictureUri;
    }
}
