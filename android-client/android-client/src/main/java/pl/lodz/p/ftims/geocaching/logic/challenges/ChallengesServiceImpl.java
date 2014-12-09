package pl.lodz.p.ftims.geocaching.logic.challenges;

import pl.lodz.p.ftims.geocaching.dao.IChallengeAccess;
import pl.lodz.p.ftims.geocaching.logic.gps.LocationService;
import pl.lodz.p.ftims.geocaching.logic.inject.InjectPlz;
import pl.lodz.p.ftims.geocaching.model.Challenge;
import pl.lodz.p.ftims.geocaching.model.ChallengeStub;
import pl.lodz.p.ftims.geocaching.model.GeoCoords;
import pl.lodz.p.ftims.geocaching.model.Solution;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michal on 12/8/14.
 */
public class ChallengesServiceImpl implements ChallengesService {

    private LocationService locationService;
    private IChallengeAccess challengeAccess;

    public ChallengesServiceImpl(LocationService locationService, IChallengeAccess challengeAccess) {
        this.locationService = locationService;
        this.challengeAccess = challengeAccess;
    }

    @Override
    public List<ChallengeStub> getChallengeList() {
        GeoCoords currentCoords = locationService.getCurrentLocation();
        if (currentCoords == null) {
            return new ArrayList<ChallengeStub>();
        }

        return challengeAccess.pickChallengeList(currentCoords);
    }

}
