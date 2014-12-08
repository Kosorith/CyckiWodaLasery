package pl.p.lodz.ftims.server.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dataModel.Coordinates;
import dataModel.Credentials;
import pl.p.lodz.ftims.server.controllerDataModel.ChallengeRequest;
import pl.p.lodz.ftims.server.entities.Challenge;
import pl.p.lodz.ftims.server.exceptions.UserAuthenticationFailedException;
import pl.p.lodz.ftims.server.logic.IAuthenticationService;
import pl.p.lodz.ftims.server.logic.IChallengeService;
import pl.p.lodz.ftims.server.logic.IRankingService;
import pl.p.lodz.ftims.server.logic.IUserProfileService;

/**
 * Kontroler umożliwiający komunikację serwera z panelem administracyjnym.
 * @author Przemysław Holak
 */

@Controller
@RequestMapping("/panel")
public class PanelController {
	
	private IChallengeService challengeService;
	private IAuthenticationService authenticationService;
	private IRankingService rankingService;
	private IUserProfileService userProfileService;
	private Credentials credentials;
	
	/*
	 * Metoda usuwająca użytkownika
	 */
	public ModelAndView removeUser(){
		try {
			userProfileService.deleteUser(credentials);
		} catch (UserAuthenticationFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Metoda uwierzytelniająca administratora
	 */
	@RequestMapping("/login")
	public String login(Model model){
		//authenticationService.authenticateAdministrator(login, password);
		System.out.println("chuj");
		ModelAndView model1 = new ModelAndView("hello");
		return "hello";
	}
	
	/**
	 * Metoda pobierająca listę wyzwań
	 */
	public ModelAndView getChallenges(){
		Coordinates coords = null;
		List<Challenge> challenges=challengeService.getChallenges(coords);
		return null;
	}
	
	/**
	 * Metoda pobierająca wyzwanie
	 */
	public ModelAndView getChallenge(/*@RequestParam("challengeId") int challengeId*/){
		dataModel.ChallengeRequest challengeId = null;
		Challenge challenge=challengeService.getChallenge(challengeId);
		return null;
	}
	
	/**
	 * Metoda weryfikująca wyzwanie
	 */
	public ModelAndView verifyChallenge(@RequestParam("challengeId") int challengeId){
		return null;
		//challengeService.verifyChallenge(challengeId, points, accepted);
	}
	
	/**
	 * Metoda usuwająca wyzwanie
	 * @param challengeId 
	 */
	public ModelAndView removeChallenge(@RequestParam("challengeId") int challengeId){
		challengeService.deleteChallenge(challengeId);
		return null;
	}
	
	
	/**
	 * Metoda pobierająca użytkownika
	 */
	public ModelAndView getUser(){
		try {
			authenticationService.authenticateUser(credentials);
		} catch (UserAuthenticationFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Metoda pobierająca listę użytkowników
	 */
	public ModelAndView getUsers(){
		userProfileService.getAllUsers();
		return null;
	}
	
	
	
	
	
	
	public Credentials getCredentials() {
		return credentials;
	}
	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}
	public IUserProfileService getUserProfileService() {
		return userProfileService;
	}
	public void setUserProfileService(IUserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}
	public IAuthenticationService getAuthenticationService() {
		return authenticationService;
	}
	public void setAuthenticationService(IAuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}
	public IChallengeService getChallengeService() {
		return challengeService;
	}
	public void setChallengeService(IChallengeService challengeService) {
		this.challengeService = challengeService;
	}
}
