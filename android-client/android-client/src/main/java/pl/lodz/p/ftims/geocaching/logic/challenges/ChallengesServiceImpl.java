package pl.lodz.p.ftims.geocaching.logic.challenges;

import pl.lodz.p.ftims.geocaching.model.Challenge;
import pl.lodz.p.ftims.geocaching.model.Solution;

import java.util.List;

/**
 * Created by michal on 12/8/14.
 */
public class ChallengesServiceImpl implements ChallengesService {
    @Override
    public List<Challenge> getChallengeList() {
        return null;
    }

    @Override
    public boolean takeChallenge(Challenge challenge) {
        return false;
    }

    @Override
    public void abandonChallenge(Challenge challenge) {

    }

    @Override
    public boolean finishChallenge(Solution solution) {
        return false;
    }

    @Override
    public List<Challenge> getAcceptedChallenges() {
        return null;
    }
}
