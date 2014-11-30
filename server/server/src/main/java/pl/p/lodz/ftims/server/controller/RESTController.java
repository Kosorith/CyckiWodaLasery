/*package pl.p.lodz.ftims.server.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.p.lodz.ftims.server.controllerDataModel.*;
import pl.p.lodz.ftims.server.entities.Challenge;
import pl.p.lodz.ftims.server.entities.Ranking;
import pl.p.lodz.ftims.server.entities.User;
import pl.p.lodz.ftims.server.logic.IAuthenticationService;
import pl.p.lodz.ftims.server.logic.IChallengeService;
import pl.p.lodz.ftims.server.logic.IRankingService;
import pl.p.lodz.ftims.server.logic.IUserProfileService;

*//**
 * Kontroler umożliwiający komunikację serwera z klientem.
 * @author Przemysław Holak
 *//*

@RestController
@RequestMapping("/rest")
public class RESTController {
	
	private IChallengeService challengeService;
	private IAuthenticationService authenticationService;
	private IRankingService rankingService;
	private IUserProfileService userProfileService;
	
	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<ChallengeRequest> temp(@RequestBody ChallengeRequest challengeRequest){
		challengeRequest.setChallengeId(5);
		return new ResponseEntity<ChallengeRequest>(challengeRequest, HttpStatus.OK);		
	}

	
	<ChallengeRequest>
		<challengeId>6</challengeId>
		<challengePassword>aaa</challengePassword>
	</ChallengeRequest>
	
	
	*//**
	 * Metoda odpowiadająca za pobranie wyzwania.
	 * @param challengeRequest
	 * @return ChallengeReply
	 *//*
	@RequestMapping(value = "/challenge", method=RequestMethod.POST, consumes=MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<ChallengeRequest> getChallengeRest(@RequestBody ChallengeRequest challengeRequest){		
		Challenge challenge=challengeService.getChallenge(challengeRequest.getChallengeId(), challengeRequest.getChallengePassword());
		return new ResponseEntity<ChallengeResponse>(challenge, HttpStatus.OK);
	}
	
	*//**
	 * Metoda odpowiadająca za pobranie listy wyzwań.
	 * @param challengeListRequest
	 * @return ChallenListReply
	 *//*
	@RequestMapping(value = "/challenges",method=RequestMethod.POST, consumes=MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<ChallengeListRequest> getChallengeListRest(@RequestBody ChallengeListRequest challengeListRequest){
		ChallengeListReply challengeListReply=challengeService.getChallenges(challengeListRequest.getLocation());
		return new ResponseEntity<ChallengeListRequest>(challengeListReply, HttpStatus.OK);
	}
	
	*//**
	 * Metoda odpowiadająca za pobranie rankingu.
	 * @return List<Ranking>
	 *//*
	@RequestMapping(value = "/ranking", method=RequestMethod.GET)
	public ResponseEntity<List<Ranking>> getRankingRest(){
		List<Ranking> ranking=rankingService.getRanking();
		return new ResponseEntity<List<Ranking>>(ranking, HttpStatus.OK);
	}	
	
	*//**
	 * Metoda odpowiadająca za logowanie.
	 * @param LoginRequest
	 * @return User
	 *//*
	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<User> loginRest(@RequestBody LoginRequest loginRequest){
		String login=loginRequest.getCredentials().getLogin();
		String password=loginRequest.getCredentials().getPassword();
		User user=authenticationService.authenticateUser(login,password);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	*//**
	 * Metoda zmieniająca hasło użytkownika.
	 * @param ChangePasswordRequest
	 *//*
	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<String> changePasswordRest(@RequestBody ChangePasswordRequest changeRequest){
		userProfileService.changePassword(userId, newPasswd);
		return new ResponseEntity<String>(HttpStatus.OK);
	}	
	
	*//**
	 * Metoda tworząca nowy profil użytkownika.
	 * @param CreateUserRequest
	 *//*
	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<String> createProfileRest(@RequestBody CreateUserRequest createUserRequest){
		userProfileService.addUser(createUserRequest);		
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	*//**
	 * Metoda pobierająca profil użytkownika.
	 * @param ProfileRequest
	 * @return Profile
	 *//*
	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Profile> getProfileRest(@RequestBody ProfileRequest profileRequest){
		String login=profileRequest.getCredentials().getLogin();
		String password=profileRequest.getCredentials().getPassword();
		Profile profile=authenticationService.authenticateUser(login, password)		
		return new ResponseEntity<Profile>(HttpStatus.OK);
	}

	//todo		
	
	*//**
	 * Metoda weryfikująca rozwiązanie
	 *//*	
}
	
	
*/