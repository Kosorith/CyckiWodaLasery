package pl.p.lodz.ftims.server.logic;

import dataModel.NewUserData;
import java.util.List;
import pl.p.lodz.ftims.server.entities.User;

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
    
    /**
     * Metoda odpowiedzialna za pobranie wszystkich użytkowników.
     * @return lista użytkowników
     */
    List<User> getAllUsers();
}
