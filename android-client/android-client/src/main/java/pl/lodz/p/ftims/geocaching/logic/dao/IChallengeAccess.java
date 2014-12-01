package pl.lodz.p.ftims.geocaching.logic.dao;


import dataModel.ChallengeRequest;

/**
 *
 * @author Tobiasz_Kowalski
 */
public interface IChallengeAccess {
    
	void sendChallengeRequest(ChallengeRequest request);
	
	void serializeChallengeRequest();
	
	void deserializeChallengeRequest();
	
	void pickChallengeResponse();
	
	void sendLocalization();
	
	void sendChallengeAnswer();
}
