package dataModel;

public class LoginRequest {
	private Credentials credentials;

	public LoginRequest(Credentials credentials) {
		this.credentials = credentials;
	}
	
	public Credentials getCredentials() {
		return credentials;
	}
	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}
}
