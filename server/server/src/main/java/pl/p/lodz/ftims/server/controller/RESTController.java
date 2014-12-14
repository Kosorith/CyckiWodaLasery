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
import dataModel.RankingReply;

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
	
	
	@RequestMapping(value="/test33", method=RequestMethod.POST, consumes=MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Challenge> temp(){
		ChallengeRequest challengeRequest=new ChallengeRequest();
		challengeRequest.setChallengeId(10);
		challengeRequest.setChallengePassword("test1");
		pl.p.lodz.ftims.server.entities.Challenge challenge = challengeService.getChallenge(challengeRequest);
		Challenge cc=new Challenge();
		cc.setDescription("dasdasdasd");
		cc.setId(challenge.getId());
		cc.setName(challenge.getName());
		cc.setPassword(challenge.getPassword());
		cc.setDescription(challenge.getDescription());
		return new ResponseEntity<Challenge>(cc, HttpStatus.OK);
		/*Challenge challenge = new Challenge();
		challenge.setDescription("dasdasddasd");*/
		//challengeRequest.setChallengeId(5);
		//return new ResponseEntity<ChallengeRequest>(challengeRequest, HttpStatus.OK);		
	}

	@RequestMapping(value = "/test2", method=RequestMethod.POST, consumes=MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<LoginRequest> temp2(@RequestBody LoginRequest lr){
		System.out.println(lr.getCredentials());
		lr.getCredentials().setLogin("dsadasdasd");
		return new ResponseEntity<LoginRequest>(lr, HttpStatus.OK);		
	}
	
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
	
	
