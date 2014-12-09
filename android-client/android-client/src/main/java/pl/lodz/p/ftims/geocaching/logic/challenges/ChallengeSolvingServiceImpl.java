package pl.lodz.p.ftims.geocaching.logic.challenges;

import android.location.Location;
import pl.lodz.p.ftims.geocaching.logic.gps.LocationObserver;
import pl.lodz.p.ftims.geocaching.logic.gps.LocationService;
import pl.lodz.p.ftims.geocaching.logic.patterns.ListSubject;
import pl.lodz.p.ftims.geocaching.logic.user.LoginService;
import pl.lodz.p.ftims.geocaching.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michal on 12/8/14.
 */
public class ChallengeSolvingServiceImpl extends ListSubject<HintsObserver>
                                         implements ChallengeSolvingService, LocationObserver {

    private LocationService locationService;
    private LoginService loginService;
    private Challenge activeChallenge;
    private float closestDistance;

    public ChallengeSolvingServiceImpl(LocationService locationService, LoginService loginService) {
        this.locationService = locationService;
        this.loginService = loginService;
    }

    @Override
    public void startChallenge(ChallengeStub challenge) {
        // TODO: Przekaż do Access
        resetHints();
    }

    @Override
    public void startChallenge(ChallengeStub challenge, String password) {
        // TODO: Przekaż do Access
        resetHints();
    }

    @Override
    public Challenge getActiveChallenge() {
        return activeChallenge;
    }

    @Override
    public boolean solveChallenge(Solution solution) {
        // TODO: przekaż do access
        return false;
    }

    @Override
    public void onLocationChanged(GeoCoords geoCoords) {
        float newDistance = calculateDistance(geoCoords);
        updateHints(newDistance);
    }

    private void resetHints() {
        closestDistance = Float.MAX_VALUE;
        float newDistance = calculateDistance(locationService.getCurrentLocation());
        updateHints(newDistance);
    }

    private void updateHints(float newDistance) {
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

    private float calculateDistance(GeoCoords newCoords) {
        if (newCoords == null) {
            return Float.MAX_VALUE;
        }
        return activeChallenge.getStub().getLocation().distanceTo(newCoords);
    }

}
