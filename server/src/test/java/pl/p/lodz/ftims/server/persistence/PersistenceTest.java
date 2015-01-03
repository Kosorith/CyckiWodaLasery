/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.p.lodz.ftims.server.persistence;

import java.util.List;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.p.lodz.ftims.server.entities.Challenge;
import pl.p.lodz.ftims.server.entities.User;

/**
 *
 * @author Piotr Grzelak
 */
@ContextConfiguration(locations = "/spring-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class PersistenceTest {
    
    public PersistenceTest() {
    }

    @Autowired
    IProfilesPersistence profPers;
  
    @Autowired
    IAdminPersistence admPers;
    
    @Autowired
    IRankingPersistence rankPers;
    
    @Autowired
    IChallengesPersistence challPer;
    
    
    @Test
    public void testFindAll() {
        Iterable<User> all = profPers.findAll();
        assertNotNull(all);
        int i = 0;
        for (User u : all) {
            ++i;
        }
    }
    
    @Test
    public void testFindByLoginAndPassword() {
        User user = profPers.findByLoginAndPassword("test1", "b444ac06613fc8d63795be9ad0beaf55011936ac");
        assertNotNull(user);
        assertEquals(user.getLogin(), "test1");
        assertEquals(user.getPassword(), "b444ac06613fc8d63795be9ad0beaf55011936ac");
        assertEquals(user.getNick(), "test1");
        assertEquals(user.getEmail(), "test1");
    }
    
    @Test
    public void testInsert() {
        Random r = new Random(new Random().nextLong());
        User user = new User();
        user.setLogin("cos" + r.nextInt());
        user.setPassword("pswd");
        user.setNick("n");
        user.setEmail("e");
        profPers.save(user);
        
        User user2 = new User();
        user2.setLogin("cos" + r.nextInt());
        user2.setPassword("haslo");
        user2.setNick("nff");
        user2.setEmail("eb");
        profPers.save(user2);
    }
    
    @Test
    public void testFindChallengesByCoords() {
        List<Challenge> challenges = challPer.findByLocation("10 10");
        assertNotNull(challenges);
        assertFalse(challenges.isEmpty());
    }
}
