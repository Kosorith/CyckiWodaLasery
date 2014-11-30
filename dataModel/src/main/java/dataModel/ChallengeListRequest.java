package dataModel;

public class ChallengeListRequest {

    private Coordinates location;

    public ChallengeListRequest(Coordinates location) {
        this.location = location;
    }

    public Coordinates getLocation() {
        return location;
    }

    public void setLocation(Coordinates location) {
        this.location = location;
    }
}
