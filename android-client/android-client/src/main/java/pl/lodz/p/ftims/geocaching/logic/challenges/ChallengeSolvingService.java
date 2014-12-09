package pl.lodz.p.ftims.geocaching.logic.challenges;

import pl.lodz.p.ftims.geocaching.model.Challenge;
import pl.lodz.p.ftims.geocaching.model.Hint;
import pl.lodz.p.ftims.geocaching.model.Solution;

import java.util.List;

/**
 * Created by michal on 11/19/14.
 */
public interface ChallengeSolvingService {

    Challenge getActiveChallenge();

    void setActiveChallenge(Challenge challenge);

    List<Hint> getHintList();

    void subscribe(HintsObserver hintsObserver);

    void unsubscribe(HintsObserver hintsObserver);

    boolean finishChallenge(Solution solution);

}
