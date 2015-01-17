package pl.lodz.p.ftims.geocaching.logic.challenges;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.Arrays;

import pl.lodz.p.ftims.geocaching.dao.IChallengeAccess;
import pl.lodz.p.ftims.geocaching.logic.gps.LocationService;
import pl.lodz.p.ftims.geocaching.logic.user.LoginService;
import pl.lodz.p.ftims.geocaching.model.Challenge;
import pl.lodz.p.ftims.geocaching.model.ChallengeStub;
import pl.lodz.p.ftims.geocaching.model.Credentials;
import pl.lodz.p.ftims.geocaching.model.GeoCoords;
import pl.lodz.p.ftims.geocaching.model.Hint;
import pl.lodz.p.ftims.geocaching.model.Solution;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class ChallengeSolvingServiceImplTest {

    private ChallengeSolvingServiceImpl challengeSolvingService;
    private LocationService locationService;

    @Before
    public void setUp() throws Exception {
        locationService = mock(LocationService.class);
        GeoCoords coords0 = new GeoCoords(0.5, 0.1);
        GeoCoords coords1 = new GeoCoords(0.5, 0.2);
        GeoCoords coords2 = new GeoCoords(0.5, 0.3);
        GeoCoords coords3 = new GeoCoords(0.5, 0.4);
        GeoCoords coords4 = new GeoCoords(0.5, 0.5);
        when(locationService.getCurrentLocation())
                .thenReturn(coords0, coords1, coords2, coords1, coords3, coords2, coords3, coords4);

        LoginService loginService = mock(LoginService.class);

        IChallengeAccess iChallengeAccess = mock(IChallengeAccess.class);
        Challenge challenge = new Challenge();
        ChallengeStub stub = new ChallengeStub();
        stub.setName("nazwa");
        GeoCoords location = mock(GeoCoords.class);
        when(location.distanceTo(coords0)).thenReturn(20f);
        when(location.distanceTo(coords1)).thenReturn(15f);
        when(location.distanceTo(coords2)).thenReturn(10f);
        when(location.distanceTo(coords3)).thenReturn(5f);
        when(location.distanceTo(coords4)).thenReturn(0f);
        stub.setLocation(location);
        challenge.setStub(stub);
        Hint hint1 = new Hint();
        hint1.setDistance(10);
        Hint hint2 = new Hint();
        hint2.setDistance(20);
        Hint hint3 = new Hint();
        hint3.setDistance(15);
        challenge.setHints(Arrays.asList(hint1, hint2, hint3));
        when(iChallengeAccess.pickChallengeHints(any(ChallengeStub.class))).thenReturn(challenge);
        when(iChallengeAccess.pickChallengeHints(any(ChallengeStub.class), anyString())).thenReturn(challenge);
        when(iChallengeAccess.checkChallengeAnswer(any(Solution.class), any(Credentials.class))).then(new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocationOnMock) throws Throwable {
                Solution solution = (Solution) invocationOnMock.getArguments()[0];
                return solution.getSecret().equals("correct");
            }
        });

        challengeSolvingService = new ChallengeSolvingServiceImpl(locationService, loginService, iChallengeAccess);
    }

    @Test
    public void testStartChallenge() throws Exception {
        assertNull(challengeSolvingService.getActiveChallenge());
        challengeSolvingService.startChallenge(new ChallengeStub());
        assertNotNull(challengeSolvingService.getActiveChallenge());
    }

    @Test
    public void testSolveChallenge() throws Exception {
        assertFalse(challengeSolvingService.solveChallenge("incorrect"));
        assertTrue(challengeSolvingService.solveChallenge("correct"));
    }

    @Test
    public void testOnLocationChangedHeatMode() throws Exception {
        challengeSolvingService.startChallenge(new ChallengeStub());
        challengeSolvingService.setMode(SolvingMode.HEAT_MODE);
        class TestObserver implements HintsObserver {
            public int temperature = 0;
            @Override
            public void onNewHint(Hint hint) {
            }

            @Override
            public void onHeatChange(int temperature) {
                System.out.printf("TEMPERATURE CHANGED: %3dC%n", temperature);
                this.temperature = temperature;
            }
        }

        TestObserver observer = new TestObserver();

        System.out.println("Start                 20m");
        challengeSolvingService.subscribe(observer);
        assertEquals(0, observer.temperature);
        System.out.println("step 1                15m");
        challengeSolvingService.onLocationChanged(locationService.getCurrentLocation());
        assertEquals(25, observer.temperature);//25
        System.out.println("step 2                10m");
        challengeSolvingService.onLocationChanged(locationService.getCurrentLocation());
        assertEquals(50, observer.temperature);
        System.out.println("step 3                15m");
        challengeSolvingService.onLocationChanged(locationService.getCurrentLocation());
        assertEquals(25, observer.temperature);
        System.out.println("step 4                 5m");
        challengeSolvingService.onLocationChanged(locationService.getCurrentLocation());
        assertEquals(75, observer.temperature);
        System.out.println("step 5                10m");
        challengeSolvingService.onLocationChanged(locationService.getCurrentLocation());
        assertEquals(50, observer.temperature);
        System.out.println("step 6                 5m");
        challengeSolvingService.onLocationChanged(locationService.getCurrentLocation());
        assertEquals(75, observer.temperature);
        System.out.println("step 7                 0m");
        challengeSolvingService.onLocationChanged(locationService.getCurrentLocation());
        assertEquals(100, observer.temperature);
    }

    @Test
    public void testOnLocationChangedHintMode() throws Exception {
        challengeSolvingService.startChallenge(new ChallengeStub());
        challengeSolvingService.setMode(SolvingMode.HINT_MODE);
        class TestObserver implements HintsObserver {
            public boolean hint = false;
            @Override
            public void onNewHint(Hint hint) {
                System.out.printf("NEW HINT! DISTANCE: %4dm%n", hint.getDistance());
                this.hint = true;
            }

            @Override
            public void onHeatChange(int temperature) {
            }
        }

        TestObserver observer = new TestObserver();

        System.out.println("Start                 20m");
        challengeSolvingService.subscribe(observer);
        assertFalse(observer.hint);
        observer.hint = false;
        System.out.println("step 1                15m");
        challengeSolvingService.onLocationChanged(locationService.getCurrentLocation());
        assertTrue(observer.hint);
        observer.hint = false;
        System.out.println("step 2                10m");
        challengeSolvingService.onLocationChanged(locationService.getCurrentLocation());
        assertTrue(observer.hint);
        observer.hint = false;
        System.out.println("step 3                15m");
        challengeSolvingService.onLocationChanged(locationService.getCurrentLocation());
        assertFalse(observer.hint);
        observer.hint = false;
        System.out.println("step 4                 5m");
        challengeSolvingService.onLocationChanged(locationService.getCurrentLocation());
        assertTrue(observer.hint);
        observer.hint = false;
        System.out.println("step 5                10m");
        challengeSolvingService.onLocationChanged(locationService.getCurrentLocation());
        assertFalse(observer.hint);
        observer.hint = false;
        System.out.println("step 6                 5m");
        challengeSolvingService.onLocationChanged(locationService.getCurrentLocation());
        assertFalse(observer.hint);
        observer.hint = false;
        System.out.println("step 7                 0m");
        challengeSolvingService.onLocationChanged(locationService.getCurrentLocation());
        assertFalse(observer.hint);
        observer.hint = false;
    }
}