package pl.lodz.p.ftims.geocaching.dao;

import pl.lodz.p.ftims.geocaching.model.*;
import java.util.List;

/**
 * Intefejs zapewniajacy spojnosc danych dotyczacych wyzwan i komunikacje pomiedzy aplikacja klienta a baza danych.
 *
 * Interfejs IChallengeAccess odpowiada za obsluge wyzwan udostepnionych graczowi
 *
 * @author Tobiasz_Kowalski, Jonatan_Gostynski
 */

public interface IChallengeAccess {

    /**
     * Metoda ktora wraca aktualna liste wyzwan bliskich pozycji uzytkownika
     *
     */
	List<ChallengeStub> pickChallengeList(GeoCoords coords);

    /**
     * Metoda zwracajaca wyzwanie dla wybranego obiektu klasy ChallengeStub
     *
     */
	Challenge pickChallengeHints(ChallengeStub challengestub);

    /**
     * Metoda zwracajaca wyzwanie prywatne dla wybranego obiektu klasy ChallengeStub
     *
     */
	Challenge pickChallengeHints(ChallengeStub challengestub, String password);

    /**
     * Metoda sprawdzajaca rozwiazanie dla wyzwania
     *
     */
	boolean checkChallengeAnswer(Solution solution, Credentials credentials);

    /**
     * Metoda wysylajaca nowe wyzwanie na serwer
     *
     */
	boolean sendNewChallenge(Challenge challenge); //TODO webservice nie ma odpowiedniej metody

}




