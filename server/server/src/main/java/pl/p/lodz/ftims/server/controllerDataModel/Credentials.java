package pl.p.lodz.ftims.server.controllerDataModel;

public class Credentials {
	private String login;
	private String password;
	
	public Credentials(String login, String password){
		this.login = login;
		this.password = password;
	}
	
	public void setLogin(String login){
		this.login=login;
	}
	public String getLogin(){
		return login;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public String getPassword(){
		return password;
	}
}