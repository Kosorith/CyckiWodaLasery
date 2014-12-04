package dataModel;

import java.util.List;

public class Challenge {

    private int id;
    private String description;
    private byte[] photo;
    private List<KHint> hints;
    private int points;
    private String name;
    private Coordinates location;

    public Challenge(int id, String desciption, byte[] photo, List<KHint> hints, int point, String name, Coordinates location) {
        this.id = id;
        this.description = desciption;
        this.photo = photo;
        this.hints = hints;
        this.points = point;
        this.name = name;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public List<KHint> getHints() {
        return hints;
    }

    public void setHints(List<KHint> hints) {
        this.hints = hints;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getLocation() {
        return location;
    }

    public void setLocation(Coordinates location) {
        this.location = location;
    }
}
