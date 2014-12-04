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
import pl.p.lodz.ftims.server.entities.User;
import pl.p.lodz.ftims.server.exceptions.UserAuthenticationFailedException;
import pl.p.lodz.ftims.server.persistence.IProfilesPersistence;

@Service
public class UserProfileService implements IUserProfileService {

    @Autowired
    private IProfilesPersistence profilesDAO;

    @Autowired
    private IAuthenticationService authenticationService;
    
    @Autowired
    private CollectionUtils collectionUtils;
    
    @Autowired
    private AuthenticationUtils authenticationUtils;

    private static final Logger logger = Logger.getLogger(UserProfileService.class.getName());
    
    @Override
    public void addUser(CreateUserRequest userData) {
        User user = new User();
        user.setLogin(userData.getLogin());
        user.setNick(userData.getNick());
        try {
            user.setPassword(authenticationUtils.generateDigest(userData.getPassword()));
        } catch (NoSuchAlgorithmException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        user.setEmail(userData.getEmail());

        profilesDAO.save(user);
    }

    @Override
    public void deleteUser(int userId) {
        profilesDAO.delete(userId);
    }

    @Override
    public void changePassword(Credentials userCredentials, String newPasswd)
            throws UserAuthenticationFailedException {
        try {
            User user = authenticationService.authenticateUser(userCredentials);
            user.setPassword(authenticationUtils.generateDigest(newPasswd));
            profilesDAO.save(user);
        } catch (NoSuchAlgorithmException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return collectionUtils.iterableToList(profilesDAO.findAll());
    }

    @Override
    public void deleteUser(Credentials credentials) throws UserAuthenticationFailedException {
        User user = authenticationService.authenticateUser(credentials);
        profilesDAO.delete(user.getId());
    }
}
