package dataModel;

public class ChallengesListRequest {
	private COORD location;

	public ChallengesListRequest(COORD location) {
		this.location = location;
	}

	public COORD getLocation() {
		return location;
	}
	public void setLocation(COORD location) {
		this.location = location;
	}
	
}
