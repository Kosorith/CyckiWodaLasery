package dataModel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Klasa DTO zawierająca dane potrzebne do utworzenia profilu. 
 * @author Piotr Grzelak
 */

@XmlRootElement(name = "CreateUserRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class CreateUserRequest {

    @XmlElement(name = "login")
    private String login = "";

    @XmlElement(name = "password")
    private String password = "";

    @XmlElement(name = "nick")
    private String nick = "";

    @XmlElement(name = "email")
    private String email = "";

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
