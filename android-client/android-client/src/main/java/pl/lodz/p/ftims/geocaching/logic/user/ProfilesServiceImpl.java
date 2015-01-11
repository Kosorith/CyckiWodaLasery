package pl.lodz.p.ftims.geocaching.logic.user;

import pl.lodz.p.ftims.geocaching.dao.IProfilesAccess;
import pl.lodz.p.ftims.geocaching.logic.patterns.StringUtils;
import pl.lodz.p.ftims.geocaching.model.Credentials;
import pl.lodz.p.ftims.geocaching.model.Profile;

/**
 * Implementacja usługi profilowej, korzystająca z innych usług i dao w projekcie.
 * @author Michał Sośnicki, Andrzej Kurczewski
 */
public class ProfilesServiceImpl implements ProfilesService {

    private LoginService loginService;
    private IProfilesAccess profilesAccess;

    private Credentials lastUser;
    private Profile currentProfile;

    public ProfilesServiceImpl(LoginService loginService, IProfilesAccess profilesAccess) {
        this.loginService = loginService;
        this.profilesAccess = profilesAccess;
    }

    @Override
    public Profile getCurrentProfile() {
        Credentials currentUser = loginService.getCurrentCredentials();

        if (lastUser != currentUser) {
            currentProfile = currentUser == null ? null : profilesAccess.getUserProfile(currentUser);
            lastUser = currentUser;
        }

        return currentProfile;
    }

    @Override
    public void setCurrentProfile(Profile profile) {
        currentProfile = profile;
    }

    @Override
    public void reloadProfile() {
        Credentials currentUser = loginService.getCurrentCredentials();
        currentProfile = currentUser == null ? null : profilesAccess.getUserProfile(currentUser);
    }

    @Override
    public boolean preverifyProfile(Profile profile) {
        return StringUtils.notEmpty(profile.getNick()) && StringUtils.notEmpty(profile.getEmail());
    }

//    @Override
//    public boolean saveProfile() {
//        if (!preverifyProfile(currentProfile)) {
//            return false;
//        }
//
//      return profilesAccess.saveUserProfile(loginService.getCurrentCredentials(), currentProfile);
//    }

}
