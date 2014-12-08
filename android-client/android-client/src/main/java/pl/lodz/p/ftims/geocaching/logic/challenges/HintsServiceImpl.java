package pl.lodz.p.ftims.geocaching.logic.challenges;

import pl.lodz.p.ftims.geocaching.model.Challenge;
import pl.lodz.p.ftims.geocaching.model.Hint;

import java.util.List;

/**
 * Created by michal on 12/8/14.
 */
public class HintsServiceImpl implements HintsService {
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

    }

    @Override
    public void unsubscribe(HintsObserver hintsObserver) {

    }
}
