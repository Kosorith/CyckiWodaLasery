package pl.lodz.p.ftims.geocaching.logic.user;

import pl.lodz.p.ftims.geocaching.model.Profile;

/**
 * Created by michal on 12/8/14.
 */
public class ProfilesServiceImpl implements ProfilesService {

    private LoginService loginService;

    private Profile currentProfile;

    public ProfilesServiceImpl(LoginService loginService) {
        this.loginService = loginService;
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
        // TODO: z accessa
    }

    @Override
    public boolean saveProfile() {
        if (!preverifyProfile(currentProfile)) {
            return false;
        }

        // TODO: z accessa

        return false;
    }

    @Override
    public boolean preverifyProfile(Profile profile) {
        return true;
    }
}
