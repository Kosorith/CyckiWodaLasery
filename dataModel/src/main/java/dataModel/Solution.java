package dataModel;

public class Solution {
	private int challengeId;
	private int secretPassword;
	
	public Solution(int challengeId, int secretPassword) {
		this.challengeId = challengeId;
		this.secretPassword = secretPassword;
	}
	
	public int getChallengeId() {
		return challengeId;
	}
	public void setChallengeId(int challengeId) {
		this.challengeId = challengeId;
	}
	public int getSecretPassword() {
		return secretPassword;
	}
	public void setSecretPassword(int secretPassword) {
		this.secretPassword = secretPassword;
	}
}
