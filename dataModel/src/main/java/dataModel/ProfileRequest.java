package dataModel;

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
