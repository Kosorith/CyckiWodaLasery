package pl.lodz.p.ftims.geocaching.logic.challenges;

import pl.lodz.p.ftims.geocaching.model.Challenge;
import pl.lodz.p.ftims.geocaching.model.Hint;
import pl.lodz.p.ftims.geocaching.model.Solution;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michal on 12/8/14.
 */
public class ChallengeSolvingServiceImpl implements ChallengeSolvingService {

    private List<HintsObserver> hintsObservers = new ArrayList<HintsObserver>();

    @Override
    public Challenge getActiveChallenge() {
        return null;
    }

    @Override
    public void setActiveChallenge(Challenge challenge) {

    }

    @Override
    public List<Hint> getHintList() {
        return null;
    }

    @Override
    public void subscribe(HintsObserver hintsObserver) {
        hintsObservers.add(hintsObserver);
    }

    @Override
    public void unsubscribe(HintsObserver hintsObserver) {

    }

    @Override
    public boolean finishChallenge(Solution solution) {
        return false;
    }
}
