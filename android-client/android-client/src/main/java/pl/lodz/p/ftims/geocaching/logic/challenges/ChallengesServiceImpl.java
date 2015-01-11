package pl.lodz.p.ftims.geocaching.logic.challenges;

import pl.lodz.p.ftims.geocaching.dao.IChallengeAccess;
import pl.lodz.p.ftims.geocaching.dao.IProfilesAccess;
import pl.lodz.p.ftims.geocaching.logic.gps.LocationService;
import pl.lodz.p.ftims.geocaching.logic.inject.InjectPlz;
import pl.lodz.p.ftims.geocaching.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementacja usługi pozyskującej informacje o wyzwaniach z wykorzystaniem innych
 * usług i dao.
 * @author Michał Sośnicki, Andrzej Kurczewski
 */
public class ChallengesServiceImpl implements ChallengesService {

    private LocationService locationService;
    private IChallengeAccess challengeAccess;
    private IProfilesAccess profilesAccess;

    public ChallengesServiceImpl(LocationService locationService, IChallengeAccess challengeAccess,
                                 IProfilesAccess profilesAccess) {
        this.locationService = locationService;
        this.challengeAccess = challengeAccess;
        this.profilesAccess = profilesAccess;
    }

    @Override
    public List<ChallengeStub> getChallengeList() {
        GeoCoords currentCoords = locationService.getCurrentLocation();
        if (currentCoords == null) {
            return new ArrayList<ChallengeStub>();
        }

        return challengeAccess.pickChallengeList(currentCoords);
    }

    @Override
    public List<RankingEntry> getRanking() {
        return profilesAccess.pickRanking();
    }

}
