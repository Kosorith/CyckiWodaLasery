package pl.p.lodz.ftims.server.logic;

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
    void createChallenge(/**/);
    
    /**
     * Metoda zwraca listę zadań dobranych według podanych współrzędnych geograficznych.
     * @param coords współrzędne geograficzne wg których wybierane są zadania (DTO? czy coś innego)
     * @return lista zadań (obiektów klasy @link Challenge)
     */
    List<Challenge> getChallenges(/*coords*/);
    
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
     * @param challengeId identyfiaktor zadania, które ma zostać zakończone
     * @param userId identyfikator użytkownika podejmującego próbę zakończenia wyzwania
     * @param challengePassword hasło wyzwania
     * @return wartość logiczna mówiąca czy udało się zakończyć wyzwanie
     */
    boolean doCompleteChallenge(int challengeId, int userId, String challengePassword);
    
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
}
