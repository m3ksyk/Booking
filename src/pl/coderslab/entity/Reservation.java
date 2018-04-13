package pl.coderslab.entity;
public class Reservation {
	int id;
	String applyDate;
	String fromDate;
	String toDate;
	String description;
	User rentingUser;
	int user_id;
	int status_id;
	Status status; //status (pole systemowe) - niemożliwe do zmiany przez użytkownika.
	
	
	public Reservation() {
	}
	public Reservation( String applyDate, String fromDate, String toDate, String description, User rentingUser,
			int user_id, int status_id, Status status) {
		
		this.applyDate = applyDate;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.description = description;
		this.rentingUser = rentingUser;
		this.user_id = user_id;
		this.status_id = status_id;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getRentingUser() {
		return rentingUser;
	}
	public void setRentingUser(User rentingUser) {
		this.rentingUser = rentingUser;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getStatus_id() {
		return status_id;
	}
	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	
}
