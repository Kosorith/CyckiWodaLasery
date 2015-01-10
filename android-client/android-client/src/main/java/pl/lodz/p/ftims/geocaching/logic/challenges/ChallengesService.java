package pl.lodz.p.ftims.geocaching.logic.challenges;

import pl.lodz.p.ftims.geocaching.model.ChallengeStub;
import pl.lodz.p.ftims.geocaching.model.RankingEntry;

import java.util.List;

/**
 * Created by michal on 11/19/14.
 */
public interface ChallengesService {

    List<ChallengeStub> getChallengeList();

    List<RankingEntry> getRanking();

}
