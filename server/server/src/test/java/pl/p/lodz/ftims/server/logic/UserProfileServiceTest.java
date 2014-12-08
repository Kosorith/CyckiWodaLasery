/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.p.lodz.ftims.server.logic;

import dataModel.CreateUserRequest;
import dataModel.Credentials;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.p.lodz.ftims.server.entities.User;
import pl.p.lodz.ftims.server.exceptions.UserAuthenticationFailedException;

/**
 *
 * @author Piotr Grzelak
 */
@ContextConfiguration(locations = "/spring-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserProfileServiceTest {
    
    @Autowired
    IUserProfileService userProfileService;
    
    public UserProfileServiceTest() {
    }

    @Test
    public void testAddUser() {
        CreateUserRequest data = new CreateUserRequest();
        data.setLogin("nowy");
        data.setPassword("nowe");
        data.setEmail("nowy@nowy.pl");
        data.setNick("nowynick");
        userProfileService.addUser(data);
    }
    
    @Test
    public void testChangePassword() throws Exception {
        Credentials cred = new Credentials("test2", "test2");
        userProfileService.changePassword(cred, "nowehaslo");
    }
    
    @Test(expected = UserAuthenticationFailedException.class)
    public void testChangePasswordFailed() throws Exception {
        Credentials cred = new Credentials("test12456", "test2");
        userProfileService.changePassword(cred, "nowehaslo");
        
    }
    
    @Test
    public void deleteByCredentialsOk() throws Exception {
        Credentials cred = new Credentials("test5", "test5");
        userProfileService.deleteUser(cred);
    }
    
    @Test(expected = UserAuthenticationFailedException.class)
    public void deleteByCredentialsFailed() throws Exception {
        Credentials cred = new Credentials("test234321344", "test214245");
        userProfileService.deleteUser(cred);
    }
    
    @Test
    public void testGetAllUsers() {
        List<User> users = userProfileService.getAllUsers();
        assertNotNull(users);
        assertFalse(users.isEmpty());
    }
}
