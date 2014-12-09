package pl.lodz.p.ftims.geocaching.model;

import java.util.List;

/**
 * Created by michal on 11/19/14.
 */
public class Challenge {

    private ChallengeStub stub;
    private List<Hint> hints;
//    private Image photo;  // TODO: Trzeba wybrać jakąś odpowiednią klasę
    private int points;

    public Challenge() {
    }

    public ChallengeStub getStub() {
        return stub;
    }

    public void setStub(ChallengeStub stub) {
        this.stub = stub;
    }

    public List<Hint> getHints() {
        return hints;
    }

    public void setHints(List<Hint> hints) {
        this.hints = hints;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
