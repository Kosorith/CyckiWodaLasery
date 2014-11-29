package dataModel;

public class Solution {

    private int challengeId;
    private String secretPassword;

    public Solution(int challengeId, String secretPassword) {
        this.challengeId = challengeId;
        this.secretPassword = secretPassword;
    }

    public int getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(int challengeId) {
        this.challengeId = challengeId;
    }

    public String getSecretPassword() {
        return secretPassword;
    }

    public void setSecretPassword(String secretPassword) {
        this.secretPassword = secretPassword;
    }
}
