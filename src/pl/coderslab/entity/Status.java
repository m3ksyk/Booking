package pl.coderslab.entity;

public class Status {
	int id;
	public int reservationStatus; //1 - received 2 - paid 3 - canceled
	
	public Status(int id, int reservationStatus) {
		this.id = id;
		this.reservationStatus = reservationStatus;
	}

	public Status() {
	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getReservationStatus() {
		return reservationStatus;
	}

	public void setReservationStatus(int reservationStatus) {
		this.reservationStatus = reservationStatus;
	}
	
}

