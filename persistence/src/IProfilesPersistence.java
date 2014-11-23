package persistence;

public interface IProfilesPersistence {
	/**
	 * Creating new user
	 * @param user	new user to create
	 */
	void createUser(User user);
	/**
	 * update informations about user
	 * @param user user with new informations to update
	 * @return	updated user
	 */
	User updateUser(User user);
	/**
	 * deleting user with specified id
	 * @param id id of user to delete
	 */
	void deleteUser(int id);
	/**
	 * finding user with specified id
	 * @param id id of user to find
	 * @return	found user
	 */
	User findUser(int id);
}
