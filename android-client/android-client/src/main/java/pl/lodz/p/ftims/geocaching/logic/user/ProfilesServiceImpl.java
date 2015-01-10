package pl.lodz.p.ftims.geocaching.logic.user;

import pl.lodz.p.ftims.geocaching.dao.IProfilesAccess;
import pl.lodz.p.ftims.geocaching.model.Credentials;
import pl.lodz.p.ftims.geocaching.model.Profile;

/**
 * Created by michal on 12/8/14.
 */
public class ProfilesServiceImpl implements ProfilesService {

    private LoginService loginService;
    private IProfilesAccess profilesAccess;

    private Profile currentProfile;

    public ProfilesServiceImpl(LoginService loginService, IProfilesAccess profilesAccess) {
        this.loginService = loginService;
        this.profilesAccess = profilesAccess;
    }

    @Override
    public Profile getCurrentProfile() {
        return currentProfile;
    }

    @Override
    public void setCurrentProfile(Profile profile) {
        currentProfile = profile;
    }

    @Override
    public void loadProfile() {
        currentProfile = profilesAccess.getUserProfile(loginService.getCurrentCredentials());
    }

//    @Override
//    public boolean saveProfile() {
//        if (!preverifyProfile(currentProfile)) {
//            return false;
//        }
//
//      return profilesAccess.saveUserProfile(loginService.getCurrentCredentials(), currentProfile);
//    }

//    @Override
//    public boolean preverifyProfile(Profile profile) {
//        return true;
//    }
}
