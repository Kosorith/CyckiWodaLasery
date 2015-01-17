package pl.lodz.p.ftims.geocaching.logic.challenges;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import pl.lodz.p.ftims.geocaching.dao.IChallengeAccess;
import pl.lodz.p.ftims.geocaching.model.Challenge;
import pl.lodz.p.ftims.geocaching.model.ChallengeStub;
import pl.lodz.p.ftims.geocaching.model.Hint;

import static com.googlecode.catchexception.CatchException.verifyException;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.spy;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class ChallengeCreationServiceImplTest {

    private ChallengeCreationService challengeCreationService;

    @Before
    public void setUp() throws Exception {
        IChallengeAccess access = mock(IChallengeAccess.class);
        when(access.sendNewChallenge(any(Challenge.class))).thenReturn(true);
        challengeCreationService = new ChallengeCreationServiceImpl(access);
    }

    @Test
    public void testInitCreation() throws Exception {
        verifyException(challengeCreationService, NullPointerException.class).addHint(null);

        challengeCreationService.initCreation();

        challengeCreationService.addHint(null);
    }

    @Test
    public void testSaveChallenge() throws Exception {
        assertFalse(challengeCreationService.saveChallenge());

        ChallengeCreationService spy = spy(challengeCreationService);

        verify(spy, times(0)).preverifyChallenge();
        spy.saveChallenge();
        verify(spy, times(1)).preverifyChallenge();
    }

    @Test
    public void testPreverifyChallenge() throws Exception {
        assertFalse(challengeCreationService.preverifyChallenge());

        Challenge challenge = new Challenge();
        ChallengeStub stub = new ChallengeStub();
        challenge.setStub(stub);
        challengeCreationService.setEditedChallenge(challenge);
        assertFalse(challengeCreationService.preverifyChallenge());

        challengeCreationService.addHint(new Hint());
        assertFalse(challengeCreationService.preverifyChallenge());

        stub.setName("Mam nazwę!");
        assertTrue(challengeCreationService.preverifyChallenge());
    }
}