package pl.p.lodz.ftims.server.logic;

import dataModel.NewUserData;
import java.util.List;
import pl.p.lodz.ftims.server.controllerDataModel.Credentials;
import pl.p.lodz.ftims.server.entities.User;
import pl.p.lodz.ftims.server.exceptions.UserAuthenticationFailedException;

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
     * Metoda usuwa użytkownika o wskazanym identyfikatorze. Nie potrzebne dodatkowe uwierzytelnienie
     * @param userId użytkownik o wskazanym identyfikatorze
     */
    void deleteUser(int userId);
    
    /**
     * Metoda usuwa użytkownika o podanych danych logowania. Obejmuje potrzebne uwierzytelnienie.
     * @param credentials dane logowania użytkownika
     * @throws pl.p.lodz.ftims.server.exceptions.UserAuthenticationFailedException
     * rzucany w przypadku gdy uwierzytelnie się nie powiedzie
     */
    void deleteUser(Credentials credentials) throws UserAuthenticationFailedException;
    
    /**
     * Metoda zmienia hasło użytkownika o podanyc danych logowania.
     * @param userCredentials dane użytkownika, którego hasło jest zmieniane
     * @param newPasswd nowe hasło użytkownika
     * @throws pl.p.lodz.ftims.server.exceptions.UserAuthenticationFailedException 
     */
    void changePassword(Credentials userCredentials, String newPasswd)
            throws UserAuthenticationFailedException;
    
    /**
     * Metoda odpowiedzialna za pobranie wszystkich użytkowników.
     * @return lista użytkowników
     */
    List<User> getAllUsers();
}
