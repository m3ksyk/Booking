package pl.coderslab.entity;

public class Room {
	int id;
	int internalNumber;
	int peopleLimit;
	String description;
	double price;
	int hotel_id;
	
	public Room(int internalNumber, int peopleLimit, String description, double price, int hotel_id) {
		this.internalNumber = internalNumber;
		this.peopleLimit = peopleLimit;
		this.description = description;
		this.price = price;
		this.hotel_id = hotel_id;
	}
	public Room() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getInternalNumber() {
		return internalNumber;
	}
	public void setInternalNumber(int internalNumber) {
		this.internalNumber = internalNumber;
	}
	public int getPeopleLimit() {
		return peopleLimit;
	}
	public void setPeopleLimit(int peopleLimit) {
		this.peopleLimit = peopleLimit;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getHotel_id() {
		return hotel_id;
	}
	public void setHotel_id(int hotel_id) {
		this.hotel_id = hotel_id;
	}
	@Override
	public String toString() {
		return "Room [id=" + id + ", internalNumber=" + internalNumber + ", peopleLimit=" + peopleLimit
				+ ", description=" + description + ", price=" + price + ", hotel_id=" + hotel_id + "]";
	}
	
}
