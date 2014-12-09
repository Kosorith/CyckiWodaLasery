package pl.lodz.p.ftims.geocaching.logic.challenges;

import pl.lodz.p.ftims.geocaching.logic.patterns.Subject;
import pl.lodz.p.ftims.geocaching.model.Challenge;
import pl.lodz.p.ftims.geocaching.model.ChallengeStub;
import pl.lodz.p.ftims.geocaching.model.Hint;
import pl.lodz.p.ftims.geocaching.model.Solution;

import java.util.List;

/**
 * Created by michal on 11/19/14.
 */
public interface ChallengeSolvingService extends Subject<HintsObserver> {

    void startChallenge(ChallengeStub challenge);

    void startChallenge(ChallengeStub challenge, String password);

    Challenge getActiveChallenge();

    boolean solveChallenge(String password);

}
