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
	//zmien na parametr
	@RequestMapping(value = "/Challenge", method=RequestMethod.POST, consumes=MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<ChallengeRequest> getChallengeRest(@RequestBody ChallengeRequest challengeRequest){		
		Challenge challenge=challengeService.getChallenge(challengeRequest.getChallengeId(), challengeRequest.getChallengePassword());
		return new ResponseEntity<ChallengeResponse>(challenge, HttpStatus.OK);
	}
	
	*//**
	 * Metoda odpowiadająca za pobranie listy wyzwań.
	 * @param challengeListRequest
	 * @return ChallenListReply
	 *//*
	@RequestMapping(value = "/Challenges",method=RequestMethod.POST, consumes=MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<ChallengeListRequest> getChallengeListRest(@RequestBody ChallengeListRequest challengeListRequest){
		ChallengeListReply challengesListReply=challengeService.getChallenges(challengeListRequest.getLocation());
		return new ResponseEntity<ChallengeRequest>(challengeListReply, HttpStatus.OK);
	}
	
	*//**
	 * Metoda odpowiadająca za pobranie rankingu.
	 * @return List<Ranking>
	 *//*
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Ranking>> getRankingRest(){
		List<Ranking> ranking=rankingService.getRanking();
		return new ResponseEntity<List<Ranking>>(ranking, HttpStatus.OK);
	}	
	
	
	
	
	//todo 
	
	
	
	
	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<> loginRest(@RequestBody LoginRequest loginRequest){
		String login=loginRequest.getCredentials().getLogin();
		String password=loginRequest.getCredentials().getPassword();
		authenticationService.authenticateUser(login,password);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<User> createeRest(@RequestBody ProfileRequest profileRequest){
		Profile profile=
		
		
		return new ResponseEntity<User>(HttpStatus.OK);
	}
	
	

	
	
	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<User> getProfileRest(@RequestBody ProfileRequest profileRequest){
		User user=userProfileService.
		
		
		return new ResponseEntity<User>(HttpStatus.OK);
	}
	
	
	
}
	
	
*/