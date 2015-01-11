package pl.lodz.p.ftims.geocaching.model;

import java.util.Calendar;

/**
 * @author Michał Sośnicki, Andrzej Kurczewski
 */
public class Profile {

    private String nick;
    private String email;
    private int points;
    private int completedChallenges;

    public Profile() {
    }

    public Profile(String nick, String email, int points, int completedChallenges) {
        this.nick = nick;
        this.email = email;
        this.points = points;
        this.completedChallenges = completedChallenges;
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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getCompletedChallenges() {
        return completedChallenges;
    }

    public void setCompletedChallenges(int completedChallenges) {
        this.completedChallenges = completedChallenges;
    }
}
