package pl.lodz.p.ftims.geocaching.logic.user;

import pl.lodz.p.ftims.geocaching.model.Credentials;
import pl.lodz.p.ftims.geocaching.model.UserSession;

/**
 * Created by michal on 11/19/14.
 */
public interface LoginService {

    UserSession getCurrentSession();

    boolean login(Credentials credentials, boolean remember);

    Credentials getRememberedCredentials();

    boolean register(Credentials credentials);

    void changePassword(Credentials credentials, String newPassword);

    boolean preverifyCredentials(Credentials credentials);

}
