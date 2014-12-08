/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.p.lodz.ftims.server.logic;

import dataModel.Credentials;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.p.lodz.ftims.server.entities.Ranking;
import pl.p.lodz.ftims.server.persistence.IRankingPersistence;

/**
 *
 * @author Piotr Grzelak
 */
@ContextConfiguration(locations = "/spring-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class RankingServiceTest {
    
    @Autowired
    private IRankingService rankingService;
    
    @Autowired
    IRankingPersistence rankPers;
    
    public RankingServiceTest() {
    }
    
    @Test
    public void testGetUserPosition() throws Exception {
        Credentials cred = new Credentials("test3");
        cred.setPassword("test3");
        int pos = rankingService.getUserPosition(cred);
        assertEquals(1, pos);
        
        cred.setLogin("test1");
        cred.setPassword("test1");
        pos = rankingService.getUserPosition(cred);
        assertEquals(4, pos);
    }
    
    @Test
    public void getUserRanking() throws Exception {
        Credentials cred = new Credentials("test1");
        cred.setPassword("test1");
        Ranking rank = rankingService.getUserRanking(cred);
        assertNotNull(rank);
        assertNotNull(rank.getUser());
        assertEquals(100, rank.getId());
        assertEquals(rank.getId(), rank.getUser().getId());
    }
    
    @Test
    public void testGetRanking() {
        List<Ranking> ranking = rankingService.getRanking();
        assertNotNull(ranking);
        assertFalse(ranking.isEmpty());
    }
    
    @Test
    public void testAddPoints() {
        rankingService.addPointsToUser(400, 5);
        Ranking rank = rankPers.findOne(400);
        assertEquals(15, rank.getPoints());
    }
}
