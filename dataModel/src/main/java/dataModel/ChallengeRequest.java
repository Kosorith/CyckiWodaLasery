package dataModel;

public class ChallengeRequest {
	private int challengeId;
	private String challengePassword;

	public ChallengeRequest(int challengeId, String challengePassword) {
		this.challengeId = challengeId;
		this.challengePassword = challengePassword;
	}

	public int getChallengeId() {
		return challengeId;
	}
	public void setChallengeId(int challengeId) {
		this.challengeId = challengeId;
	}
	public String getChallengePassword() {
		return challengePassword;
	}
	public void setChallengePassword(String challengePassword) {
		this.challengePassword = challengePassword;
	}
}
