/*package pl.p.lodz.ftims.server.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.p.lodz.ftims.server.controllerDataModel.ChallengeRequest;
import pl.p.lodz.ftims.server.entities.Challenge;
import pl.p.lodz.ftims.server.logic.IAuthenticationService;
import pl.p.lodz.ftims.server.logic.IChallengeService;
import pl.p.lodz.ftims.server.logic.IRankingService;
import pl.p.lodz.ftims.server.logic.IUserProfileService;

*//**
 * Kontroler umożliwiający komunikację serwera z panelem administracyjnym.
 * @author Przemysław Holak
 *//*

@Controller
public class PanelController {
	
	private IChallengeService challengeService;
	private IAuthenticationService authenticationService;
	private IRankingService rankingService;
	private IUserProfileService userProfileService;
	
	*//**
	 * Metoda usuwająca użytkownika
	 *//*
	public removeUser(){
		userProfileService.deleteUser(Id);
	}
	*//**
	 * Metoda uwierzytelniająca administratora
	 *//*
	public login(){
		authenticationService.authenticateAdministrator(login, password);
	}
	
	*//**
	 * Metoda pobierająca listę wyzwań
	 *//*
	public getChallenges(){
		List<Challenge> challenges=challengeService.getChallenges();
	}
	
	*//**
	 * Metoda pobierająca wyzwanie
	 *//*
	public getChallenge(){
		Challenge challenge=challengeService.getChallenge(challengeId, passwd);
	}
	
	*//**
	 * Metoda weryfikująca wyzwanie
	 *//*
	public verifyChallenge(){
		challengeService.verifyChallenge(challengeId, points, accepted);
	}
	
	*//**
	 * Metoda usuwająca wyzwanie
	 *//*
	public removeChallenge(){
		challengeService.deleteChallenge(challengeId);
	}
	
	
	*//**
	 * Metoda pobierająca użytkownika
	 *//*
	public getUser(){
		authenticationService.authenticateUser(credentials);
	}
	
	*//**
	 * Metoda pobierająca listę użytkowników
	 *//*	
	public getUsers(){
		userProfileService.getAllUsers();
	}
}
*/