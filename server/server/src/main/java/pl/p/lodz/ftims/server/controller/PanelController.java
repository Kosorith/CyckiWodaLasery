package pl.p.lodz.ftims.server.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.p.lodz.ftims.server.entities.Challenge;
import pl.p.lodz.ftims.server.entities.Ranking;
import pl.p.lodz.ftims.server.entities.User;
import pl.p.lodz.ftims.server.exceptions.UserAuthenticationFailedException;
import pl.p.lodz.ftims.server.logic.IAuthenticationService;
import pl.p.lodz.ftims.server.logic.IChallengeService;
import pl.p.lodz.ftims.server.logic.IRankingService;
import pl.p.lodz.ftims.server.logic.IUserProfileService;
import pl.p.lodz.ftims.server.logic.UserProfileService;
import dataModel.Credentials;

/**
 * Kontroler umożliwiający komunikację serwera z panelem administracyjnym.
 * 
 * @author Przemysław Holak
 */

@Controller
@RequestMapping("/panel")
public class PanelController {

	@Autowired
	private IChallengeService challengeService;
	
	@Autowired
	private IAuthenticationService authenticationService;
	
	@Autowired
	private IRankingService rankingService;
	
	@Autowired
	private IUserProfileService userProfileService;
	
	private Credentials credentials;

	private static final Logger logger = Logger.getLogger(PanelController.class.getName());
	/**
	 * Metoda usuwająca użytkownika
	 */
	@RequestMapping("/users/delete")
	public ModelAndView removeUser(HttpServletRequest request) {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		Credentials userCredentials =  new Credentials(login);
		userCredentials.setPassword(password);
		try {
			userProfileService.deleteUser(userCredentials);
		} catch (UserAuthenticationFailedException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "An error occured in removeUser(): "+e.getMessage());
		}
		ModelAndView model = new ModelAndView("redirect:/panel/users");
		return model;
	}

	/**
	 * Metoda uwierzytelniająca administratora
	 */
	public ModelAndView login() {
		// authenticationService.authenticateAdministrator(login, password);
		ModelAndView model = new ModelAndView("hello");
		return model;
	}

	/**
	 * Metoda pobierająca listę wyzwań
	 */
	@RequestMapping("/challenges")
	public ModelAndView getChallenges() {

		List<Challenge> challenges = challengeService.getAllChallenges();
		ModelAndView model = new ModelAndView("challanges");
		model.addObject("challanges", challenges);
		return model;
	}

	/**
	 * Metoda pobierająca ranking
	 */
	@RequestMapping("/ranking")
	public ModelAndView getRanking() {

		List<Ranking> ranking = rankingService.getRanking();
		ModelAndView model = new ModelAndView("ranking");
		model.addObject("ranking", ranking);
		return model;
	}
	
	/**
	 * Metoda pobierająca wyzwanie 
	 * FIXME Prawdopodobnie do kosza, bo w sumie nie widze senus użycia, bardziej interesują nasz wszytkie
	 * wyzwania, niż jedno konkretne
	 */
	public ModelAndView getChallenge() {
		// dataModel.ChallengeRequest challengeId = null;
		// Challenge challenge=challengeService.getChallenge(challengeId);
		return null;
	}

	/**
	 * Metoda weryfikująca wyzwanie
	 */
	@RequestMapping("/challenges/veryfi")
	public ModelAndView verifyChallenge(HttpServletRequest request) {
		String id = request.getParameter("id");
		String points = request.getParameter("points");
		String status = request.getParameter("status");
		challengeService.verifyChallenge(Integer.parseInt(id), Integer.parseInt(points), Boolean.getBoolean(status));
		ModelAndView model = new ModelAndView("redirect:/panel/challenges");
		return model;
	}

	/**
	 * Metoda usuwająca wyzwanie
	 * 
	 * @param challengeId
	 */
	@RequestMapping("/challenges/delete")
	public ModelAndView removeChallenge(HttpServletRequest request) {
		String id = request.getParameter("id");
		challengeService.deleteChallenge(Integer.parseInt(id));
		ModelAndView model = new ModelAndView("redirect:/panel/challenges");
		return model;
	}

	/**
	 * Metoda pobierająca użytkownika
	 * FIXME Prawdopodobnie do kosza, bo w sumie nie widze senus użycia, bardziej interesują nasz wszytkie
	 * wyzwania, niż jedno konkretne
	 */
	public ModelAndView getUser() {
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
	@RequestMapping("/users")
	public ModelAndView getUsers() {
		List<User> allUsers = userProfileService.getAllUsers();
		ModelAndView model = new ModelAndView("users");
		model.addObject("users", allUsers);
		return model;
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

	public IRankingService getRankingService() {
	    return rankingService;
    }

	public void setRankingService(IRankingService rankingService) {
	    this.rankingService = rankingService;
    }
}
