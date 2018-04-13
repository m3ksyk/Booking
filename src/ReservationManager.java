import java.sql.SQLException;
import java.util.Calendar;
import java.util.Scanner;

import pl.coderslab.dao.ReservationDAO;
import pl.coderslab.entity.Reservation;

public class ReservationManager{
	
	public static void manageReservations() throws SQLException {
		ReservationDAO.readAllReservations();

		Scanner scan = new Scanner(System.in);
		System.out.println("Choose one of the options:");
		System.out.println("add - to add reservation");
		System.out.println("edit - to edit reservation");
		System.out.println("delete - to delete reservation");
		System.out.println("view - to view reservations");
		System.out.println("change - to change reservation status");
		System.out.println("details - to view reservation details");
		System.out.println("quit - to quit");
		String inputStr = scan.nextLine();
		while(!inputStr.equals("quit")) {
			if(inputStr.equals("add")) {
				addReservation(scan);
				System.out.println("Choose one of the options:");
				inputStr = scan.nextLine();
			}else if (inputStr.equals("edit")) {
				editReservation(scan);
				System.out.println("Choose one of the options:");
				inputStr = scan.nextLine();
			}else if (inputStr.equals("delete")) {
				deleteReservation(scan);
				System.out.println("Choose one of the options:");			
			}else if (inputStr.equals("view")) {
				ReservationDAO.readAllReservations();
				System.out.println("Choose one of the options:");			
			}else if (inputStr.equals("change")) {
				changeStatus(scan);
				System.out.println("Choose one of the options:");			
			}else if (inputStr.equals("details")) {
				viewDetails(scan);
				System.out.println("Choose one of the options:");			
			}else {
				System.out.println("Choose one of the options:");
				inputStr = scan.nextLine();
			}
				
		}
		scan.close();
	}
				
	private static void viewDetails(Scanner scan) {
		System.out.println("Enter reservation id: ");
		while (!scan.hasNextInt()) {
			System.out.println("Input not a number. Please type a number:");
            scan.next();
        }
		int id = scan.nextInt();
		ReservationDAO.read(id);
		
	}

	private static void changeStatus(Scanner scan) {
		Reservation reservation = new Reservation();
		System.out.println("Enter user id: ");
		while (!scan.hasNextInt()) {
			System.out.println("Input not a number. Please type a number:");
            scan.next();
        }
		int user_id = scan.nextInt();
		reservation.setUser_id(user_id);
		System.out.println("Enter status id: ");
		while (!scan.hasNextInt()) {
			System.out.println("Input not a number. Please type a number:");
            scan.next();
        }
		int status_id = scan.nextInt();
		reservation.setStatus_id(status_id);
		ReservationDAO.updateStatus(reservation);
		System.out.println("Reservation status updated.");
		
	}
	
	public static void addReservation(Scanner scan) throws SQLException {
		System.out.println("Adding a new reservation. Enter reservation data:");
		Reservation reservation = new Reservation();		
		String applyDate = Calendar.getInstance().toString();
		reservation.setApplyDate(applyDate);
		System.out.println("Enter starting date: ");
		String fromDate = scan.nextLine();
		reservation.setFromDate(fromDate);
		System.out.println("Enter ending date: ");
		String toDate = scan.nextLine();
		reservation.setToDate(toDate);
		System.out.println("Enter reservation description: ");
		String description = scan.nextLine();
		reservation.setDescription(description);
		System.out.println("Enter user id: ");
		while (!scan.hasNextInt()) {
			System.out.println("Input not a number. Please type a number:");
            scan.next();
        }
		int user_id = scan.nextInt();
		reservation.setUser_id(user_id);		
		ReservationDAO.create(reservation);
		System.out.println("Reservation added.");
		ReservationDAO.readAllReservations();
	}
				
	public static void editReservation(Scanner scan) throws SQLException {
		System.out.println("Editing reservation. Enter reservation data:");
		Reservation reservation = new Reservation();		
		String applyDate = Calendar.getInstance().toString();
		reservation.setApplyDate(applyDate);
		System.out.println("Enter starting date: ");
		String fromDate = scan.nextLine();
		reservation.setFromDate(fromDate);
		System.out.println("Enter ending date: ");
		String toDate = scan.nextLine();
		reservation.setToDate(toDate);
		System.out.println("Enter reservation description: ");
		String description = scan.nextLine();
		reservation.setDescription(description);
		System.out.println("Enter user id: ");
		while (!scan.hasNextInt()) {
			System.out.println("Input not a number. Please type a number:");
            scan.next();
        }
		int user_id = scan.nextInt();
		reservation.setUser_id(user_id);
		System.out.println("Enter status id: ");
		while (!scan.hasNextInt()) {
			System.out.println("Input not a number. Please type a number:");
            scan.next();
        }
		int status_id = scan.nextInt();
		reservation.setStatus_id(status_id);
		int id = scan.nextInt();
		reservation.setId(id);
		ReservationDAO.update(reservation);
		System.out.println("Reservation edited.");
		ReservationDAO.readAllReservations();
	}
	
	public static void deleteReservation(Scanner scan) throws SQLException {
		System.out.println("Deleting reservation. Enter reservation id:");
		while (!scan.hasNextInt()) {
			System.out.println("Input not a number. Please type a number:");
	        scan.next();
	    }
		int id = scan.nextInt();
		ReservationDAO.delete(id);
		System.out.println("Reservation with id: " + id + " deleted");
		ReservationDAO.readAllReservations();
	}		
	}

