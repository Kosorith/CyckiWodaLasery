package pl.lodz.p.ftims.geocaching.logic.challenges;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import pl.lodz.p.ftims.geocaching.dao.IChallengeAccess;
import pl.lodz.p.ftims.geocaching.dao.IProfilesAccess;
import pl.lodz.p.ftims.geocaching.logic.gps.LocationService;
import pl.lodz.p.ftims.geocaching.model.ChallengeStub;
import pl.lodz.p.ftims.geocaching.model.GeoCoords;
import pl.lodz.p.ftims.geocaching.model.RankingEntry;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.spy;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class ChallengesServiceImplTest {

    private ChallengesService challengesService;
    private IProfilesAccess iProfilesAccess;

    @Before
    public void setUp() throws Exception {
        LocationService locationService = mock(LocationService.class);
        GeoCoords coords = new GeoCoords(0.1, 0.1);
        GeoCoords coords1 = new GeoCoords(0.2, 0.3);
        when(locationService.getCurrentLocation()).thenReturn(coords).thenReturn(coords1).thenReturn(null);

        IChallengeAccess iChallengeAccess = mock(IChallengeAccess.class);
        when(iChallengeAccess.pickChallengeList(coords)).thenReturn(new ArrayList<ChallengeStub>());
        ArrayList<ChallengeStub> stubs = new ArrayList<ChallengeStub>();
        stubs.add(new ChallengeStub());
        stubs.add(new ChallengeStub());
        stubs.add(new ChallengeStub());
        when(iChallengeAccess.pickChallengeList(coords1)).thenReturn(stubs);

        iProfilesAccess = mock(IProfilesAccess.class);

        challengesService = new ChallengesServiceImpl(locationService, iChallengeAccess, iProfilesAccess);
    }

    @Test
    public void testGetChallengeList() throws Exception {
        List<ChallengeStub> list = challengesService.getChallengeList();
        assertTrue(list.isEmpty());

        list = challengesService.getChallengeList();
        assertEquals(3, list.size());

        list = challengesService.getChallengeList();
        assertTrue(list.isEmpty());
    }

    @Test
    public void testGetRanking() throws Exception {
        verify(iProfilesAccess, times(0)).pickRanking();
        challengesService.getRanking();
        verify(iProfilesAccess, times(1)).pickRanking();
    }
}