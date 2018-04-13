
import java.sql.SQLException;
import java.util.Scanner;

public class UserProgram {
	public static void userManagementView() throws SQLException {
	Scanner scan = new Scanner(System.in);
	System.out.println("enter your user id:");
	while (!scan.hasNextInt()) {
		System.out.println("Input not a number. Please type a number:");
        scan.next();
    }
	int id = scan.nextInt();
	
	System.out.println("Choose one of the options:");
	System.out.println("add - to add new reservation");
	System.out.println("quit - to quit");
	String inputStr = scan.nextLine();
	while(!inputStr.equals("quit")) {
		if(inputStr.equals("add")) {
			add(scan, id);
			System.out.println("Choose one of the options:");
			inputStr = scan.nextLine();
		}else {
			System.out.println("Choose one of the options:");
			inputStr = scan.nextLine();
		}
		
	}
	scan.close();
	}
	
	private static void add(Scanner scan, int id) throws SQLException {
		ReservationManager.addReservation(scan);		
	}

}	





