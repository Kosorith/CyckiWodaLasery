package pl.p.lodz.ftims.server.controllerDataModel;

import java.util.List;

public class ChallengeListReply {
	private List<ChallengeEntry> challenges;

	public ChallengeListReply(List<ChallengeEntry> challenges) {
		this.challenges = challenges;
	}

	public List<ChallengeEntry> getChallenges() {
		return challenges;
	}
	public void setChallenges(List<ChallengeEntry> challenges) {
		this.challenges = challenges;
	}	
}