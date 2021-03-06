/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.p.lodz.ftims.server.logic;

import dataModel.ChallengeRequest;
import dataModel.Coordinates;
import dataModel.KHint;
import dataModel.Solution;
import dataModel.SolutionSubmission;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.naming.AuthenticationException;
import org.apache.commons.collections.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.p.lodz.ftims.server.entities.Challenge;
import pl.p.lodz.ftims.server.entities.Hint;
import pl.p.lodz.ftims.server.entities.User;
import pl.p.lodz.ftims.server.exceptions.UserAuthenticationFailedException;
import pl.p.lodz.ftims.server.persistence.IChallengesPersistence;
import pl.p.lodz.ftims.server.persistence.IHintsPersistence;

@Service
@Transactional(rollbackFor = AuthenticationException.class, readOnly = true)
public class ChallengeService implements IChallengeService {

    private static final String challengePhotoFilenamePrefix = "challenge";
    
    private static final String hintPhotoFilenamePrefix = "hint";
    
    @Autowired
    private IChallengesPersistence challengesDAO;
    
    @Autowired
    private IHintsPersistence hintsDAO;

    @Autowired
    private IAuthenticationService authenticationService;
    
    @Autowired
    private IRankingService rankingService;
    
    @Autowired
    private IPhotosManager photosManager;

    @Override
    @Transactional(readOnly = false)
    public void createChallenge(dataModel.Challenge challengeData) throws IOException {
        Challenge challenge = new Challenge();
        challenge.setDescription(challengeData.getDescription());
        challenge.setLocation(challengeData.getLocation().toString());
        challenge.setName(challengeData.getName());
        challenge.setPassword(challengeData.getPassword());
        //challenge.setPhoto(challengeData.getPhoto());
        challenge.setPoints(challengeData.getPoints());
        challenge.setSecretPassword(challengeData.getSecretPassword());
        challenge.setStatus(challengeData.getStatus());
        challenge = challengesDAO.save(challenge);

        String challengePhotoName = challengePhotoFilenamePrefix + challenge.getId();
        challenge.setPhoto(challengePhotoName);
        if (challengeData.getPhoto() != null) {
            photosManager.savePhoto(challengeData.getPhoto(), challengePhotoName);
        }
        
        for (KHint khint : challengeData.getHints()) {
            Hint hint = new Hint();
            hint.setDistance(khint.getDistance());
            //hint.setPhoto(khint.getPhoto());
            hint.setText(khint.getText());
            hint.setChallenge(challenge);
            challenge.addHint(hint);
            
            hint = hintsDAO.save(hint);
            String hintPhotoName = hintPhotoFilenamePrefix + hint.getId();
            hint.setPhoto(hintPhotoName);
            if (khint.getPhoto() != null) {
                photosManager.savePhoto(khint.getPhoto(), hintPhotoName);
            }
        }
    }

    @Override
    public List<Challenge> getChallenges(Coordinates coords) {
        List<Challenge> challenges = IteratorUtils.toList(challengesDAO.findAll().iterator());
        challenges.sort((Challenge o1, Challenge o2) -> {
            double distance1 = new Coordinates(o1.getLocation()).computeDistance(coords);
            double distance2 = new Coordinates(o2.getLocation()).computeDistance(coords);
            return (int) (distance2 - distance1);
        });
        return challenges;
    }

    @Override
    public Challenge getChallenge(ChallengeRequest request) {
        Challenge challenge = challengesDAO.findOne(request.getChallengeId());
        if (challenge == null || challenge.getPassword() == null || challenge.getPassword().equals(request.getChallengePassword())) {
            return challenge;
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = false)
    public boolean doCompleteChallenge(SolutionSubmission submission) throws UserAuthenticationFailedException {
        User user = authenticationService.authenticateUser(submission.getCredentials());
        Solution solution = submission.getSolution();
        Challenge challenge = challengesDAO.findOne(solution.getChallengeId());
        if (challenge == null) {
            return false;
        }

        if (challenge.getSecretPassword().equals(solution.getSecretPassword())) {
            rankingService.addPointsToUser(user.getId(), challenge.getPoints());
            return true;
        }
        
        return false;
    }

    @Override
    @Transactional(readOnly = false)
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
    @Transactional(readOnly = false)
    public void deleteChallenge(int challengeId) {
        challengesDAO.delete(challengeId);
    }

    @Override
    public List<Challenge> getAllChallenges() {
        return IteratorUtils.toList(challengesDAO.findAll().iterator());
    }
}
