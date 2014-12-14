package dataModel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ChallengeReply")
@XmlAccessorType(XmlAccessType.FIELD)
public class ChallengeReply {

    private Challenge challenge;

    public ChallengeReply() {
		super();
	}

	public ChallengeReply(Challenge challenge) {
        this.challenge = challenge;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public String getDescription() {
        return this.challenge.getDescription();
    }

    public void setDescription(String description) {
        this.challenge.setDescription(description);
    }
}
