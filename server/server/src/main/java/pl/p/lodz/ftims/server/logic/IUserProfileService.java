package pl.p.lodz.ftims.server.logic;

import dataModel.NewUserData;

/**
 * Interfejs udostępniający operacje na profilach użytkowników gry.
 * @author Piotr Grzelak
 */
public interface IUserProfileService {
    
    /**
     * Metoda dodaję nowego użytkownika na podstawie otrzymanych danych.
     * @param userData - DTO, które powstanie
     */
    void addUser(NewUserData userData);
    
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
