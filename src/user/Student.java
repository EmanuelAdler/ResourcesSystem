package user;

public class Student extends User{
	protected String type;

	public Student(String name, String email, String type) {
		super(name, email);
		this.type = type;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
