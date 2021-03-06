package user;

import java.util.ArrayList;
import resource.*;

public class User {
	protected String name;
	protected String email;
	public ArrayList<Resource> resourcesList;
	
	public User(String name, String email){
		this.name = name;
		this.email = email;
		this.resourcesList = new ArrayList<Resource>();
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ArrayList<Resource> getResourcesList() {
		return resourcesList;
	}
}
