package pl.lodz.p.ftims.geocaching.model;

/**
 * @author Michał Sośnicki, Andrzej Kurczewski
 */
public class Solution {

    private String secret;
    private Challenge solved;

    public Solution() {
    }

    public Solution(String secret, Challenge solved) {
        this.secret = secret;
        this.solved = solved;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Challenge getSolved() {
        return solved;
    }

    public void setSolved(Challenge solved) {
        this.solved = solved;
    }
}
