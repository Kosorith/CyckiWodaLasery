package dataModel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SolutionSubmission")
@XmlAccessorType(XmlAccessType.FIELD)
public class SolutionSubmission {

    private Credentials credentials;
    private Solution solution;

    public SolutionSubmission(Credentials credentials, Solution solution) {
        this.credentials = credentials;
        this.solution = solution;
    }

    public SolutionSubmission() {
		super();
	}

	public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }
}
