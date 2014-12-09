package pl.lodz.p.ftims.geocaching.model;

import java.util.List;

/**
 * Created by michal on 11/19/14.
 */
public class Challenge {

    private int id;
    private String name;
    private String description;
    private GeoCoords location;
    private List<Hint> hints;
//    private Image photo;  // TODO: Trzeba wybrać jakąś odpowiednią klasę
    private int points;
    private boolean publicAccess;

    public Challenge() {
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

    public List<Hint> getHints() {
        return hints;
    }

    public void setHints(List<Hint> hints) {
        this.hints = hints;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isPublicAccess() {
        return publicAccess;
    }

    public void setPublicAccess(boolean publicAccess) {
        this.publicAccess = publicAccess;
    }
}
