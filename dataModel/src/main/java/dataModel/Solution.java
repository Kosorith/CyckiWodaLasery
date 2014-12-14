package dataModel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Solution")
@XmlAccessorType(XmlAccessType.FIELD)
public class Solution {

    public Solution() {
		super();
	}

	private int challengeId;
    private String secretPassword;

    public Solution(int challengeId, String secretPassword) {
        this.challengeId = challengeId;
        this.secretPassword = secretPassword;
    }

    public int getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(int challengeId) {
        this.challengeId = challengeId;
    }

    public String getSecretPassword() {
        return secretPassword;
    }

    public void setSecretPassword(String secretPassword) {
        this.secretPassword = secretPassword;
    }
}
