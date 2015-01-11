package pl.lodz.p.ftims.geocaching.model;

/**
 * @author Michał Sośnicki, Andrzej Kurczewski
 */
public class RankingEntry {

    private int position;
    private String nick;
    private int points;
    private int completedChallenges;

    public RankingEntry() {
    }

    public RankingEntry(int position, String nick, int points, int completedChallenges) {
        this.position = position;
        this.nick = nick;
        this.points = points;
        this.completedChallenges = completedChallenges;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
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
