package pl.lodz.p.ftims.geocaching.logic.challenges;

import pl.lodz.p.ftims.geocaching.model.Challenge;
import pl.lodz.p.ftims.geocaching.model.Solution;

import java.util.List;

/**
 * Created by michal on 11/19/14.
 */
public interface ChallengesService {

    List<Challenge> getChallengeList();

    boolean finishChallenge(Solution solution);

}
