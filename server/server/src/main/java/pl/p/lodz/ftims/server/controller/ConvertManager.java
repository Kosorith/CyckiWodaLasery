package pl.p.lodz.ftims.server.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import dataModel.Challenge;
import dataModel.ChallengeEntry;
import dataModel.ChallengeListReply;
import dataModel.Ranking;
import dataModel.RankingReply;

/**
 * @author Przemysław Holak
 */

@Component
public class ConvertManager implements IConvertManager {

	@Override
	public ChallengeEntry convertToChallengeEntry(
			pl.p.lodz.ftims.server.entities.Challenge entityChallenge) {
		ChallengeEntry responseChallenge=new ChallengeEntry();		
		responseChallenge.setId(entityChallenge.getId());
		responseChallenge.setName(entityChallenge.getName());
		responseChallenge.setDescription(entityChallenge.getDescription());
		return responseChallenge;
	}

	@Override
	public Challenge convertToChallenge(
			pl.p.lodz.ftims.server.entities.Challenge entityChallenge) {
		Challenge challenge=new Challenge();
		challenge.setDescription(entityChallenge.getDescription());
		challenge.setId(entityChallenge.getId());
		//challenge.setLocation(entityChallenge.getLocation());
		challenge.setName(entityChallenge.getName());
		challenge.setPassword(entityChallenge.getPassword());
		challenge.setPoints(entityChallenge.getPoints());
		challenge.setStatus(entityChallenge.getStatus());
		return challenge;
	}

	@Override
	public ChallengeListReply convertChallengeList(
			List<pl.p.lodz.ftims.server.entities.Challenge> entityChallenges) {
		
		List<ChallengeEntry> challenges = new ArrayList<ChallengeEntry>();
		for (pl.p.lodz.ftims.server.entities.Challenge entityChallenge : entityChallenges) {
			challenges.add(convertToChallengeEntry(entityChallenge));
		}
		ChallengeListReply clr=new ChallengeListReply(challenges);
		return clr;
	}

	@Override
	public RankingReply convertRankingList(
			List<pl.p.lodz.ftims.server.entities.Ranking> entityRankingList) {
		RankingReply rankingReply=new RankingReply();
		
		List<Ranking> rankingList =new ArrayList<Ranking>();
		for (pl.p.lodz.ftims.server.entities.Ranking ranking : entityRankingList) {
			rankingList.add(convertRanking(ranking));
		}
		rankingReply.setRanking(rankingList);
		return rankingReply;
	}

	@Override
	public Ranking convertRanking(
			pl.p.lodz.ftims.server.entities.Ranking entityRanking) {
		Ranking ranking=new Ranking();
		ranking.setId(entityRanking.getId());
		ranking.setPoints(entityRanking.getPoints());
		ranking.setCompletedChallengesNum(entityRanking.getCompletedChallengesNum());
		//ranking.setProfile(entityRanking.getUser());
		return ranking;
	}

}
