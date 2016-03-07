package resource;

public class Resource {
	protected int id;
	protected Activity activity;
	boolean isAllocated;
	
	public Resource(int id) {
		this.id = id;
		this.activity = new Activity("", "", "");
		this.isAllocated = false;
	}
	
	public int getId(){
		return id;
	}
	public boolean getAllocated(){
		return isAllocated;
	}
	public void setAllocated(boolean isAllocated){
		this.isAllocated = isAllocated;
	}
	
}
