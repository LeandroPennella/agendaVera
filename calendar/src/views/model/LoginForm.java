package views.model;


public class LoginForm {
	
	
	private String username;
	private String password;
	private Boolean recordar = false;
	
	public LoginForm(){
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getRecordar() {
		return recordar;
	}

	public void setRecordar(Boolean recordar) {
		this.recordar = recordar;
	}
	
	
	
	
	
	
}
