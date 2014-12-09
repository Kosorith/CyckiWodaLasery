package pl.lodz.p.ftims.geocaching.model;

import java.util.Calendar;

/**
 * Created by michal on 11/19/14.
 */
public class Profile {

    private String nick;
    private String email;

    public Profile() {
    }

    public Profile(String nick, String email) {
        this.nick = nick;
        this.email = email;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
