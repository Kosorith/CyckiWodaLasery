package pl.p.lodz.ftims.server.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dataModel.ChallengeListReply;
import dataModel.ChallengeListRequest;
import dataModel.CreateUserRequest;
import dataModel.Credentials;
import dataModel.LoginRequest;
import dataModel.ChallengeRequest;
import pl.p.lodz.ftims.server.controllerDataModel.ChangePasswordRequest;
import pl.p.lodz.ftims.server.entities.Challenge;
import pl.p.lodz.ftims.server.entities.Ranking;
import pl.p.lodz.ftims.server.entities.User;
import pl.p.lodz.ftims.server.exceptions.UserAuthenticationFailedException;
import pl.p.lodz.ftims.server.logic.IAuthenticationService;
import pl.p.lodz.ftims.server.logic.IChallengeService;
import pl.p.lodz.ftims.server.logic.IRankingService;
import pl.p.lodz.ftims.server.logic.IUserProfileService;

/**
 * Kontroler umożliwiający komunikację serwera z klientem.
 * @author Przemysław Holak
 */

@RestController
@RequestMapping("/rest")
public class RESTController {
	
	private IChallengeService challengeService;
	private IAuthenticationService authenticationService;
	private IRankingService rankingService;
	private IUserProfileService userProfileService;
	
	/*	<ChallengeRequest>
	<challengeId>6</challengeId>
	<challengePassword>aaa</challengePassword>
</ChallengeRequest>
*/
	
	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<ChallengeRequest> temp(@RequestBody ChallengeRequest challengeRequest){
		challengeRequest.setChallengeId(5);
		return new ResponseEntity<ChallengeRequest>(challengeRequest, HttpStatus.OK);		
	}
	
	/**
	 * Metoda odpowiadająca za pobranie wyzwania.
	 * @param challengeRequest
	 * @return ChallengeReply
	 */
	@RequestMapping(value = "/challenge", method=RequestMethod.POST, consumes=MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Challenge> getChallengeRest(@RequestBody ChallengeRequest challengeRequest){		
		Challenge challenge=challengeService.getChallenge(challengeRequest);
		return new ResponseEntity<Challenge>(challenge, HttpStatus.OK);
	}
	
	/**
	 * Metoda odpowiadająca za pobranie listy wyzwań.
	 * @param challengeListRequest
	 * @return ChallenListReply
	 */
	@RequestMapping(value = "/challenges",method=RequestMethod.POST, consumes=MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<ChallengeListReply> getChallengeListRest(@RequestBody ChallengeListRequest challengeListRequest){
		ChallengeListReply challengeListReply = null;
		List <Challenge> challenges=challengeService.getChallenges(challengeListRequest.getLocation());
		//challengeListReply.setChallenges(//todo);
		return new ResponseEntity<ChallengeListReply>(challengeListReply, HttpStatus.OK);
	}
	
	/**
	 * Metoda odpowiadająca za pobranie rankingu.
	 * @return List<Ranking>
	 */
	@RequestMapping(value = "/ranking", method=RequestMethod.GET)
	public ResponseEntity<List<Ranking>> getRankingRest(){
		List<Ranking> ranking=rankingService.getRanking();
		return new ResponseEntity<List<Ranking>>(ranking, HttpStatus.OK);
	}	
	
	/**
	 * Metoda odpowiadająca za logowanie.
	 * @param LoginRequest
	 * @return User
	 */
	@RequestMapping(value ="/login", method=RequestMethod.POST, consumes=MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<User> loginRest(@RequestBody LoginRequest loginRequest){
		String login=loginRequest.getCredentials().getLogin();
		String password=loginRequest.getCredentials().getPassword();
		User user;
		try {
			user = authenticationService.authenticateUser(loginRequest.getCredentials());
		} catch (UserAuthenticationFailedException e) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<User>(user,HttpStatus.OK);
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
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
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
	 * Metoda pobierająca profil użytkownika.
	 * @param ProfileRequest
	 * @return Profile
	 */
/*	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Profile> getProfileRest(@RequestBody ProfileRequest profileRequest){
		Profile profile=authenticationService.authenticateUser(profileRequest.getCredentials())		
		return new ResponseEntity<Profile>(HttpStatus.OK);
	}*/

	//todo		
	
	/**
	 * Metoda weryfikująca rozwiązanie
	 */	
}
	
	
