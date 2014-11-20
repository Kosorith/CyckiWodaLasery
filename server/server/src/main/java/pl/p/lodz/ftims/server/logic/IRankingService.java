package pl.p.lodz.ftims.server.logic;

import java.util.List;
import pl.p.lodz.ftims.server.entities.Ranking;

/**
 * Interfejs udostępniający metody do zarządzania rankingiem użytkowników.
 * @author Piotr Grzelak
 */
public interface IRankingService {
    
    /**
     * Dodaję punkty użytkownikowi o wskazanym identyfikatorze.
     * @param userId identyfikator użytkownika
     * @param points liczba punktów do dodania
     */
    public void addPointsToUser(int userId, int points);
    
    /**
     * Pobiera pozycję użytkownika o wskazanym identyfikatorze w rankingu.
     * @param userId identyfikator użytkownika 
     * @return pozycja użytkownika w rankingu
     */
    int getUserPosition(int userId);
    
    /**
     * Pobiera cały ranking.
     * @return lista rankingowa
     */
    List<Ranking> getRanking();
}
