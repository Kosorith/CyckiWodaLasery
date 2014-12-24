package pl.lodz.p.ftims.geocaching.dao;

import pl.lodz.p.ftims.geocaching.model.Credentials;
import pl.lodz.p.ftims.geocaching.model.Profile;

/**
 *
 * @author Tobiasz_Kowalski
 */
public interface IProfilesAccess {
    
	boolean changePassword(Credentials credentials, String newPassword);
	
	//Ranking pickRanking();
	
	boolean createNewUser(Profile profile, Credentials credentials);

	boolean login(Credentials credentials);

	Profile getUserProfile(Credentials currentCredentials);

	//boolean saveUserProfile(Credentials currentCredentials, Profile profile); nie ma klasy do obslugi w datamodelu

}
