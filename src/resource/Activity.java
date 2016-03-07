package resource;

import java.util.ArrayList;
import user.*;

public class Activity {
	protected String title;
	protected String description;
	protected ArrayList<User> userList;
	protected String supportMaterial;
	
	public Activity(String title, String description, String supportMaterial) {
		this.title = title;
		this.description = description;
		this.supportMaterial = supportMaterial;
		this.userList = new ArrayList<User>();
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ArrayList<User> getUserList() {
		return userList;
	}
	public String getSupportMaterial() {
		return supportMaterial;
	}
	public void setSupportMaterial(String supportMaterial) {
		this.supportMaterial = supportMaterial;
	}
}
