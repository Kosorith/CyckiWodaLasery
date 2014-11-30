package pl.p.lodz.ftims.server.controllerDataModel;

import dataModel.Credentials;

public class ChangePasswordRequest {
	
	private Credentials credentials;
	private String newPasswd;
	
	public ChangePasswordRequest() {
	}
	public Credentials getCredentials() {
		return credentials;
	}
	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}
	public String getNewPasswd() {
		return newPasswd;
	}
	public void setNewPasswd(String newPasswd) {
		this.newPasswd = newPasswd;
	}
	

}
