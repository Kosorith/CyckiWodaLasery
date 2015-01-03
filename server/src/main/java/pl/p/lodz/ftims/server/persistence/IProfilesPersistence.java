package pl.p.lodz.ftims.server.persistence;

import org.springframework.data.repository.CrudRepository;
import pl.p.lodz.ftims.server.entities.User;

/**
 *
 * @author Piotr Grzelak
 */
public interface IProfilesPersistence extends CrudRepository<User, Integer> {

    /**
     * finding user with specified login and passwd
     *
     * @param login
     * @param password
     * @return found user
     */
    User findByLoginAndPassword(String login, String password);
    
    /**
     * Finds user with specified login.
     * @param login
     * @return found user
     */
    User findByLogin(String login);
}
