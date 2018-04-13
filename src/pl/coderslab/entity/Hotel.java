package pl.coderslab.entity;
public class Hotel {
	int id;
	String name;
	String address;
	String phoneNumber;
	int petsAllowed;
	String description;
	Room[] rooms;
	
	public Hotel(String name, String address, String phoneNumber, int petsAllowed, String description,
			Room[] rooms) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.petsAllowed = petsAllowed;
		this.description = description;
		this.rooms = rooms;
	}

	public Hotel() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int isPetsAllowed() {
		return petsAllowed;
	}

	public void setPetsAllowed(int i) {
		this.petsAllowed = i;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Room[] getRooms() {
		return rooms;
	}

	public void setRooms(Room[] rooms) {
		this.rooms = rooms;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
}