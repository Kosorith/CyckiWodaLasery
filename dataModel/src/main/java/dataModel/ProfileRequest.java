package dataModel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ProfileRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProfileRequest {

    private Credentials credentials;

    public ProfileRequest(Credentials credentials) {
        this.setCredentials(credentials);
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }
}
