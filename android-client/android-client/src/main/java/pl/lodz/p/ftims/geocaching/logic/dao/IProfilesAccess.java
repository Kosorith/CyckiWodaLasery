package pl.lodz.p.ftims.geocaching.logic.dao;

import dataModel.Credentials;


/**
 *
 * @author Tobiasz_Kowalski
 */
public interface IProfilesAccess {
    
	Credentials verifyCredentials();
	
	void changeCredentials(credentials: Credentials);
	
	void pickRanking();
	
	void pickUserProfile();
	
	void sendNewUserCredentials();
}
