package dataModel;

/**
 * Klasa DTO zawierająca dane potrzebne do utworzenia profilu. 
 * @author Piotr Grzelak
 */
public class NewUserData {

    private String login = "";

    private String password = "";

    private String nick = "";

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
