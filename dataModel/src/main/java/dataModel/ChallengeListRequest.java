package dataModel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ChallengeListRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class ChallengeListRequest {

    public ChallengeListRequest() {
		super();
	}

	@XmlElement(name = "Location")
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
