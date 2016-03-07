import user.*;
import java.util.ArrayList;

public class Allocation {
	protected String status;
	protected String startDate;
	protected String endDate;
	protected int resourceId;
	protected ArrayList<User> userlist;
	
	public Allocation(String startDate, String endDate, int resourceId){
		this.startDate = startDate;
		this.endDate = endDate;
		this.resourceId = resourceId;
		this.status = "em processo de alocacao";
		this.userlist = new ArrayList<User>();
	}
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getResourceId(){
		return resourceId;
	}
	
	
}
