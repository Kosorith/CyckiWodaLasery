/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.p.lodz.ftims.server.logic;

import dataModel.ChallengeRequest;
import dataModel.Coordinates;
import dataModel.Solution;
import dataModel.SolutionSubmission;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.p.lodz.ftims.server.entities.Challenge;
import pl.p.lodz.ftims.server.exceptions.UserAuthenticationFailedException;
import pl.p.lodz.ftims.server.persistence.IChallengesPersistence;

@Service
public class ChallengeService implements IChallengeService {

    @Autowired
    private IChallengesPersistence challengesDAO;

    @Autowired
    private IAuthenticationService authenticationService;

    @Autowired
    private IRankingService rankingService;

    private CollectionUtils collectionUtils = new CollectionUtils();

    @Override
    public void createChallenge(dataModel.Challenge challengeData) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Challenge> getChallenges(Coordinates coords) {
        List<Challenge> challenges = collectionUtils.iterableToList(challengesDAO.findAll());
        challenges.sort((Challenge o1, Challenge o2) -> {
            double distance1 = new Coordinates(o1.getLocation()).computeDistance(coords);
            double distance2 = new Coordinates(o2.getLocation()).computeDistance(coords);
            return (int) (distance2 - distance1);
        });
        return challenges;
    }

    @Override
    public Challenge getChallenge(ChallengeRequest request) {
        return challengesDAO.findOne(request.getChallengeId());
    }

    @Override
    public boolean doCompleteChallenge(SolutionSubmission submission) throws UserAuthenticationFailedException {
        authenticationService.authenticateUser(submission.getCredentials());
        Solution solution = submission.getSolution();
        Challenge challenge = challengesDAO.findOne(solution.getChallengeId());
        if (challenge == null) {
            return false;
        }
        return challenge.getSecretPassword().equals(solution.getSecretPassword());
    }

    @Override
    public void verifyChallenge(int challengeId, int points, boolean accepted) {
        Challenge challenge = challengesDAO.findOne(challengeId);
        challenge.setPoints(points);
        challenge.setStatus(accepted);
        challengesDAO.save(challenge);
    }

    @Override
    public boolean challengeExists(int challengeId) {
        return challengesDAO.findOne(challengeId) != null;
    }

    @Override
    public void deleteChallenge(int challengeId) {
        challengesDAO.delete(challengeId);
    }

    @Override
    public List<Challenge> getAllChallenges() {
        return collectionUtils.iterableToList(challengesDAO.findAll());
    }
}
