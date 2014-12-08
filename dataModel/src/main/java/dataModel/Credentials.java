package dataModel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Credentials")
@XmlAccessorType(XmlAccessType.FIELD)
public class Credentials {

    @XmlElement(name = "login")
    private String login;

    @XmlElement(name = "password")
    private String password;

    public Credentials() {
    }

    public Credentials(String login) {
		super();
		this.login = login;
	}

	public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
