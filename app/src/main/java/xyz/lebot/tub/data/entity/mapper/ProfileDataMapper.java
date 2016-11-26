package xyz.lebot.tub.data.entity.mapper;

import com.facebook.Profile;

import xyz.lebot.tub.data.model.ProfileModel;

/**
 * Created by axell on 25/11/2016.
 */

public class ProfileDataMapper {
    public ProfileModel transform(Profile profile) {
        ProfileModel profileModel = new ProfileModel();
        profileModel.setName(profile.getName());
        profileModel.setProfileUri(profile.getLinkUri());
        profileModel.setProfilePictureUri(profile.getProfilePictureUri(200,200));
        return profileModel;
    }
}
