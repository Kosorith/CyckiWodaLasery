package pl.p.lodz.ftims.server.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import pl.p.lodz.ftims.server.entities.Hint;
import pl.p.lodz.ftims.server.entities.User;
import dataModel.Challenge;
import dataModel.ChallengeEntry;
import dataModel.ChallengeListReply;
import dataModel.Coordinates;
import dataModel.KHint;
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
		challengeEntry.setPublicAccess(true);//TODO 
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
		Collection<Hint> entityHints = entityChallenge.getHints();
		
		List<KHint> hints=new ArrayList<KHint>(); 
		for (Hint entityHint : entityHints) {
			hints.add(convertToKHint(entityHint));
		}
		challenge.setHints(hints); 
		
		Coordinates coordinates=new Coordinates();
		String location=entityChallenge.getLocation();
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
		ChallengeListReply challengeListReply=new ChallengeListReply(challenges);
		return challengeListReply;
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
		ranking.setProfile(convertToProfile(entityRanking.getUser()));
		return ranking;
	}

	@Override
	public Profile convertToProfile(User entityUser) {
		Profile profile=new Profile();
		profile.setEmail(entityUser.getEmail());
		profile.setId(entityUser.getId());
		profile.setLogin(entityUser.getLogin());
		profile.setNick(entityUser.getNick());
		profile.setPassword(entityUser.getPassword());
		profile.setRanking(convertRanking(entityUser.getRanking()));
		return profile;
	}

	@Override
	public KHint convertToKHint(Hint entityHint) {
		String text = entityHint.getText();
		byte[] photo = entityHint.getPhoto();
		int distance = entityHint.getDistance();
		return new KHint(text, photo, distance);
	}
}
