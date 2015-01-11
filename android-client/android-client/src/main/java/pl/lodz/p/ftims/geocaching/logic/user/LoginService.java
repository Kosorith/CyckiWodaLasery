package pl.lodz.p.ftims.geocaching.logic.user;

import pl.lodz.p.ftims.geocaching.model.Credentials;
import pl.lodz.p.ftims.geocaching.model.Profile;

/**
 * Usługa realizująca operacje związane z logowaniem i ogólniej kontem użytkownika.
 * @author Michał Sośnicki, Andrzej Kurczewski
 */
public interface LoginService {

    /**
     * Zwraca dane logowania obecnie zalogowanego użytkownika bądź null.
     * @return Aktualne dane logowania.
     */
    Credentials getCurrentCredentials();

    /**
     * Metoda przyjmuje dane logowania i flagę oznaczającą, czy ma je zapamiętać i zwraca
     * czy zalogowanie zakończyło się sukcesem.
     * @param credentials Dane logowania użytkownika.
     * @param remember Flaga oznaczająca czy dane mają zostać zachowane.
     * @return True jeżeli logowanie się powiodło, inaczej false.
     */
    boolean login(Credentials credentials, boolean remember);

    /**
     * Metoda usuwa dane o aktualnie zalogowanym użytkowniku.
     */
    void logout();

    /**
     * Metoda zwraca zapamiętane dane logowania, bądź null jeżeli ich nie ma.
     * @return Zapamiętane dane, bądź null jeżeli ich nie ma.
     */
    Credentials getRememberedCredentials();

    /**
     * Metoda wysyła dane logowania i profil, próbując je zarejestrować e
     * nowego użytkownika.
     * @param credentials Dane logowania nowego użytkownika.
     * @param profile Profil nowego użytkownika.
     * @return True jeżeli rejestracja się powiodła.
     */
    boolean register(Credentials credentials, Profile profile);

    /**
     * Metoda przyjmuje stare i nowe hasło użytkownika i próbuje je zmienić na serwerze.
     * Ktoś musi być zalogowany w tej usłudze.
     * @param oldPassword
     * @param newPassword
     * @return True jeżeli hasło zostało zmienione.
     */
    boolean changePassword(String oldPassword, String newPassword);

    /**
     * Metoda dokonuje podstawowych testów danych i zwraca false, jeżeli
     * nie mają w ogóle szansy przejść.
     * @param credentials Dane logowania do sprawdzenia.
     * @return True jeżeli jest w ogóle sens wysyłać te dane na serwer.
     */
    boolean preverifyCredentials(Credentials credentials);

}
