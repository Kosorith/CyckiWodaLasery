package pl.p.lodz.ftims.server.controller;

/**
 * @author Przemysław Holak
 */

import java.util.List;

import pl.p.lodz.ftims.server.entities.Challenge;
import pl.p.lodz.ftims.server.entities.Ranking;

public interface IConvertManager {
	dataModel.ChallengeEntry convertToChallengeEntry(Challenge entityChallenge);
	
	dataModel.Challenge convertToChallenge(Challenge entityChallenge);
	
	dataModel.ChallengeListReply convertChallengeList(List<Challenge> entityChallenges);
	
	dataModel.Ranking convertRanking(Ranking entityRanking);
	
 	dataModel.RankingReply convertRankingList(List<Ranking> entityRankingList); 

}
