package pl.p.lodz.ftims.server.controllerDataModel;

public class DeleteUserRequest {
	private Credentials credentials;
	private int id;
	public Credentials getCredentials() {
		return credentials;
	}
	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public DeleteUserRequest() {
		super();
	}
}
