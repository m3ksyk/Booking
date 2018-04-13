import java.sql.SQLException;
import java.util.Scanner;
import pl.coderslab.dao.RoomDAO;
import pl.coderslab.entity.Room;

public class RoomManager {
	public static void manageRooms() throws SQLException {
		RoomDAO.readAllRooms();

		Scanner scan = new Scanner(System.in);
		System.out.println("Choose one of the options:");
		System.out.println("add - to add room");
		System.out.println("edit - to edit room");
		System.out.println("delete - to delete room");
		System.out.println("view - to view rooms");
		System.out.println("assign - to assign room to a hotel");
		System.out.println("quit - to quit");
		String inputStr = scan.nextLine();
		while(!inputStr.equals("quit")) {
			if(inputStr.equals("add")) {
				addRoom(scan);
				System.out.println("Choose one of the options:");
				inputStr = scan.nextLine();
			}else if (inputStr.equals("edit")) {
				editRoom(scan);
				System.out.println("Choose one of the options:");
				inputStr = scan.nextLine();
			}else if (inputStr.equals("delete")) {
				deleteRoom(scan);
				System.out.println("Choose one of the options:");			
			}else if (inputStr.equals("view")) {
				RoomDAO.readAllRooms();
				System.out.println("Choose one of the options:");			
			}else if (inputStr.equals("assign")) {
				assignToHotel(scan);
				System.out.println("Choose one of the options:");			
			}else {
				System.out.println("Choose one of the options:");
				inputStr = scan.nextLine();
			}
				
		}
		scan.close();
	}
				
	private static void assignToHotel(Scanner scan) {
		System.out.println("Assigning room to a hotel. Enter room id:");
		while (!scan.hasNextInt()) {
			System.out.println("Input not a number. Please type a number:");
	        scan.next();
	    }
		int id = scan.nextInt();
		System.out.println("Enter hotel id:");
		while (!scan.hasNextInt()) {
			System.out.println("Input not a number. Please type a number:");
	        scan.next();
	    }
		int id2 = scan.nextInt();
		Room room = RoomDAO.read(id);
		RoomDAO.assignToHotel(room, id2);
		System.out.println("Room with id: " + id + " deleted");		
	}

	public static void addRoom(Scanner scan) throws SQLException {
		System.out.println("Adding a new room. Enter room data:");
		Room room = new Room();
		System.out.println("Enter room internal number: ");
		while (!scan.hasNextInt()) {
			System.out.println("Input not a number. Please type a number:");
            scan.next();
        }
		int num = scan.nextInt();
		room.setInternalNumber(num);
		System.out.println("Enter limit of people in the room: ");
		while (!scan.hasNextInt()) {
			System.out.println("Input not a number. Please type a number:");
            scan.next();
        }
		int numPpl = scan.nextInt();
		room.setPeopleLimit(numPpl);
		System.out.println("Enter rooms description: ");
		String description = scan.nextLine();
		room.setDescription(description);
		System.out.println("Enter the price of the room: ");
		while (!scan.hasNextDouble()) {
			System.out.println("Input not a number. Please type a number:");
            scan.next();
        }
		double price = scan.nextDouble();
		room.setPrice(price);
			RoomDAO.create(room);
			System.out.println("Room added.");
			RoomDAO.readAllRooms();
	}
				
	public static void editRoom(Scanner scan) throws SQLException {
		System.out.println("Editing room. Enter room data: ");
		Room room = new Room();
		System.out.println("Enter room internal number: ");
		while (!scan.hasNextInt()) {
			System.out.println("Input not a number. Please type a number:");
            scan.next();
        }
		int num = scan.nextInt();
		room.setInternalNumber(num);
		System.out.println("Enter limit of people in the room: ");
		while (!scan.hasNextInt()) {
			System.out.println("Input not a number. Please type a number:");
            scan.next();
        }
		int numPpl = scan.nextInt();
		room.setPeopleLimit(numPpl);
		System.out.println("Enter rooms description: ");
		String description = scan.nextLine();
		room.setDescription(description);
		System.out.println("Enter the price of the room: ");
		while (!scan.hasNextDouble()) {
			System.out.println("Input not a number. Please type a number:");
            scan.next();
        }
		double price = scan.nextDouble();
		room.setPrice(price);
		System.out.println("Enter room id: ");
		while (!scan.hasNextInt()) {
			System.out.println("Input not a number. Please type a number:");
		    scan.next();
		}
		int id = scan.nextInt();
		room.setId(id);
		RoomDAO.update(room);
		System.out.println("Room edited.");
		RoomDAO.readAllRooms();
	}
	
	public static void deleteRoom(Scanner scan) throws SQLException {
		System.out.println("Deleting room. Enter room id:");
		while (!scan.hasNextInt()) {
			System.out.println("Input not a number. Please type a number:");
	        scan.next();
	    }
		int id = scan.nextInt();
		RoomDAO.delete(id);
		System.out.println("Room with id: " + id + " deleted");
		RoomDAO.readAllRooms();
	}
}

