package dataModel;

public class ChallengesListRequest {

    private Coordinates location;

    public ChallengesListRequest(Coordinates location) {
        this.location = location;
    }

    public Coordinates getLocation() {
        return location;
    }

    public void setLocation(Coordinates location) {
        this.location = location;
    }
}
