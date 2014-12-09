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
    private String password; // Tego raczej nie powinniście przesyłać, nie?
                             // Bo hasła idą już w ChallengeRequest i SolutionSubmission
    private String secretPassword; // Tego też nie
    private boolean status; // Myślę, że klient nie potrzebuje tej informacji od serwera - po prostu odrzucajcie Solution do już zrobionych Challenge 

    public Challenge() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getSecretPassword() {
        return secretPassword;
    }

    public void setSecretPassword(String secretPassword) {
        this.secretPassword = secretPassword;
    }
}
