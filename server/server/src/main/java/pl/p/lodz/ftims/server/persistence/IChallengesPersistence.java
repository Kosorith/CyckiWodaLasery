package pl.p.lodz.ftims.server.persistence;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import pl.p.lodz.ftims.server.entities.Challenge;

public interface IChallengesPersistence extends CrudRepository<Challenge, Integer> {

    /**
     * finding all challenges near specified location
     *
     * @param coords location near which we are looking for challenges
     * @return found challenges
     */
    List<Challenge> findByCoords(String coords);
}
