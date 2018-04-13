

import java.sql.SQLException;
import java.util.Scanner;

public class Manager {
	
	public static void manage() throws SQLException {
		Scanner scan = new Scanner(System.in);
		System.out.println("Press 1 to manage users");
		System.out.println("Press 2 to manage groups");
		System.out.println("Press 3 to manage hotelss");
		System.out.println("Press 4 to manage rooms");
		System.out.println("Press 5 to manage reservations");
		System.out.println("Press q to quit");
		String inputStr = scan.nextLine();
		while(!inputStr.equals("q")) {
			if(inputStr.equals("1")) {
				System.out.println("User management");
				UserManager.manageUsers();
				inputStr = scan.nextLine();
			}else if (inputStr.equals("2")) {
				System.out.println("Group management");
				GroupManager.manageGroups();
				inputStr = scan.nextLine();
			}else if (inputStr.equals("3")) {
				System.out.println("Hotel management");
				HotelManager.manageHotels();
				inputStr = scan.nextLine();
			}else if (inputStr.equals("4")) {
				System.out.println("Room management");
				RoomManager.manageRooms();
				inputStr = scan.nextLine();
			}else if (inputStr.equals("5")) {
				System.out.println("Reservation management");
				ReservationManager.manageReservations();
				inputStr = scan.nextLine();
			}else {
				System.out.println("Choose one of the options:");
				inputStr = scan.nextLine();
			}			
			
		}
		scan.close();
	}
}