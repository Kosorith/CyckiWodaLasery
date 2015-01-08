/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.p.lodz.ftims.server.logic;

import dataModel.Credentials;
import java.security.NoSuchAlgorithmException;
import javax.naming.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.p.lodz.ftims.server.entities.Administrator;
import pl.p.lodz.ftims.server.entities.User;
import pl.p.lodz.ftims.server.exceptions.UserAuthenticationFailedException;
import pl.p.lodz.ftims.server.persistence.IAdminPersistence;
import pl.p.lodz.ftims.server.persistence.IProfilesPersistence;

@Service
@Transactional(rollbackFor = AuthenticationException.class, readOnly = true)
public class AuthenticationService implements IAuthenticationService {
    
    @Autowired
    private IProfilesPersistence profilesDAO;

    @Autowired
    private IAdminPersistence adminDAO;
    
    @Autowired
    private AuthenticationUtils authenticationUtils;

    @Override
    public User authenticateUser(Credentials credentials) throws UserAuthenticationFailedException {
        try {
            String digestedPassword = authenticationUtils.generateDigest(credentials.getPassword());
            User user = profilesDAO.findByLoginAndPassword(credentials.getLogin(), digestedPassword);
            if (user == null) {
                throw new UserAuthenticationFailedException();
            }
            return user;
        } catch (NoSuchAlgorithmException ex) {
            throw new UserAuthenticationFailedException(ex);
        }
    }

    @Override
    public boolean authenticateAdministrator(String login, String password) {
        Administrator administrator = adminDAO.findByLoginAndPassword(login, password);
        return administrator != null;
    }
}
