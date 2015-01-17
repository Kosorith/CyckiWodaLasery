package pl.p.lodz.ftims.server.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.p.lodz.ftims.server.entities.Challenge;
import pl.p.lodz.ftims.server.entities.Ranking;
import pl.p.lodz.ftims.server.entities.User;
import pl.p.lodz.ftims.server.exceptions.UserAuthenticationFailedException;
import pl.p.lodz.ftims.server.logic.IAuthenticationService;
import pl.p.lodz.ftims.server.logic.IChallengeService;
import pl.p.lodz.ftims.server.logic.IRankingService;
import pl.p.lodz.ftims.server.logic.IUserProfileService;
import dataModel.Credentials;

/**
 * Kontroler umożliwiający komunikację serwera z panelem administracyjnym.
 * 
 * @author Bartłomiej Długosz
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
	 * Obsługa usuwania konkretnego użytkownika
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
	 * Obsługa uwierzytelniania administratora (przetwarzanie danych podanych w formatce logowania)
	 */
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request) {
		String login = request.getParameter("login");
		String password = request.getParameter("pass");
		ModelAndView model;
		if(authenticationService.authenticateAdministrator(login, password)) {
			HttpSession session = request.getSession(false);
			session.setAttribute("userId", login);
			model = new ModelAndView("redirect:/");
		} else {
			model = new ModelAndView("login");
			model.addObject("error", "Upss, try again");
		}
		return model;
	}
	
	/**
	 * Obsługa uwierzytelniania administratora (obsługa zapytania get, zwraca jedynie jsp'a z formatką logowania)
	 */
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView loginGET(HttpServletRequest request) {
		return new ModelAndView("login");
		
	}
	/**
	 * Obsługa strony z wyzwaniami (pobieramy listę wyzwań i przesyłamy ją w modelu do jsp'a)
	 */
	@RequestMapping("/challenges")
	public ModelAndView getChallenges() {

		List<Challenge> challenges = challengeService.getAllChallenges();
		ModelAndView model = new ModelAndView("challanges");
		model.addObject("challanges", challenges);
		return model;
	}

	/**
	 * Obsługa strony z rankingiem (pobieramy listę obiektów reprezentujących wpis w rankingu  i przesyłamy ją w modelu do jsp'a)
	 */
	@RequestMapping("/ranking")
	public ModelAndView getRanking() {

		List<Ranking> ranking = rankingService.getRanking();
		ModelAndView model = new ModelAndView("ranking");
		model.addObject("ranking", ranking);
		return model;
	}
	
	/**
	 * Obsługa strony z konkretnym wyzwaniem (pobieramy wyzwanie  i przesyłamy je w modelu do jsp'a)
	 */
	public ModelAndView getChallenge() {
		// dataModel.ChallengeRequest challengeId = null;
		// Challenge challenge=challengeService.getChallenge(challengeId);
		return null;
	}

	/**
	 * Obsługa weryfikacji wyzwanie
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
	 * Obsługa usuwania wyzwania
	 */
	@RequestMapping("/challenges/delete")
	public ModelAndView removeChallenge(HttpServletRequest request) {
		String id = request.getParameter("id");
		challengeService.deleteChallenge(Integer.parseInt(id));
		ModelAndView model = new ModelAndView("redirect:/panel/challenges");
		return model;
	}

	/**
	 * Obsługa strony z konkretnym użytkownikiem (pobieramy użytkownika i przesyłamy go w modelu do jsp'a)
	 */
	public ModelAndView getUser() {
		try {
			authenticationService.authenticateUser(credentials);
		} catch (UserAuthenticationFailedException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Obsługa strony z użytkownikami (pobieramy listę użytkowników i przesyłamy ją w modelu do jsp'a)
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
