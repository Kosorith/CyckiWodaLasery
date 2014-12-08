package dataModel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ChallengeRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class ChallengeRequest {

    @XmlElement(name = "challengeId")
    private int challengeId;

    @XmlElement(name = "challengePassword")
    private String challengePassword;

    public ChallengeRequest() {
    }

    public ChallengeRequest(int challengeId, String challengePassword) {
        this.challengeId = challengeId;
        this.challengePassword = challengePassword;
    }

    public int getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(int challengeId) {
        this.challengeId = challengeId;
    }

    public String getChallengePassword() {
        return challengePassword;
    }

    public void setChallengePassword(String challengePassword) {
        this.challengePassword = challengePassword;
    }
}
