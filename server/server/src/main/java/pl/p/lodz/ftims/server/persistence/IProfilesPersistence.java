package pl.p.lodz.ftims.server.persistence;

import org.springframework.data.repository.CrudRepository;
import pl.p.lodz.ftims.server.entities.User;

public interface IProfilesPersistence extends CrudRepository<User, Integer> {

    /**
     * finding user with specified login and passwd
     *
     * @param login
     * @param password
     * @return found user
     */
    User findByLoginAndPassword(String login, String password);
}
