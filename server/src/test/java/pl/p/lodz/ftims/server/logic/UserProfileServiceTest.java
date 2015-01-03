/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.p.lodz.ftims.server.logic;

import dataModel.CreateUserRequest;
import dataModel.Credentials;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.p.lodz.ftims.server.entities.User;
import pl.p.lodz.ftims.server.exceptions.UserAuthenticationFailedException;
import pl.p.lodz.ftims.server.persistence.IProfilesPersistence;

/**
 *
 * @author Piotr Grzelak
 */
@ContextConfiguration(locations = "/spring-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserProfileServiceTest {
    
    @Autowired
    IUserProfileService userProfileService;
    
    @Autowired
    IProfilesPersistence profilesPersistence;
    
    public UserProfileServiceTest() {
    }

    @Test
    public void testAddUserOk() {
        CreateUserRequest data = new CreateUserRequest();
        data.setLogin("nowy");
        data.setPassword("nowe");
        data.setEmail("nowy@nowy.pl");
        data.setNick("nowynick");
        boolean res = userProfileService.addUser(data);
        assertTrue(res);
        
        User user = profilesPersistence.findByLoginAndPassword("nowy", "0a8d71b07d004c55ccb249c93fdd477f3bde13a3");
        assertNotNull(user);
    }
    
    @Test
    public void testAddUserFailed() {
        CreateUserRequest data = new CreateUserRequest();
        data.setLogin("test1");
        data.setPassword("nowe");
        data.setEmail("nowy@nowy.pl");
        data.setNick("nowynick");
        boolean res = userProfileService.addUser(data);
        assertFalse(res);
    }
    
    @Test
    public void testChangePassword() throws Exception {
        Credentials cred = new Credentials("test2");
        cred.setPassword("test2");
        userProfileService.changePassword(cred, "nowehaslo");
    }
    
    @Test(expected = UserAuthenticationFailedException.class)
    public void testChangePasswordFailed() throws Exception {
        Credentials cred = new Credentials("test12456");
        cred.setPassword("test2");
        userProfileService.changePassword(cred, "nowehaslo");
        
    }
    
    @Test
    public void deleteByCredentialsOk() throws Exception {
        Credentials cred = new Credentials("test5");
        cred.setPassword("test5");
        userProfileService.deleteUser(cred);
    }
    
    @Test(expected = UserAuthenticationFailedException.class)
    public void deleteByCredentialsFailed() throws Exception {
        Credentials cred = new Credentials("test234321344");
        cred.setPassword("test214245");
        userProfileService.deleteUser(cred);
    }
    
    @Test
    public void testGetAllUsers() {
        List<User> users = userProfileService.getAllUsers();
        assertNotNull(users);
        assertFalse(users.isEmpty());
    }
}
