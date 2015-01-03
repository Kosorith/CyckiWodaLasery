/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.p.lodz.ftims.server.logic;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Piotr Grzelak
 */
@ContextConfiguration(locations = "/spring-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class AuthenticationUtilsTest {
    
    @Autowired
    private AuthenticationUtils utils;
    
    public AuthenticationUtilsTest() {
    }

    @Test
    public void testgenerateDigest() throws Exception {
        String result = utils.generateDigest("test1");
        assertEquals("b444ac06613fc8d63795be9ad0beaf55011936ac", result);
        result = utils.generateDigest("test2");
        assertEquals("109f4b3c50d7b0df729d299bc6f8e9ef9066971f", result);
        result = utils.generateDigest("test3");
        assertEquals("3ebfa301dc59196f18593c45e519287a23297589", result);
    }
    
}
