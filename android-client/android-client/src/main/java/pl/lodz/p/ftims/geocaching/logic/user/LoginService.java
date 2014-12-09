package pl.lodz.p.ftims.geocaching.logic.user;

import pl.lodz.p.ftims.geocaching.model.Credentials;
import pl.lodz.p.ftims.geocaching.model.Profile;

/**
 * Created by michal on 11/19/14.
 */
public interface LoginService {

    Credentials getCurrentCredentials();

    boolean login(Credentials credentials, boolean remember);

    Credentials getRememberedCredentials();

    boolean register(Credentials credentials, Profile profile);

    boolean changePassword(Credentials credentials, String newPassword);

    boolean preverifyCredentials(Credentials credentials);

}
