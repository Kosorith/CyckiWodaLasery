package pl.p.lodz.ftims.server.controller;

import java.util.List;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.p.lodz.ftims.server.entities.Ranking;
import pl.p.lodz.ftims.server.entities.User;
import pl.p.lodz.ftims.server.exceptions.UserAuthenticationFailedException;
import pl.p.lodz.ftims.server.logic.IAuthenticationService;
import pl.p.lodz.ftims.server.logic.IChallengeService;
import pl.p.lodz.ftims.server.logic.IRankingService;
import pl.p.lodz.ftims.server.logic.IUserProfileService;
import dataModel.Challenge;
import dataModel.ChallengeListReply;
import dataModel.ChallengeListRequest;
import dataModel.ChallengeReply;
import dataModel.ChallengeRequest;
import dataModel.ChangePasswordRequest;
import dataModel.CreateUserRequest;
import dataModel.Credentials;
import dataModel.LoginRequest;
import dataModel.Profile;
import dataModel.RankingReply;
import dataModel.SolutionSubmission;

/**
 * Kontroler umożliwiający komunikację serwera z klientem.
 * @author Przemysław Holak
 */

@RestController
@RequestMapping("/rest")
public class RESTController {
	
	@Autowired
	private IChallengeService challengeService;
	@Autowired
	private IAuthenticationService authenticationService;
	@Autowired
	private IRankingService rankingService;
	@Autowired
	private IUserProfileService userProfileService;
	@Autowired
	private IConvertManager convertManager;

	/**
	 * Metoda odpowiadająca za pobranie wyzwania.
	 * @param challengeRequest
	 * @return ChallengeReply
	 */
	@RequestMapping(value = "/challenge", method=RequestMethod.POST, consumes=MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<ChallengeReply> getChallengeRest(@RequestBody ChallengeRequest challengeRequest){		
		pl.p.lodz.ftims.server.entities.Challenge entityChallenge = challengeService.getChallenge(challengeRequest);		
		Challenge challenge=convertManager.convertToChallenge(entityChallenge);
		ChallengeReply challengeReply=new ChallengeReply(challenge);
		return new ResponseEntity<ChallengeReply>(challengeReply, HttpStatus.OK);
	}
	
	/**
	 * Metoda odpowiadająca za pobranie listy wyzwań.
	 * @param challengeListRequest
	 * @return ChallengeListReply
	 */
	@RequestMapping(value = "/challenges",method=RequestMethod.POST, consumes=MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<ChallengeListReply> getChallengeListRest(@RequestBody ChallengeListRequest challengeListRequest){
        List<pl.p.lodz.ftims.server.entities.Challenge> challenges = challengeService.getChallenges(challengeListRequest.getLocation());
		ChallengeListReply challengeListReply = convertManager.convertChallengeList(challenges);
		return new ResponseEntity<ChallengeListReply>(challengeListReply, HttpStatus.OK);
	}
	
	/**
	 * Metoda odpowiadająca za pobranie rankingu.
	 * @return List<Ranking>
	 */
	@RequestMapping(value = "/ranking", method=RequestMethod.GET)
	public ResponseEntity<RankingReply> getRankingRest() throws JAXBException{
		List<Ranking> ranking=rankingService.getRanking();
		RankingReply r=convertManager.convertRankingList(ranking);
		return new ResponseEntity<RankingReply>(r, HttpStatus.OK);
	}	
	
	/**
	 * Metoda odpowiadająca za logowanie.
	 * @param LoginRequest
	 * @return User
	 */
	@RequestMapping(value ="/login", method=RequestMethod.POST, consumes=MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Profile> loginRest(@RequestBody LoginRequest loginRequest){
		Profile profile=new Profile();
		try {
			User user = authenticationService.authenticateUser(loginRequest.getCredentials());
			profile=convertManager.convertToProfile(user);
			System.err.println();
		} catch (UserAuthenticationFailedException e) {
			return new ResponseEntity<Profile>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<Profile>(profile ,HttpStatus.OK);
	}
	
	/**
	 * Metoda zmieniająca hasło użytkownika.
	 * @param ChangePasswordRequest
	 */
	@RequestMapping(value="/password", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<String> changePasswordRest(@RequestBody ChangePasswordRequest changePasswordRequest){
		Credentials credentials=changePasswordRequest.getCredentials();
		String newPasswd=changePasswordRequest.getNewPasswd();
		try {
			userProfileService.changePassword(credentials, newPasswd);
		} catch (UserAuthenticationFailedException e) {
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}	
	
	/**
	 * Metoda tworząca nowy profil użytkownika.
	 * @param CreateUserRequest
	 */
	@RequestMapping(value="/profile", method=RequestMethod.POST, consumes=MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<String> createProfileRest(@RequestBody CreateUserRequest createUserRequest){
		userProfileService.addUser(createUserRequest);		
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	/**
	 * Metoda weryfikująca rozwiązanie
	 * @param SolutionSubsmission
	 * @return boolean
	 */	
	@RequestMapping(value="/solution", method=RequestMethod.POST, consumes=MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<String> sendResolvedChallenge(@RequestBody SolutionSubmission solutionSubmission){
		boolean bool=false;
		try {
			bool=challengeService.doCompleteChallenge(solutionSubmission);
			System.err.println();
		} catch (UserAuthenticationFailedException e) {
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
		}
		return bool==true ? new ResponseEntity<String>(HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
	}
}
	
	
