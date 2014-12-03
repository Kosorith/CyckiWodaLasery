/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.p.lodz.ftims.server.logic;

import dataModel.Credentials;
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
    private IAuthenticationService AuthenticationService;
    
    @Autowired
    private IRankingPersistence rankingDAO;
    
    @Autowired
    private IProfilesPersistence profilesDAO;
    
    @Override
    public void addPointsToUser(int userId, int points) {
        User user = profilesDAO.findOne(userId);
        Ranking ranking = rankingDAO.findByUser(user);
        ranking.setPoints(ranking.getPoints() + points);
        rankingDAO.save(ranking);
    }

    @Override
    public int getUserPosition(Credentials userCredentials) throws UserAuthenticationFailedException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Ranking> getRanking() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ranking getUserRanking(Credentials userCredentials) throws UserAuthenticationFailedException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
