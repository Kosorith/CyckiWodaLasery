package pl.p.lodz.ftims.server.logic;

import dataModel.Credentials;
import java.util.List;
import pl.p.lodz.ftims.server.entities.Ranking;
import pl.p.lodz.ftims.server.exceptions.UserAuthenticationFailedException;

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
     * Pobiera pozycję w rankingu dla użytkownika o wskazanych danych.
     * @param userCredentials dane użytkownika, którego ranking ma zostać pobrany 
     * @return pozycja użytkownika w rankingu
     * @throws pl.p.lodz.ftims.server.exceptions.UserAuthenticationFailedException 
     * rzucany w sytuacji niepowodzenia uwierzytenienia
     */
    int getUserPosition(Credentials userCredentials) throws UserAuthenticationFailedException;
    
    /**
     * 
     * @param userCredentials
     * @return 
     * @throws pl.p.lodz.ftims.server.exceptions.UserAuthenticationFailedException 
     */
    Ranking getUserRanking(Credentials userCredentials) throws UserAuthenticationFailedException;
    
    /**
     * Pobiera cały ranking.
     * @return lista rankingowa
     */
    List<Ranking> getRanking();
}
