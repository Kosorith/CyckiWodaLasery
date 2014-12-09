package pl.lodz.p.ftims.geocaching.logic.user;

import android.content.Context;
import android.content.SharedPreferences;
import pl.lodz.p.ftims.geocaching.logic.dao.IChallengeAccess;
import pl.lodz.p.ftims.geocaching.model.Credentials;

/**
 * Created by michal on 12/8/14.
 */
public class LoginServiceImpl implements LoginService {

    private static final String PREFS_REMEMBERED_CREDENTIALS = "PREFS_REMEMBERED_CREDENTIALS";
    private static final String PREFS_KEY_LOGIN = "PREFS_KEY_LOGIN";
    private static final String PREFS_KEY_PASSWORD = "PREFS_KEY_PASSWORD";

    private IChallengeAccess challengeAccess; // TODO: Zapewne przyda się to jak już zaistnieje
    private Context context;

    private Credentials credentials;

    public LoginServiceImpl(Context context) {
        this.context = context;
    }

    @Override
    public Credentials getCurrentCredentials() {
        return credentials;
    }

    @Override
    public boolean login(Credentials credentials, boolean remember) {
        if (remember) {
            storeCredentials(credentials);
        }

        // TODO: Wyślij / zweryfikuj

        return false;
    }

    @Override
    public Credentials getRememberedCredentials() {
        SharedPreferences credentialPrefs = context.getSharedPreferences(PREFS_REMEMBERED_CREDENTIALS, Context.MODE_PRIVATE);

        String login = credentialPrefs.getString(PREFS_KEY_LOGIN, "");
        String password = credentialPrefs.getString(PREFS_KEY_PASSWORD, ""); // TODO: To też? Chyba nie, nie? :o

        return new Credentials(login, password);
    }

    private void storeCredentials(Credentials credentials) {
        SharedPreferences credentialPrefs = context.getSharedPreferences(PREFS_REMEMBERED_CREDENTIALS, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = credentialPrefs.edit();

        prefsEditor.putString(PREFS_KEY_LOGIN, credentials.getLogin());
        prefsEditor.putString(PREFS_KEY_PASSWORD, credentials.getPassword());

        prefsEditor.apply();
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
        return notEmpty(credentials.getLogin())
            && notEmpty(credentials.getPassword());
    }

    private boolean notEmpty(String string) {
        return string != null && !string.isEmpty();
    }
}
