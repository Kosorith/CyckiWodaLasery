package pl.lodz.p.ftims.geocaching.logic.user;

import android.content.Context;
import android.content.SharedPreferences;
import pl.lodz.p.ftims.geocaching.dao.IProfilesAccess;
import pl.lodz.p.ftims.geocaching.logic.patterns.StringUtils;
import pl.lodz.p.ftims.geocaching.model.Credentials;
import pl.lodz.p.ftims.geocaching.model.Profile;

/**
 * Implementacja usługi logowania korzystająca z innych usług i dao.
 * @author Michał Sośnicki, Andrzej Kurczewski
 */
public class LoginServiceImpl implements LoginService {

    private static final String PREFS_REMEMBERED_CREDENTIALS = "PREFS_REMEMBERED_CREDENTIALS";
    private static final String PREFS_KEY_LOGIN = "PREFS_KEY_LOGIN";
    private static final String PREFS_KEY_PASSWORD = "PREFS_KEY_PASSWORD";

    private Context context;

    private IProfilesAccess profilesAccess;

    private Credentials credentials;


    public LoginServiceImpl(Context context, IProfilesAccess profilesAccess) {
        this.context = context;
        this.profilesAccess = profilesAccess;
    }

    @Override
    public Credentials getCurrentCredentials() {
        return credentials;
    }

    @Override
    public boolean login(Credentials credentials, boolean remember) {
        if (!preverifyCredentials(credentials)) {
            return false;
        }

        if (remember) {
            storeCredentials(credentials);
        }
        else {
            resetStoredCredentials();
        }

        if (profilesAccess.login(credentials)) {
            this.credentials = credentials;
            return true;
        }
        return false;
    }

    @Override
    public void logout() {
        this.credentials = null;
    }

    @Override
    public Credentials getRememberedCredentials() {
        SharedPreferences credentialPrefs = context.getSharedPreferences(PREFS_REMEMBERED_CREDENTIALS, Context.MODE_PRIVATE);

        String login = credentialPrefs.getString(PREFS_KEY_LOGIN, "");
        String password = credentialPrefs.getString(PREFS_KEY_PASSWORD, "");

        return new Credentials(login, password);
    }

    private void storeCredentials(Credentials credentials) {
        SharedPreferences credentialPrefs = context.getSharedPreferences(PREFS_REMEMBERED_CREDENTIALS, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = credentialPrefs.edit();

        prefsEditor.putString(PREFS_KEY_LOGIN, credentials.getLogin());
        prefsEditor.putString(PREFS_KEY_PASSWORD, credentials.getPassword());

        prefsEditor.apply();
    }

    private void resetStoredCredentials() {
        SharedPreferences credentialPrefs = context.getSharedPreferences(PREFS_REMEMBERED_CREDENTIALS, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = credentialPrefs.edit();

        prefsEditor.remove(PREFS_KEY_LOGIN);
        prefsEditor.remove(PREFS_KEY_PASSWORD);

        prefsEditor.apply();
    }

    @Override
    public boolean register(Credentials credentials, Profile profile) {
        if (!preverifyCredentials(credentials)) {
            return false;
        }

        if (profilesAccess.createNewUser(profile, credentials)) {
            this.credentials = credentials;
            return true;
        }
        return false;
    }

    @Override
    public boolean changePassword(String oldPassword, String newPassword) {
        if (credentials == null) {
           return false;
        }

        Credentials sentCredentials = new Credentials(credentials.getLogin(), oldPassword);

        if (profilesAccess.changePassword(sentCredentials, newPassword)) {
            credentials.setPassword(newPassword);
            return true;
        }
        return false;
    }

    @Override
    public boolean preverifyCredentials(Credentials credentials) {
        return StringUtils.notEmpty(credentials.getLogin())
            && StringUtils.notEmpty(credentials.getPassword());
    }
}
