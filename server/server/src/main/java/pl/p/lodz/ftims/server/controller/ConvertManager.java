package pl.p.lodz.ftims.server.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.Location;

import org.springframework.stereotype.Component;

import pl.p.lodz.ftims.server.entities.User;
import dataModel.Challenge;
import dataModel.ChallengeEntry;
import dataModel.ChallengeListReply;
import dataModel.Coordinates;
import dataModel.Profile;
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
		ChallengeEntry challengeEntry=new ChallengeEntry();		
		challengeEntry.setId(entityChallenge.getId());
		challengeEntry.setName(entityChallenge.getName());
		challengeEntry.setDescription(entityChallenge.getDescription());
		challengeEntry.setPublicAccess(true);
		return challengeEntry;
	}

	@Override
	public Challenge convertToChallenge(
			pl.p.lodz.ftims.server.entities.Challenge entityChallenge) {
		Challenge challenge=new Challenge();
		
		challenge.setDescription(entityChallenge.getDescription());
		challenge.setId(entityChallenge.getId());
		challenge.setName(entityChallenge.getName());
		challenge.setPassword(entityChallenge.getPassword());
		challenge.setStatus(entityChallenge.getStatus());
		challenge.setPhoto(entityChallenge.getPhoto());
		challenge.setPoints(entityChallenge.getPoints());
		challenge.setSecretPassword(entityChallenge.getSecretPassword());
		challenge.setHints(null); //TODO
		Coordinates coordinates=new Coordinates();
		String location=entityChallenge.getLocation();
		System.out.println(location.substring(0,2));
		System.out.println(location.substring(3,5));
		coordinates.setLongitude(Double.parseDouble(location.substring(0,1)));
		coordinates.setLatitude(Double.parseDouble(location.substring(3,4)));
		challenge.setLocation(coordinates); 
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
		//ranking.setProfile(entityRanking.getUser()); //TODO
		return ranking;
	}

	@Override
	public Profile convertToProfile(User user) {
		Profile profile=new Profile();
		profile.setEmail(user.getEmail());
		profile.setId(user.getId());
		profile.setLogin(user.getLogin());
		profile.setNick(user.getNick());
		profile.setPassword(user.getPassword());
		//profile.setRanking(user.getRanking()); //TODO
		return profile;
	}

}
