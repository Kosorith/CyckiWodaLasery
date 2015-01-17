package pl.p.lodz.ftims.server.controller;

/**
 * @author Przemysław Holak
 */

import java.util.List;

import pl.p.lodz.ftims.server.entities.Challenge;
import pl.p.lodz.ftims.server.entities.Hint;
import pl.p.lodz.ftims.server.entities.Ranking;
import pl.p.lodz.ftims.server.entities.User;

/**
 * Interfejs, którego zadaniem jest konwertowanie obieków logiki biznesowej do DTO
 * @author Przemysła Holak
 *
 */
public interface IConvertManager {
	
	
	/**
	 * Metoda konwertuje wyzwanie(Challenge) do DTO(ChallengeEntry) które jest zwracane REST-em do klienta.
	 * @param entityChallenge
	 * @return
	 */
	dataModel.ChallengeEntry convertToChallengeEntry(Challenge entityChallenge);
	
	/**
	 * Metoda konwertuje wyzwanie(Challenge) do DTO(dataModel.Challenge) które jest zwracane REST-em do klienta.
	 * @param entityChallenge
	 * @return
	 */
	dataModel.Challenge convertToChallenge(Challenge entityChallenge);
	
	/**
	 * Metoda konwertuje listę wyzwań(Challenge) do DTO(dataModel.ChallengeListReply) które jest zwracane REST-em do klienta.
	 * @param entityChallenges
	 * @return
	 */
	dataModel.ChallengeListReply convertChallengeList(List<Challenge> entityChallenges);
	
	/**
	 * Metoda konwertuje wpis w rankingu(Ranking) do DTO(dataModel.Ranking) które jest zwracane REST-em do klienta.
	 * @param entityRanking
	 * @return
	 */
	dataModel.Ranking convertRanking(Ranking entityRanking);
	
 	/**
 	 * Metoda konwertuje ranking(List<Ranking>) do DTO(dataModel.RankingReply) które jest zwracane REST-em do klienta.
 	 * @param entityRankingList
 	 * @return
 	 */
 	dataModel.RankingReply convertRankingList(List<Ranking> entityRankingList); 
 	
 	/**
 	 * Metoda konwertuje użytkownika(User) do DTO(dataModel.Profile) które jest zwracane REST-em do klienta.
 	 * @param entityUser
 	 * @return
 	 */
 	dataModel.Profile convertToProfile(User entityUser);
 	
 	/**
 	 * Metoda konwertuje podpowiedź(Hint) do DTO(dataModel.KHint) które jest zwracane REST-em do klienta.
 	 * @param entityHint
 	 * @return
 	 */
 	dataModel.KHint convertToKHint(Hint entityHint);

}
