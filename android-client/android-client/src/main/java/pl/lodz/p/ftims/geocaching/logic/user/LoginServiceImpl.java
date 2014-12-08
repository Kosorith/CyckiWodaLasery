package pl.lodz.p.ftims.geocaching.logic.user;

import pl.lodz.p.ftims.geocaching.logic.dao.IChallengeAccess;
import pl.lodz.p.ftims.geocaching.model.Credentials;
import pl.lodz.p.ftims.geocaching.model.UserSession;

/**
 * Created by michal on 12/8/14.
 */
public class LoginServiceImpl implements LoginService {

    private IChallengeAccess challengeAccess; // TODO: Utworzyć jak się pojawi taka możliwość

    private UserSession userSession;

    public LoginServiceImpl() {
    }

    @Override
    public UserSession getCurrentSession() {
        return userSession;
    }

    @Override
    public boolean login(Credentials credentials) {
        return false;
    }

    @Override
    public boolean register(Credentials credentials) {
        return false;
    }

    @Override
    public void changePassword(Credentials credentials, String newPassword) {

    }

    @Override
    public boolean preverifyCredentials(Credentials credentials) {
        return false;
    }
}
