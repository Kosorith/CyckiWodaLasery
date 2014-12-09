package pl.lodz.p.ftims.geocaching.dao;


import pl.lodz.p.ftims.geocaching.model.Credentials;
import pl.lodz.p.ftims.geocaching.model.Profile;



/**
 *
 * @author Tobiasz_Kowalski
 */
public interface IProfilesAccess {
    
	boolean verifyCredentials(Credentials credentials);
	
	void changePassword(Credentials credentials, String newPassword);
	
	
	//Ranking pickRanking();
	
	void createNewUser(Profile profile, Credentials credentials);
}
