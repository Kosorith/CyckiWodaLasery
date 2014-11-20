package pl.p.lodz.ftims.server.logic;

/**
 * Interfejs udostępniający operacje na profilach użytkowników gry.
 * @author Piotr Grzelak
 */
public interface IUserProfileService {
    
    /**
     * Metoda dodaję nowego użytkownika na podstawie otrzymanych danych.
     * @param userData - DTO, które powstanie
     */
    void addUser(/* userData*/);
    
    /**
     * Metoda usuwa użytkownika o wskazanym identyfikatorze.
     * @param userId użytkownik o wskazanym identyfikatorze
     */
    void deleteUser(int userId);
    
    /**
     * Metoda zmienia hasło użytkownika o podanym identyfikatorze.
     * @param userId identyfikator użytkownika
     * @param newPasswd nowe hasło użytkownika
     */
    void changePassword(int userId, String newPasswd);
}
