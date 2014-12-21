package dataModel;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ChallengeListReply")
@XmlAccessorType(XmlAccessType.FIELD)
public class ChallengeListReply {

    @XmlElementWrapper(name="Challenges")
    @XmlElement(name="Challenge")
    private List<ChallengeEntry> challenges;

    public ChallengeListReply() {
		super();
	}

	public ChallengeListReply(List<ChallengeEntry> challenges) {
        this.challenges = challenges;
    }

    public List<ChallengeEntry> getChallenges() {
        return challenges;
    }

    public void setChallenges(List<ChallengeEntry> challenges) {
        this.challenges = challenges;
    }
}
