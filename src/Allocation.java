import user.*;
import java.util.ArrayList;
import java.util.Date;

public class Allocation {
	protected String status;
	protected Date startDate;
	protected Date endDate;
	protected int resourceId;
	protected ArrayList<User> userlist;
	
	public Allocation(Date startDate2, Date endDate2, int resourceId){
		this.startDate = startDate2;
		this.endDate = endDate2;
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
	public Date getStartDate() {
		return startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public int getResourceId(){
		return resourceId;
	}
	
	
}
