package dataModel;

public class ChallengeEntry {
    
    private int id;
    private String name;
    private boolean publicAccess;//Zakłądam, ze publicAccess=false oznacza, iż challenge do podjęcia wymaga hasła
    private Coordinates location;
    private String description;

    public ChallengeEntry() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPublicAccess() {
        return publicAccess;
    }

    public void setPublicAccess(boolean publicAccess) {
        this.publicAccess = publicAccess;
    }

    public Coordinates getLocation() {
        return location;
    }

    public void setLocation(Coordinates location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
