package dataModel;

public class Ranking{
    
    private int id;
    
    public Ranking() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getCompletedChallengesNum() {
		return completedChallengesNum;
	}

	public void setCompletedChallengesNum(int completedChallengesNum) {
		this.completedChallengesNum = completedChallengesNum;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	private int points;
    
    private int completedChallengesNum;
    
    private Profile profile;
}

