package pl.lodz.p.ftims.geocaching.dao;


import pl.lodz.p.ftims.geocaching.model.Challenge;
import pl.lodz.p.ftims.geocaching.model.GeoCoords;
import pl.lodz.p.ftims.geocaching.model.Solution;
import pl.lodz.p.ftims.geocaching.model.Hint;
import java.util.List;

/**
 *
 * @author Tobiasz_Kowalski
 */
public interface IChallengeAccess {
    
	List<ChallengeStub> pickChallengeList(GeoCoords coords);
	
	List<Hints> pickChallengeHints(ChallengeStub challengestub);
	
	List<Hints> pickChallengeHints(ChallengeStub challengestub, String password);
	
	//void leaveChallenge(Challenge challenge); brak metody w dataModel która by to obsługiwała
	
	boolean checkChallengeAnswer(Solution solution, Credentials credentials);
	
}




