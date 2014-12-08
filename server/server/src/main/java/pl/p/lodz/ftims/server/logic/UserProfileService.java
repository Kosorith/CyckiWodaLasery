/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.p.lodz.ftims.server.logic;

import dataModel.CreateUserRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dataModel.Credentials;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.AuthenticationException;
import org.apache.commons.collections.IteratorUtils;
import org.springframework.transaction.annotation.Transactional;
import pl.p.lodz.ftims.server.entities.Ranking;
import pl.p.lodz.ftims.server.entities.User;
import pl.p.lodz.ftims.server.exceptions.UserAuthenticationFailedException;
import pl.p.lodz.ftims.server.persistence.IProfilesPersistence;
import pl.p.lodz.ftims.server.persistence.IRankingPersistence;

@Service
@Transactional(rollbackFor = AuthenticationException.class)
public class UserProfileService implements IUserProfileService {

    @Autowired
    private IProfilesPersistence profilesDAO;
    
    @Autowired
    private IRankingPersistence rankingsDAO;

    @Autowired
    private IAuthenticationService authenticationService;

    private static final Logger logger = Logger.getLogger(UserProfileService.class.getName());
    
    @Override
    public void addUser(CreateUserRequest userData) {
        User user = new User();
        user.setLogin(userData.getLogin());
        user.setNick(userData.getNick());
        user.setPassword(userData.getPassword());
        user.setEmail(userData.getEmail());

        Ranking ranking = new Ranking();
        ranking.setUser(user);
        user.setRanking(ranking);
        
        profilesDAO.save(user);
        rankingsDAO.save(ranking);
    }

    @Override
    public void deleteUser(int userId) {
        profilesDAO.delete(userId);
    }

    @Override
    public void changePassword(Credentials userCredentials, String newPasswd)
            throws UserAuthenticationFailedException {
        User user = authenticationService.authenticateUser(userCredentials);
        user.setPassword(newPasswd);
        profilesDAO.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return IteratorUtils.toList(profilesDAO.findAll().iterator());
    }

    @Override
    public void deleteUser(Credentials credentials) throws UserAuthenticationFailedException {
        User user = authenticationService.authenticateUser(credentials);
        //Ranking ranking = rankingsDAO.findByUser(user);
        //rankingsDAO.delete(ranking);
        profilesDAO.delete(user.getId());
    }
}
