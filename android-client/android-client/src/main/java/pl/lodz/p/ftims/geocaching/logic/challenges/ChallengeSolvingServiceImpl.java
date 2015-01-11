package pl.lodz.p.ftims.geocaching.logic.challenges;

import android.location.Location;
import pl.lodz.p.ftims.geocaching.dao.IChallengeAccess;
import pl.lodz.p.ftims.geocaching.logic.gps.LocationObserver;
import pl.lodz.p.ftims.geocaching.logic.gps.LocationService;
import pl.lodz.p.ftims.geocaching.logic.patterns.ListSubject;
import pl.lodz.p.ftims.geocaching.logic.user.LoginService;
import pl.lodz.p.ftims.geocaching.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementacja usługi kontrolującej proces rozwiązywania wyzwania z wykorzystaniem innych usług i dao.
 * @author Michał Sośnicki, Andrzej Kurczewski
 */
public class ChallengeSolvingServiceImpl extends ListSubject<HintsObserver>
                                         implements ChallengeSolvingService, LocationObserver {

    private LocationService locationService;
    private LoginService loginService;
    private IChallengeAccess challengeAccess;

    private SolvingMode solvingMode = SolvingMode.HINT_MODE;
    private Challenge activeChallenge;
    private float closestDistance;
    private float maxDistance;

    public ChallengeSolvingServiceImpl(LocationService locationService, LoginService loginService, IChallengeAccess challengeAccess) {
        this.locationService = locationService;
        this.loginService = loginService;
        this.challengeAccess = challengeAccess;
    }

    @Override
    public void startChallenge(ChallengeStub challenge) {
        activeChallenge = challengeAccess.pickChallengeHints(challenge);
        resetHints();
    }

    @Override
    public boolean startChallenge(ChallengeStub challenge, String password) {
        activeChallenge = challengeAccess.pickChallengeHints(challenge, password);

        if (activeChallenge == null) {
            return false;
        }

        resetHints();
        return true;
    }

    @Override
    public Challenge getActiveChallenge() {
        return activeChallenge;
    }

    @Override
    public boolean solveChallenge(String password) {
        Solution solution = new Solution(password, activeChallenge);
        return challengeAccess.checkChallengeAnswer(solution, loginService.getCurrentCredentials());
    }

    @Override
    public void onLocationChanged(GeoCoords geoCoords) {
        float newDistance = calculateDistance(geoCoords);
        updateHints(newDistance);
        updateHeat(newDistance);
        closestDistance = newDistance;
    }

    @Override
    public SolvingMode getMode() {
        return solvingMode;
    }

    @Override
    public void setMode(SolvingMode solvingMode) {
        this.solvingMode = solvingMode;
    }

    private void resetHints() {
        closestDistance = Float.MAX_VALUE;
        maxDistance = activeChallenge.maxDistanceHint().getDistance();
        float newDistance = calculateDistance(locationService.getCurrentLocation());
        updateHints(newDistance);
        updateHeat(newDistance);
        closestDistance = newDistance;
    }

    private void updateHints(float newDistance) {
        if (solvingMode != SolvingMode.HINT_MODE) {
            return;
        }
        if (newDistance >= closestDistance) {
            return;
        }

        List<Hint> newHints = activeChallenge.hintsBetween((int) newDistance, (int) closestDistance);

        for (Hint hint : newHints) {
            for (HintsObserver observer : observers) {
                observer.onNewHint(hint);
            }
        }
    }

    private void updateHeat(float newDistance) {
        if (solvingMode != SolvingMode.HEAT_MODE) {
            return;
        }

        int temperature = Math.max(0, (int) ((maxDistance - newDistance) / maxDistance * 100));

        for (HintsObserver observer : observers) {
            observer.onHeatChange(temperature);
        }
    }

    private float calculateDistance(GeoCoords newCoords) {
        if (newCoords == null) {
            return Float.MAX_VALUE;
        }
        return activeChallenge.getStub().getLocation().distanceTo(newCoords);
    }

}
