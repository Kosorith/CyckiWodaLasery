package pl.lodz.p.ftims.geocaching.dao;

import pl.lodz.p.ftims.geocaching.model.Credentials;
import pl.lodz.p.ftims.geocaching.model.Profile;
import pl.lodz.p.ftims.geocaching.model.RankingEntry;

import java.util.List;

/**
 *
 * @author Tobiasz_Kowalski
 */
public interface IProfilesAccess {
    
	boolean changePassword(Credentials credentials, String newPassword);
	
	List<RankingEntry> pickRanking();
	
	boolean createNewUser(Profile profile, Credentials credentials);

	boolean login(Credentials credentials);

	Profile getUserProfile(Credentials currentCredentials);

	//boolean saveUserProfile(Credentials currentCredentials, Profile profile); TODO nie ma klasy do obslugi w datamodelu

}
