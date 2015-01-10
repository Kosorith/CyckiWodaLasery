package dataModel;

public class Ranking{
    
    private int id;  // Jesteś pewny, że koniecznie chcesz przesłać id rekordu z bazy danych na telefon jakiegoś biednego ludka? Czy to coś innego?
    
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

	public Profile getProfile() {  // Jesteś pewny, że chcesz przesłać cały profil każdego ludka z rankingu, wraz z hasłem?
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	private int points;
    
    private int completedChallengesNum;
    
    private Profile profile;
}

