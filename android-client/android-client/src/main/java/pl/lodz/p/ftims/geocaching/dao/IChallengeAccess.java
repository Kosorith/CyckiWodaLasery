package pl.lodz.p.ftims.geocaching.dao;

import pl.lodz.p.ftims.geocaching.model.*;
import java.util.List;

/**
 *
 * @author Tobiasz_Kowalski
 */

public interface IChallengeAccess {
    
	List<ChallengeStub> pickChallengeList(GeoCoords coords);
	
	Challenge pickChallengeHints(ChallengeStub challengestub);
	
	Challenge pickChallengeHints(ChallengeStub challengestub, String password);
	
	//void leaveChallenge(Challenge challenge); brak metody w dataModel kt�ra by to obs�ugiwa�a
	
	boolean checkChallengeAnswer(Solution solution, Credentials credentials);
	
}




