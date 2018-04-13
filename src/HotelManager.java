import java.sql.SQLException;
import java.util.Scanner;
import pl.coderslab.dao.HotelDAO;
import pl.coderslab.entity.Hotel;

public class HotelManager {
	public static void manageHotels() throws SQLException {
		HotelDAO.readAllHotels();

		Scanner scan = new Scanner(System.in);
		System.out.println("Choose one of the options:");
		System.out.println("add - to add hotel");
		System.out.println("edit - to edit hotel");
		System.out.println("delete - to delete hotel");
		System.out.println("view - to view hotels");
		System.out.println("quit - to quit");
		String inputStr = scan.nextLine();
		while(!inputStr.equals("quit")) {
			if(inputStr.equals("add")) {
				addHotel(scan);
				System.out.println("Choose one of the options:");
				inputStr = scan.nextLine();
			}else if (inputStr.equals("edit")) {
				editHotel(scan);
				System.out.println("Choose one of the options:");
				inputStr = scan.nextLine();
			}else if (inputStr.equals("delete")) {
				deleteHotel(scan);
				System.out.println("Choose one of the options:");			
			}else if (inputStr.equals("view")) {
				HotelDAO.readAllHotels();
				System.out.println("Choose one of the options:");			
			}else {
				System.out.println("Choose one of the options:");
				inputStr = scan.nextLine();
			}
			
		}
		scan.close();
	}
			
	public static void addHotel(Scanner scan) throws SQLException {
		System.out.println("Adding a new hotel. Enter hotel data:");
		Hotel hotel = new Hotel();
		System.out.println("Enter hotel name: ");
		String name = scan.nextLine();
		hotel.setName(name);
		System.out.println("Enter hotel address: ");
		String address = scan.nextLine();
		hotel.setAddress(address);
		System.out.println("Enter hotel phone number: ");
		String phone = scan.nextLine();
		hotel.setPhoneNumber(phone);
		System.out.println("Are pets allowed in hotel (0/1): ");
		while (!scan.hasNextInt()) {
			System.out.println("Input not a number. Please type a number:");
            scan.next();
        }
		int pets = scan.nextInt();
		hotel.setPetsAllowed(pets);
		System.out.println("Enter hotel description: ");
		String description = scan.nextLine();
		hotel.setDescription(description);
		HotelDAO.create(hotel);
		System.out.println("Hotel added.");
		HotelDAO.readAllHotels();
	}
			
	public static void editHotel(Scanner scan) throws SQLException {
		System.out.println("Editing hotel. Enter hotel data:");
		Hotel hotel = new Hotel();
		System.out.println("Enter hotel name: ");
		String name = scan.nextLine();
		hotel.setName(name);
		System.out.println("Enter hotel address: ");
		String address = scan.nextLine();
		hotel.setAddress(address);
		System.out.println("Enter hotel phone number: ");
		String phone = scan.nextLine();
		hotel.setPhoneNumber(phone);
		System.out.println("Are pets allowed in hotel (0/1): ");
		while (!scan.hasNextInt()) {
			System.out.println("Input not a number. Please type a number:");
            scan.next();
        }
		int pets = scan.nextInt();
		hotel.setPetsAllowed(pets);
		System.out.println("Enter hotel description: ");
		String description = scan.nextLine();
		hotel.setDescription(description);
		System.out.println("Enter hotel id: ");
		while (!scan.hasNextInt()) {
			System.out.println("Input not a number. Please type a number:");
		    scan.next();
		}
		int id = scan.nextInt();
		hotel.setId(id);
		HotelDAO.update(hotel);
		System.out.println("Hotel edited.");
		HotelDAO.readAllHotels();
	}
	public static void deleteHotel(Scanner scan) throws SQLException {
		System.out.println("Deleting hotel. Enter hotel id:");
		while (!scan.hasNextInt()) {
			System.out.println("Input not a number. Please type a number:");
	        scan.next();
	    }
		int id = scan.nextInt();
		HotelDAO.delete(id);
		System.out.println("Hotel with id: " + id + " deleted");
		HotelDAO.readAllHotels();
	}
			
}

