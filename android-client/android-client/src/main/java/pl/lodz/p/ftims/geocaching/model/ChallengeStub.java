package pl.lodz.p.ftims.geocaching.model;

/**
 * @author Michał Sośnicki, Andrzej Kurczewski
 */
public class ChallengeStub {

    private int id;
    private String name;
    private String description;
    private GeoCoords location;
    private boolean publicAccess;

    public ChallengeStub() {
    }

    public ChallengeStub(String name, String description, GeoCoords location, boolean publicAccess) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.publicAccess = publicAccess;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GeoCoords getLocation() {
        return location;
    }

    public void setLocation(GeoCoords location) {
        this.location = location;
    }

    public boolean isPublicAccess() {
        return publicAccess;
    }

    public void setPublicAccess(boolean publicAccess) {
        this.publicAccess = publicAccess;
    }
}
