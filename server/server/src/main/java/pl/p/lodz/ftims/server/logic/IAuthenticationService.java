package pl.p.lodz.ftims.server.logic;

import pl.p.lodz.ftims.server.entities.User;

/**
 * Interfejs udostępniający metody związane z uwierzytelnieniem użytkowników.
 * @author Piotr Grzelak
 */
public interface IAuthenticationService {
    
    /**
     * Metoda uwierzytelnia użytkownika, w rezultacie zwraca jego profil
     * @param login login użytkownika
     * @param password hasło użytkownika
     * @return profil użytkownika
     */
    User authenticateUser(String login, String password);
    
    /**
     * Metoda uwierzytelnia administratora.
     * @param login login administratora
     * @param password hasło administratora
     * @return wartość logiczna oznaczająca powodzenie albo niepowodzenie operacji.
     */
    boolean authenticateAdministrator(String login, String password);
}
