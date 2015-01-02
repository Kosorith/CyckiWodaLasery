package pl.lodz.p.ftims.geocaching.dao;

import pl.lodz.p.ftims.geocaching.model.*;
import java.util.List;

/**
 *
 * @author Tobiasz_Kowalski
 */

public interface IChallengeAccess {
    
	List<ChallengeStub> pickChallengeList(GeoCoords coords);
	
	Challenge pickChallengeHints(ChallengeStub challengestub); //brak odpowiednich klas w datamodelu
	
	Challenge pickChallengeHints(ChallengeStub challengestub, String password);
	
	boolean checkChallengeAnswer(Solution solution, Credentials credentials);

	boolean sendNewChallenge(Challenge challenge);

}




