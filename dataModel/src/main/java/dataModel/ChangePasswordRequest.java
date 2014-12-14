package dataModel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import dataModel.Credentials;

/**
* @author Przemyslaw Holak
*/

@XmlRootElement(name = "ChangePasswordRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class ChangePasswordRequest {
	
    @XmlElement(name = "credentials")
	private Credentials credentials;
    
    @XmlElement(name = "newPasswd")
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

