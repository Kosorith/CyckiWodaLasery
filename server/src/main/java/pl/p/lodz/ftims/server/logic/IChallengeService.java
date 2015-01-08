package pl.p.lodz.ftims.server.logic;

import dataModel.ChallengeRequest;
import dataModel.Coordinates;
import dataModel.SolutionSubmission;
import java.io.IOException;
import java.util.List;
import pl.p.lodz.ftims.server.entities.Challenge;
import pl.p.lodz.ftims.server.exceptions.UserAuthenticationFailedException;

/**
 * Interfejs udostępniający metody służące do zarządzania zadaniami w grze.
 *
 * @author Piotr Grzelak
 */
public interface IChallengeService {
    
    /**
     * Metoda dodaje nowe wyzwanie na podstawie podanych danych użytkownika.
     *
     * @param challengeData - DTO przechowujące dane tworzonego zadania
     * @throws java.io.IOException rzucany w sytuacji gdy nie uda się zapis 
     * zdjęcia do zadania bądź jego wskazówek
     */
    void createChallenge(dataModel.Challenge challengeData) throws IOException;

    /**
     * Metoda zwraca listę wyzwań, posortowaną począwszy od najbliższych podanej
     * lokalizacji.
     *
     * @param coords współrzędne geograficzne wg których wybierane są zadania
     * @return lista zadań
     */
    List<Challenge> getChallenges(Coordinates coords);

    /**
     * Metoda zwraca wszystkie zadania.
     *
     * @return lista wszystkich zadań
     */
    List<Challenge> getAllChallenges();

    /**
     * Metoda zwraca zadanie odpowiadające danym wyspecyfikowanym w żadaniu.
     *
     * @param challengeRequest żądanie zadania
     * @return zadanie lub null, jeżeli hasło nie zgadza się dla prywatnego
     * zadania
     */
    Challenge getChallenge(ChallengeRequest challengeRequest);

    /**
     * Metoda odpowiedzialna za 'kończenie' wyzwania na podstawie podanego
     * zgłoszenia.
     *
     * @param submission paczka z rozwiązaniem od użytkownika
     * @return wartość logiczna mówiąca czy udało się zakończyć wyzwanie
     * @throws
     * pl.p.lodz.ftims.server.exceptions.UserAuthenticationFailedException
     * rzucany w sytuacji gdy uwierzytelnienie użytkownika zgłaszającego się nie
     * powiedzie
     */
    boolean doCompleteChallenge(SolutionSubmission submission)
            throws UserAuthenticationFailedException;

    /**
     * Metoda zmieniająca status zadania o podanym identyfikatorze
     *
     * @param challengeId identyfikator zadania, którego dotyczy zmiana
     * @param points liczba punktów przyznanyc zadaniu (w przypadku braku
     * akceptacji wyzwania wartość nie ma znaczenia)
     * @param accepted określa status wyzwania (zaakceptowane lub nie)
     */
    void verifyChallenge(int challengeId, int points, boolean accepted);

    /**
     * Sprawdza czy zadanie o podanym identyfikatorze istnieje.
     *
     * @param challengeId identyfikator zadania
     * @return wartość logiczna mówiąca czy wyzwanie istnieje
     */
    boolean challengeExists(int challengeId);

    /**
     * Metoda usuwa wyzwanie o wskazanym id.
     *
     * @param challengeId id wyzwania do usunięcia
     */
    void deleteChallenge(int challengeId);
}
