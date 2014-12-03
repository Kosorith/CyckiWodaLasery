/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.p.lodz.ftims.server.logic;

import dataModel.Credentials;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.p.lodz.ftims.server.entities.Ranking;
import pl.p.lodz.ftims.server.entities.User;
import pl.p.lodz.ftims.server.exceptions.UserAuthenticationFailedException;
import pl.p.lodz.ftims.server.persistence.IProfilesPersistence;
import pl.p.lodz.ftims.server.persistence.IRankingPersistence;

@Service
public class RankingService implements IRankingService {

    @Autowired
    private IAuthenticationService authenticationService;

    @Autowired
    private IRankingPersistence rankingDAO;

    @Autowired
    private IProfilesPersistence profilesDAO;

    @Override
    public void addPointsToUser(int userId, int points) {
        User user = profilesDAO.findOne(userId);
        Ranking ranking = rankingDAO.findByUser(user);
        ranking.setPoints(ranking.getPoints() + points);
        ranking.setCompletedChallengesNum(ranking.getCompletedChallengesNum() + 1);
        rankingDAO.save(ranking);
    }

    @Override
    public int getUserPosition(Credentials userCredentials) throws UserAuthenticationFailedException {
        Ranking userRanking = getUserRanking(userCredentials);

        int userPosition = 1;
        Iterable<Ranking> rankings = rankingDAO.findAll();
        for (Ranking r : rankings) {
            if (userRanking.compareTo(r) > 0) {
                ++userPosition;
            }
        }

        return userPosition;
    }

    @Override
    public List<Ranking> getRanking() {
        Iterable<Ranking> rankings = rankingDAO.findAll();
        List<Ranking> rankingsList = new ArrayList<>();
        for (Ranking r : rankings) {
            rankingsList.add(r);
        }
        return rankingsList;
    }

    @Override
    public Ranking getUserRanking(Credentials userCredentials) throws UserAuthenticationFailedException {
        User user = authenticationService.authenticateUser(userCredentials);
        return rankingDAO.findByUser(user);
    }
}
