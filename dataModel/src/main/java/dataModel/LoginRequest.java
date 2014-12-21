package dataModel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "LoginRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class LoginRequest {

    @XmlElement(name = "Credentials")
    private Credentials credentials;

    public LoginRequest(Credentials credentials) {
        this.credentials = credentials;
    }

    public LoginRequest() {
		super();
	}

	public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }
}
