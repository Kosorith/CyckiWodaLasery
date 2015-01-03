package pl.p.lodz.ftims.server.logic;

import dataModel.Credentials;
import pl.p.lodz.ftims.server.entities.User;
import pl.p.lodz.ftims.server.exceptions.UserAuthenticationFailedException;

/**
 * Interfejs udostępniający metody związane z uwierzytelnieniem użytkowników.
 * @author Piotr Grzelak
 */
public interface IAuthenticationService {
    
    /**
     * Metoda uwierzytelnia użytkownika, w rezultacie zwraca jego profil
     * @param credentials dane logowania użytkownika
     * @return profil użytkownika.
     * @throws pl.p.lodz.ftims.server.exceptions.UserAuthenticationFailedException
     * rzucany w razie niepowodzenia autentykacji
     */
    User authenticateUser(Credentials credentials) throws UserAuthenticationFailedException;
    
    /**
     * Metoda uwierzytelnia administratora.
     * @param login login administratora
     * @param password hasło administratora
     * @return wartość logiczna oznaczająca powodzenie albo niepowodzenie operacji.
     */
    boolean authenticateAdministrator(String login, String password);
}
