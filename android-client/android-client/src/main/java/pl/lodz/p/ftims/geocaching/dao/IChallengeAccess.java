package pl.lodz.p.ftims.geocaching.dao;

import pl.lodz.p.ftims.geocaching.model.*;
import java.util.List;

/**
 *
 * @author Tobiasz_Kowalski
 */

public interface IChallengeAccess {
    
	List<ChallengeStub> pickChallengeList(GeoCoords coords);
	
	List<Hint> pickChallengeHints(ChallengeStub challengestub);
	
	List<Hint> pickChallengeHints(ChallengeStub challengestub, String password);
	
	//void leaveChallenge(Challenge challenge); brak metody w dataModel która by to obs³ugiwa³a
	
	boolean checkChallengeAnswer(Solution solution, Credentials credentials);
	
}




