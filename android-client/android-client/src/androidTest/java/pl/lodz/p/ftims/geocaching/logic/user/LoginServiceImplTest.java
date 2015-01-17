package pl.lodz.p.ftims.geocaching.logic.user;

import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import pl.lodz.p.ftims.geocaching.dao.IProfilesAccess;
import pl.lodz.p.ftims.geocaching.model.Credentials;
import pl.lodz.p.ftims.geocaching.model.Profile;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.spy;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class LoginServiceImplTest {

    private LoginService loginService;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;
    private IProfilesAccess iProfilesAccess;

    @Before
    public void setUp() throws Exception {
        context = mock(Context.class);
        preferences = mock(SharedPreferences.class);
        editor = mock(SharedPreferences.Editor.class);
        when(context.getSharedPreferences(anyString(), anyInt())).thenReturn(preferences);
        when(preferences.edit()).thenReturn(editor);

        iProfilesAccess = mock(IProfilesAccess.class);
        //when(iProfilesAccess.saveUserProfile(any(Credentials.class), any(Profile.class))).thenReturn(true);
        when(iProfilesAccess.getUserProfile(any(Credentials.class))).thenReturn(new Profile());
        when(iProfilesAccess.createNewUser(any(Profile.class), any(Credentials.class))).thenReturn(true);
        when(iProfilesAccess.changePassword(any(Credentials.class), anyString())).thenReturn(true);
        when(iProfilesAccess.login(any(Credentials.class))).thenReturn(true);

        loginService = new LoginServiceImpl(context, iProfilesAccess);
    }

    @Test
    public void testLoginWithoutStoringCredentials() throws Exception {
        LoginService spy = spy(loginService);
        verify(spy, times(0)).preverifyCredentials(any(Credentials.class));
        verify(preferences, times(0)).edit();
        verify(editor, times(0)).apply();
        spy.login(new Credentials("login", "pass"), false);
        verify(preferences, times(1)).edit();
        verify(editor, atLeast(1)).remove(anyString());
        verify(editor, times(1)).apply();
        verify(spy, times(1)).preverifyCredentials(any(Credentials.class));
    }

    @Test
    public void testLoginWithStoringCredentials() throws Exception {
        LoginService spy = spy(loginService);
        verify(spy, times(0)).preverifyCredentials(any(Credentials.class));
        verify(preferences, times(0)).edit();
        verify(editor, times(0)).apply();
        spy.login(new Credentials("login", "pass"), true);
        verify(preferences, times(1)).edit();
        verify(editor, atLeast(1)).putString(anyString(), anyString());
        verify(editor, times(1)).apply();
        verify(spy, times(1)).preverifyCredentials(any(Credentials.class));
    }

    @Test
    public void testGetRememberedCredentials() throws Exception {
        verify(context, times(0)).getSharedPreferences(anyString(), anyInt());
        loginService.getRememberedCredentials();
        verify(context, times(1)).getSharedPreferences(anyString(), anyInt());
        verify(preferences, times(0)).edit();
    }

    @Test
    public void testRegister() throws Exception {
        verify(iProfilesAccess, times(0)).createNewUser(any(Profile.class), any(Credentials.class));
        assertTrue(loginService.register(new Credentials("username", "pass"), new Profile()));
        verify(iProfilesAccess, times(1)).createNewUser(any(Profile.class), any(Credentials.class));
    }

    @Test
    public void testChangePassword() throws Exception {
        loginService.login(new Credentials("username", "pass"), true);
        verify(iProfilesAccess, times(0)).changePassword(any(Credentials.class), anyString());
        assertTrue(loginService.changePassword("oldpass", "pass"));
        verify(iProfilesAccess, times(1)).changePassword(any(Credentials.class), anyString());
    }

    @Test
    public void testPreverifyCredentials() throws Exception {
        assertFalse(loginService.preverifyCredentials(new Credentials("", "")));
        assertFalse(loginService.preverifyCredentials(new Credentials("", "pass")));
        assertFalse(loginService.preverifyCredentials(new Credentials("username", "")));
        assertTrue(loginService.preverifyCredentials(new Credentials("username", "pass")));
    }

    @Test
    public void testLogout() throws Exception {
        loginService.login(new Credentials("login", "pass"), true);
        Credentials currentCredentials = loginService.getCurrentCredentials();
        System.out.println(currentCredentials);
        assertNotNull(currentCredentials);
        loginService.logout();
        assertNull(loginService.getCurrentCredentials());
    }
}