package dataModel;

public class ChallengeReply {

    private Challenge challenge;

    public ChallengeReply(Challenge challenge) {
        this.challenge = challenge;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public String getDescription() {
        return this.challenge.getDesciption();
    }

    public void setDescription(String description) {
        this.challenge.setDesciption(description);
    }
}
