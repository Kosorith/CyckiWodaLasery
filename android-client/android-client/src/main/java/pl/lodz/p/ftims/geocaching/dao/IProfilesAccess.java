package pl.lodz.p.ftims.geocaching.dao;

import pl.lodz.p.ftims.geocaching.model.Credentials;
import pl.lodz.p.ftims.geocaching.model.Profile;
import pl.lodz.p.ftims.geocaching.model.RankingEntry;

import java.util.List;

/**
 * Intefejs zapewniajacy spojnosc danych dotyczacych profilu uzytkownika i komunikacje pomiedzy aplikacja klienta a baza danych.
 *
 * Interfejs IProfilesAccess odpowiada za obsluge profilu uzytkownika.
 *
 * @author Tobiasz_Kowalski, Jonatan_Gostynski
 */
public interface IProfilesAccess {

    /**
     * Metoda majaca zmiane hasla uzytkownika.
     *
     *
     */
	boolean changePassword(Credentials credentials, String newPassword);

    /**
     * Metoda pobierajaca ranking graczy.
     *
     *
     */
	List<RankingEntry> pickRanking();

    /**
     * Metoda tworzaca nowego gracza.
     *
     *
     */
	boolean createNewUser(Profile profile, Credentials credentials);

    /**
     * Metoda majaca na celu uwierzytelnienie uzytkownika.
     *
     *
     */
	boolean login(Credentials credentials);

    /**
     * Metoda pobierajaca pelny profil uzytkownika z serwera.
     *
     *
     */
	Profile getUserProfile(Credentials currentCredentials);

	//boolean saveUserProfile(Credentials currentCredentials, Profile profile); TODO nie ma klasy do obslugi w datamodelu

}
