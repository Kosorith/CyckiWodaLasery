package pl.p.lodz.ftims.server.logic;

import dataModel.Coordinates;
import dataModel.Solution;
import java.util.List;
import pl.p.lodz.ftims.server.entities.Challenge;

/**
 * Interfejs udostępniający metody służące do zarządzania zadaniami w grze.
 * @author Piotr Grzelak
 */
public interface IChallengeService {
    
    /**
     * Metoda dodaje nowe wyzwanie na podstawie podanych danych użytkownika.
     * @param challengeData - DTO do zrobienia
     */
    void createChallenge(dataModel.Challenge challengeData);
    
    /**
     * Metoda zwraca listę zadań dobranych według podanych współrzędnych geograficznych.
     * @param coords współrzędne geograficzne wg których wybierane są zadania 
     * @return lista zadań (obiektów klasy @link Challenge)
     */
    List<Challenge> getChallenges(Coordinates coords);
    
    /**
     * Metoda zwraca zadanie o podanym identyfikatorze. Hasło jest opcjonalne,
     * dotyczy zadań prywatnych.
     * @param challengeId identyfikator zadania
     * @param passwd hasło do zadania, opcjonalne
     * @return zadanie
     */
    Challenge getChallenge(int challengeId, String passwd);
    
    /**
     * Metoda odpowiedzialna za 'kończenie' wyzwania o podanym identyfikatorze przez
     * użytkownika o danym ID
     * @param solution podane rozwiązanie do zadania
     * @param userId identyfikator użytkownika podejmującego próbę zakończenia wyzwania
     * @return wartość logiczna mówiąca czy udało się zakończyć wyzwanie
     */
    boolean doCompleteChallenge(Solution solution, int userId);
    
    /**
     * Metoda zmieniająca status zadania o podanym identyfikatorze
     * @param challengeId identyfikator zadania, którego dotyczy zmiana
     * @param points liczba punktów przyznanyc zadaniu 
     * (w przypadku braku akceptacji wyzwania wartość nie ma znaczenia) 
     * @param accepted określa status wyzwania (zaakceptowane lub nie)
     */
    void verifyChallenge(int challengeId, int points, boolean accepted);
    
    /**
     * Sprawdza czy zadanie o podanym identyfikatorze istnieje.
     * @param challengeId identyfikator zadania
     * @return wartość logiczna mówiąca czy wyzwanie istnieje
     */
    boolean challengeExists(int challengeId);
    
    /**
     * Metoda usuwa wyzwanie o wskazanym id.
     * @param challengeId id wyzwania do usunięcia
     */
    void deleteChallenge(int challengeId);
}
