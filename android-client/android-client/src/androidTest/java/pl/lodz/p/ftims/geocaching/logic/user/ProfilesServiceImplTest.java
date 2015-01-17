package pl.lodz.p.ftims.geocaching.logic.user;

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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
//@Config(manifest = "./src/main/AndroidManifest.xml", emulateSdk = 18)
@Config(manifest = Config.NONE)
public class ProfilesServiceImplTest {

    private ProfilesService profilesService;

    @Before
    public void setUp() throws Exception {
        LoginService loginService = mock(LoginService.class);
        when(loginService.getCurrentCredentials()).thenReturn(new Credentials("login", "pass"));

        IProfilesAccess iProfilesAccess = mock(IProfilesAccess.class);
        //when(iProfilesAccess.saveUserProfile(any(Credentials.class), any(Profile.class))).thenReturn(true); //Czemu ktoś zakomentowuje metodę, jak nie umie jej zaimplementować? Luuudzie, API niech będzie jednolite
        when(iProfilesAccess.getUserProfile(any(Credentials.class))).thenReturn(new Profile("first", "", 0, 0)).thenReturn(new Profile("second", "", 0, 0));

        profilesService = new ProfilesServiceImpl(loginService, iProfilesAccess);
    }

    @Test
    public void testReloadProfile() throws Exception {
        assertEquals(profilesService.getCurrentProfile().getNick(), "first");
        profilesService.reloadProfile();
        assertEquals(profilesService.getCurrentProfile().getNick(), "second");
    }

    @Test
    public void testSaveProfile() throws Exception {
//        assertTrue(profilesService.saveProfile());
//
//        ProfilesService spy = spy(profilesService);
//
//        verify(spy, times(0)).preverifyProfile(any(Profile.class));
//        spy.saveProfile();
//        verify(spy, times(1)).preverifyProfile(any(Profile.class));
    }

    @Test
    public void testPreverifyProfile() throws Exception {
        Profile profile = new Profile();
        assertFalse(profilesService.preverifyProfile(profile));
        profile = new Profile("", "", 0, 0);
        assertFalse(profilesService.preverifyProfile(profile));
        profile = new Profile("zxc", "", 0, 0);
        assertFalse(profilesService.preverifyProfile(profile));
        profile = new Profile("", "zxc", 0, 0);
        assertFalse(profilesService.preverifyProfile(profile));
        profile = new Profile("zxc", "vbn", 0, 0);
        assertTrue(profilesService.preverifyProfile(profile));
    }
}