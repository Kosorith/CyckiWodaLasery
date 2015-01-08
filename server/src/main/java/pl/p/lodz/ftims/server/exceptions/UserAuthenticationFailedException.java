/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.p.lodz.ftims.server.exceptions;

/**
 * Wyjątek rzucany w sytuacji niepowodzenia uwierzytelnienia użytkownika.
 * @author Piotr Grzelak
 */
public class UserAuthenticationFailedException extends Exception {

    /**
     * Creates a new instance of <code>UserAuthenticationFailedException</code>
     * without detail message.
     */
    public UserAuthenticationFailedException() {
        super("Authentication has failed");
    }

    public UserAuthenticationFailedException(Throwable cause) {
        super(cause);
    }
}
