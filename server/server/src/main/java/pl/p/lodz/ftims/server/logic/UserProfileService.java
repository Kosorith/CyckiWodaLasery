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
import pl.p.lodz.ftims.server.entities.User;
import pl.p.lodz.ftims.server.exceptions.UserAuthenticationFailedException;
import pl.p.lodz.ftims.server.persistence.IProfilesPersistence;

@Service
public class UserProfileService implements IUserProfileService {
    
    @Autowired
    private IProfilesPersistence profilesDAO;
    
    @Autowired
    private IAuthenticationService authenticationService;
    
    private CollectionUtils collectionUtils = new CollectionUtils();
    
    @Override
    public void addUser(CreateUserRequest userData) {        
        User user = new User();
        user.setLogin(userData.getLogin());
        user.setNick(userData.getNick());
        user.setPassword(userData.getPassword());
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
        User user = authenticationService.authenticateUser(userCredentials);
        user.setPassword(newPasswd);
        profilesDAO.save(user);
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
