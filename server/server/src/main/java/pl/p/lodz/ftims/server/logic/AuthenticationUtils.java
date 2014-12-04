/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.p.lodz.ftims.server.logic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import org.springframework.stereotype.Component;

/**
 *
 * @author Wojciech Szałapski
 */
@Component
public class AuthenticationUtils {

    private static final String DIGEST_ALGORITHM = "SHA-1";
    
    private String bytesToHex(byte[] bytes) {
        try (Formatter formatter = new Formatter()) {
            for (byte b : bytes) {
                formatter.format("%02x", b);
            }
            return formatter.toString();
        }
    }

    public String generateDigest(String text) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(DIGEST_ALGORITHM);
        return bytesToHex(messageDigest.digest(text.getBytes()));
    }
}
