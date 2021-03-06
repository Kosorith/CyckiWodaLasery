/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.p.lodz.ftims.server.logic;

import dataModel.ChallengeRequest;
import dataModel.Coordinates;
import dataModel.Credentials;
import dataModel.KHint;
import dataModel.Solution;
import dataModel.SolutionSubmission;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.p.lodz.ftims.server.entities.Challenge;
import pl.p.lodz.ftims.server.exceptions.UserAuthenticationFailedException;
import static pl.p.lodz.ftims.server.logic.IPhotosManager.PHOTOS_DIR;

/**
 *
 * @author Piotr Grzelak
 */
@ContextConfiguration(locations = "/spring-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ChallengeServiceTest {

    @Autowired
    IChallengeService challengeService;

    public ChallengeServiceTest() {
    }

    @BeforeClass
    public static void createImgDir() throws IOException {
        if (Files.notExists(Paths.get(PHOTOS_DIR), LinkOption.NOFOLLOW_LINKS)) {
            Files.createDirectory(Paths.get(PHOTOS_DIR));
        }
    }

    @AfterClass
    public static void deleteImgDir() throws IOException {
        Stream<Path> stream = Files.list(Paths.get(PHOTOS_DIR));
        stream.forEach((Path p) -> {
            try {
                Files.delete(p);
            } catch (IOException ex) {
                Logger.getLogger(ChallengeServiceTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Files.deleteIfExists(Paths.get(PHOTOS_DIR));
    }

    /**
     * Test of createChallenge method, of class ChallengeService.
     */
    @Test
    public void testCreateChallenge() throws Exception {
        System.out.println("createChallenge");
        dataModel.Challenge challengeData = new dataModel.Challenge();
        challengeData.setName("testowe");
        challengeData.setDescription("Opis testowy");
        challengeData.setLocation(new Coordinates(45.0, 100.0));
        challengeData.setPassword("passwd");
        challengeData.setPoints(20);
        challengeData.setSecretPassword("secPasswd");
        challengeData.setStatus(false);

        byte[] photo = new byte[1500000];
        Random r = new Random();
        r.nextBytes(photo);
        challengeData.setPhoto(photo);

        List<KHint> khints = new ArrayList<>(5);
        for (int i = 0; i < 5; ++i) {
            khints.add(new KHint("hint" + i, null, 10 + i));
        }
        challengeData.setHints(khints);

        challengeService.createChallenge(challengeData);
    }

    /**
     * Test of getChallenges method, of class ChallengeService.
     */
    @Test
    public void testGetChallenges() {
        List<Challenge> challenges = challengeService.getChallenges(new Coordinates(10, 10));
        assertNotNull(challenges);
        assertFalse(challenges.isEmpty());
    }

    /**
     * Test of getChallenge method, of class ChallengeService.
     */
    @Test
    public void testGetChallenge() {
        System.out.println("getChallenge");
        ChallengeRequest request = new ChallengeRequest(210, "passwd");

        Challenge challenge = challengeService.getChallenge(request);
        assertNull(challenge);

        request = new ChallengeRequest(10, "pass1");
        challenge = challengeService.getChallenge(request);

        assertNotNull(challenge);
        assertEquals(10, challenge.getId());

        request = new ChallengeRequest(10, "wrong_password");
        challenge = challengeService.getChallenge(request);
        assertNull(challenge);
    }

    @Test
    public void testDoCompleteChallengeSolutionOk() throws Exception {
        Credentials cred = new Credentials("test1");
        cred.setPassword("test1");
        Solution sol = new Solution(10, "qwe");
        SolutionSubmission solutionSub = new SolutionSubmission(cred, sol);
        boolean solved = challengeService.doCompleteChallenge(solutionSub);
        assertEquals(true, solved);
    }

    @Test
    public void testDoCompleteChallengeSolutionWrong() throws Exception {
        Credentials cred = new Credentials("test1");
        cred.setPassword("test1");
        Solution sol = new Solution(10, "zlehaslo");
        SolutionSubmission solutionSub = new SolutionSubmission(cred, sol);
        boolean solved = challengeService.doCompleteChallenge(solutionSub);
        assertEquals(false, solved);
    }

    /**
     * Test of doCompleteChallenge method, of class ChallengeService.
     */
    @Test(expected = UserAuthenticationFailedException.class)
    public void testDoCompleteChallengeAuthenticationFailed() throws Exception {
        Credentials cred = new Credentials("niema");
        cred.setPassword("niema");
        Solution sol = new Solution(10, "secret");
        SolutionSubmission solutionSub = new SolutionSubmission(cred, sol);
        challengeService.doCompleteChallenge(solutionSub);
    }

    /**
     * Test of verifyChallenge method, of class ChallengeService.
     */
    @Test
    public void testVerifyChallenge() {
        System.out.println("verifyChallenge");
        int challengeId = 10;
        int points = 20;
        boolean accepted = true;
        challengeService.verifyChallenge(challengeId, points, accepted);
    }

    /**
     * Test of challengeExists method, of class ChallengeService.
     */
    @Test
    public void testChallengeExists() {
        System.out.println("challengeExists");
        int challengeId = 0;
        boolean expResult = false;
        boolean result = challengeService.challengeExists(challengeId);
        assertEquals(expResult, result);

        challengeId = 10;
        expResult = true;
        result = challengeService.challengeExists(challengeId);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteChallenge method, of class ChallengeService.
     */
    @Test
    public void testDeleteChallenge() {
        System.out.println("deleteChallenge");
        int challengeId = 50;
        challengeService.deleteChallenge(challengeId);
    }

    /**
     * Test of getAllChallenges method, of class ChallengeService.
     */
    @Test
    public void testGetAllChallenges() {
        System.out.println("getAllChallenges");
        List<Challenge> challenges = challengeService.getAllChallenges();
        assertNotNull(challenges);
        assertFalse(challenges.isEmpty());
    }
}
