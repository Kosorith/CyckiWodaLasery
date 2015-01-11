package pl.lodz.p.ftims.geocaching.logic.user;

import pl.lodz.p.ftims.geocaching.model.Profile;

/**
 * Usługa zarządzająca profilem użytkownika.
 *
 * @author Michał Sośnicki, Andrzej Kurczewski
 */
public interface ProfilesService {

    /**
     * Zwraca profil użytkownika aktualnie zalogowanego w powiązanej usłudze logowania.
     * @return Profil zalogowanego użytkownika bądź null.
     */
    Profile getCurrentProfile();

    /**
     * Ponownie ściąga z serwera dane aktualnie zalogowanego użytkownika, wycofując zmiany wprowadzone
     * przez setCurrentProfile.
     */
    void reloadProfile();

    /**
     * Ustawia profil użytkownika na przekazany, by było możliwe jego późniejsze zapisanie.
     * @param profile Nowy profil użytkownika.
     */
    void setCurrentProfile(Profile profile);

    /**
     * Sprawdza czy podany profil ma szansę zostać zaakceptowany przez serwer.
     * @param profile Profil do sprawdzenia.
     * @return Rezultat walidacji.
     */
    boolean preverifyProfile(Profile profile);

//    DaoClient ponoć nie ma jak tego zrobić
//    boolean saveProfile();

}
