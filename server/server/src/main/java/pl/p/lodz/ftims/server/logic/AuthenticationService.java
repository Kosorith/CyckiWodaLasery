/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.p.lodz.ftims.server.logic;

import dataModel.Credentials;
import java.security.NoSuchAlgorithmException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.p.lodz.ftims.server.entities.Administrator;
import pl.p.lodz.ftims.server.entities.User;
import pl.p.lodz.ftims.server.exceptions.UserAuthenticationFailedException;
import pl.p.lodz.ftims.server.persistence.AdminRepository;
import pl.p.lodz.ftims.server.persistence.IProfilesPersistence;

@Service
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    private IProfilesPersistence profilesDAO;

    @Autowired
    private AdminRepository adminDAO;
    
    @Autowired
    private AuthenticationUtils authenticationUtils;

    @Override
    public User authenticateUser(Credentials credentials) throws UserAuthenticationFailedException {
        try {
            String passwordDigest = authenticationUtils.generateDigest(credentials.getPassword());
            User user = profilesDAO.findByLoginAndPassword(credentials.getLogin(), passwordDigest);
            if (user == null) {
                throw new UserAuthenticationFailedException();
            }
            return user;
        } catch (NoSuchAlgorithmException ex) {
            throw new UserAuthenticationFailedException();
        }
    }

    @Override
    public boolean authenticateAdministrator(String login, String password) {
        try {
            Administrator administrator = adminDAO.findByLoginAndPassword(login, authenticationUtils.generateDigest(password));
            return administrator != null;
        } catch (NoSuchAlgorithmException ex) {
            return false;
        }
    }
}
