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
 * Klasa, której zadaniem jest konwertowanie obuiektów logiki biznesowej do DTO
 * @author Przemysław Holak
 */

@Component
public class ConvertManager implements IConvertManager {

	/* (non-Javadoc)
	 * @see pl.p.lodz.ftims.server.controller.IConvertManager#convertToChallengeEntry(pl.p.lodz.ftims.server.entities.Challenge)
	 */
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

	/* (non-Javadoc)
	 * @see pl.p.lodz.ftims.server.controller.IConvertManager#convertToChallenge(pl.p.lodz.ftims.server.entities.Challenge)
	 */
	@Override
	public Challenge convertToChallenge(
			pl.p.lodz.ftims.server.entities.Challenge entityChallenge) {
		Challenge challenge=new Challenge();
		
		challenge.setDescription(entityChallenge.getDescription());
		challenge.setId(entityChallenge.getId());
		challenge.setName(entityChallenge.getName());
		challenge.setPassword(entityChallenge.getPassword());
		challenge.setStatus(entityChallenge.getStatus());
		challenge.setPhoto(entityChallenge.getPhoto().getBytes());
		challenge.setPoints(entityChallenge.getPoints());
		challenge.setSecretPassword(entityChallenge.getSecretPassword());
		Collection<Hint> entityHints = entityChallenge.getHints();
		
		List<KHint> hints=new ArrayList<>(); 
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

	/* (non-Javadoc)
	 * @see pl.p.lodz.ftims.server.controller.IConvertManager#convertChallengeList(java.util.List)
	 */
	@Override
	public ChallengeListReply convertChallengeList(
			List<pl.p.lodz.ftims.server.entities.Challenge> entityChallenges) {
		
		List<ChallengeEntry> challenges = new ArrayList<>();
		for (pl.p.lodz.ftims.server.entities.Challenge entityChallenge : entityChallenges) {
			challenges.add(convertToChallengeEntry(entityChallenge));
		}
		ChallengeListReply challengeListReply=new ChallengeListReply(challenges);
		return challengeListReply;
	}

	/* (non-Javadoc)
	 * @see pl.p.lodz.ftims.server.controller.IConvertManager#convertRankingList(java.util.List)
	 */
	@Override
	public RankingReply convertRankingList(
			List<pl.p.lodz.ftims.server.entities.Ranking> entityRankingList) {
		RankingReply rankingReply=new RankingReply();
		
		List<Ranking> rankingList =new ArrayList<>();
		for (pl.p.lodz.ftims.server.entities.Ranking ranking : entityRankingList) {
			rankingList.add(convertRanking(ranking));
		}
		rankingReply.setRanking(rankingList);
		return rankingReply;
	}

	/* (non-Javadoc)
	 * @see pl.p.lodz.ftims.server.controller.IConvertManager#convertRanking(pl.p.lodz.ftims.server.entities.Ranking)
	 */
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

	/* (non-Javadoc)
	 * @see pl.p.lodz.ftims.server.controller.IConvertManager#convertToProfile(pl.p.lodz.ftims.server.entities.User)
	 */
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

	/* (non-Javadoc)
	 * @see pl.p.lodz.ftims.server.controller.IConvertManager#convertToKHint(pl.p.lodz.ftims.server.entities.Hint)
	 */
	@Override
	public KHint convertToKHint(Hint entityHint) {
		String text = entityHint.getText();
		byte[] photo = entityHint.getPhoto().getBytes();
		int distance = entityHint.getDistance();
		return new KHint(text, photo, distance);
	}
}
