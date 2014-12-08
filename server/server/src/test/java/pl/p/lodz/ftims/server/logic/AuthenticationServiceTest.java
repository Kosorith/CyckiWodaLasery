/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.p.lodz.ftims.server.logic;

import dataModel.Credentials;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.p.lodz.ftims.server.entities.Administrator;
import pl.p.lodz.ftims.server.entities.User;
import pl.p.lodz.ftims.server.exceptions.UserAuthenticationFailedException;

/**
 *
 * @author Piotr Grzelak
 */
@ContextConfiguration(locations = "/spring-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class AuthenticationServiceTest {
    
    @Autowired
    private IAuthenticationService authenticationService;
    
    public AuthenticationServiceTest() {
    }

    @Test
    public void testAuthenticateUserOk() throws Exception {
        User user = authenticationService.authenticateUser(new Credentials("test1", "test1"));
        assertNotNull(user);
    }
    
    @Test(expected = UserAuthenticationFailedException.class)
    public void testAuthenticateUserException() throws Exception {
        User user = authenticationService.authenticateUser(new Credentials("niema", "niema"));
    }
    
    @Test
    public void testAuthenticateAdminOk() throws Exception {
        boolean ok = authenticationService.authenticateAdministrator("admin", "admin");
        assertEquals(true, ok);
    }
    
    @Test
    public void testAuthenticateAdminWrong() throws Exception {
        boolean ok = authenticationService.authenticateAdministrator("niema", "admin");
        assertEquals(false, ok);
    }
}
