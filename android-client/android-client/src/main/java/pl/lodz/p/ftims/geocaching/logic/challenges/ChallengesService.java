package pl.lodz.p.ftims.geocaching.logic.challenges;

import pl.lodz.p.ftims.geocaching.model.ChallengeStub;
import pl.lodz.p.ftims.geocaching.model.RankingEntry;

import java.util.List;

/**
 * Usługa pozyskująca informacje o wyzwaniach.
 * @author Michał Sośnicki, Andrzej Kurczewski
 */
public interface ChallengesService {

    /**
     * Zwraca listę wszystkich dostępnych wyzwań. Na jedno z nich można się następnie zarejestrować.
     * Zwracane są tylko zalążki wyzwań - bez części informacji.
     * @return Lista z informacjami o dostępnych wyzwaniach.
     */
    List<ChallengeStub> getChallengeList();

    /**
     * Zwraca ranking użytkowników.
     * @return Ranking użytkowników.
     */
    List<RankingEntry> getRanking();

}
