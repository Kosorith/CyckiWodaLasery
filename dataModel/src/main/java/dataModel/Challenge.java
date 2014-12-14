package dataModel;

import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Challenge")
@XmlAccessorType(XmlAccessType.FIELD)
public class Challenge {

    @XmlElement(name = "id")
    private int id;
    @XmlElement(name = "description")
    private String description;
    @XmlElement(name="photo")
    private byte[] photo;
    @XmlElement(name="hints")
    private List<KHint> hints;
    @XmlElement(name = "points")
    private int points;
    @XmlElement(name = "name")
    private String name;
/*    @Override
	public String toString() {
		return "Challenge [id=" + id + ", description=" + description
				+ ", photo=" + Arrays.toString(photo) + ", hints=" + hints
				+ ", points=" + points + ", name=" + name + ", location="
				+ location + ", password=" + password + ", secretPassword="
				+ secretPassword + ", status=" + status + "]";
	}*/

	@XmlElement(name = "coordinates")
    private Coordinates location;
    
    @XmlElement(name="password")
    private String password; // Tego raczej nie powinniście przesyłać, nie?
                             // Bo hasła idą już w ChallengeRequest i SolutionSubmission
    @XmlElement(name="secretPassword")
    private String secretPassword; // Tego też nie
    @XmlElement(name="status")
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
