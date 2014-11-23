package persistence;
import java.util.List;


public interface IChallengesPersistence {
	
	/**
	 * creating new challenge
	 * @param challenge	new challenge to create
	 */
	void createChallenge(Challenge challenge);
	/**
	 * updating an existing challenge
	 * @param challenge challenge with new informations to update
	 * @return	updated challenge
	 */
	Challenge updateChallenge(Challenge challenge);
	/**
	 * deleting challenge with specified id
	 * @param id id of challenge to delete
	 */
	void deleteChallenge(int id);
	/**
	 * finding challenge with specified id
	 * @param id id of challenge to find
	 * @return	found challenge
	 */
	Challenge findChallenge(int id);
	/**
	 * finding all challenges near specified location
	 * @param location location near which we are looking for challenges
	 */
	List<Challenge> findChallengesByLocation(COORD location);
}
