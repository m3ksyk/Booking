

import java.sql.SQLException;
import java.util.Scanner;
import pl.coderslab.dao.UserDAO;
import pl.coderslab.entity.User;

public class UserManager {
	
	public static void manageUsers() throws SQLException{
		UserDAO.readAllUsers();

		//might implement?:
//		read(Integer userId);
//		readByGroup(int groupId)
//		readByMail(String email)
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Choose one of the options:");
		System.out.println("add - to add user");
		System.out.println("edit - to edit user");
		System.out.println("delete - to delete user");
		System.out.println("addgroup - to add user to a group");
		System.out.println("view - to view all users");
		System.out.println("quit - to quit");
		String inputStr = scan.nextLine();
		while(!inputStr.equals("quit")) {
			if(inputStr.equals("add")) {
				addUser(scan);
				System.out.println("Choose one of the options:");
				inputStr = scan.nextLine();
			}else if (inputStr.equals("edit")) {
				editUser(scan);
				System.out.println("Choose one of the options:");
				inputStr = scan.nextLine();
			}else if (inputStr.equals("delete")) {
				deleteUser(scan);
				System.out.println("Choose one of the options:");			
			}else if (inputStr.equals("addgroup")) {
				addToGroup(scan);
				System.out.println("Choose one of the options:");			
			}else if (inputStr.equals("view")) {
				UserDAO.readAllUsers();
				System.out.println("Choose one of the options:");			
			}else {
				System.out.println("Choose one of the options:");
				inputStr = scan.nextLine();
			}
			
			UserDAO.readAllUsers();
		}
		scan.close();
	}
	
	public static void addUser(Scanner scan) throws SQLException {
		System.out.println("Adding a new user. Enter user data:");
		User user = new User();
		System.out.println("Enter user name: ");
		String username = scan.nextLine();
		user.setName(username);
		System.out.println("Enter unique user email address: ");
		String email = scan.nextLine();
		user.setEmail(email);
		System.out.println("Set user password: ");
		String password = scan.nextLine();
		user.setPassword(password);
		System.out.println("Enter user group id: ");
		while (!scan.hasNextInt()) {
			System.out.println("Input not a number. Please type a number:");
            scan.next();
        }
		int groupId = scan.nextInt();
		user.setUserGroupId(groupId);
		UserDAO.create(user);
		System.out.println("User added.");
		UserDAO.readAllUsers();
	}
	
	public static void editUser(Scanner scan) throws SQLException {
		System.out.println("Editing user. Enter user data:");
		User user = new User();
		System.out.println("Enter user name: ");
		String username = scan.nextLine();
		user.setName(username);
		System.out.println("Enter unique user email address: ");
		String email = scan.nextLine();
		user.setEmail(email);
		System.out.println("Set user password: ");
		String password = scan.nextLine();
		user.setPassword(password);
		System.out.println("Enter user group id: ");
		while (!scan.hasNextInt()) {
			System.out.println("Input not a number. Please type a number:");
            scan.next();
        }
		int groupId = scan.nextInt();
		user.setUserGroupId(groupId);
		System.out.println("Enter user id: ");
		while (!scan.hasNextInt()) {
			System.out.println("Input not a number. Please type a number:");
            scan.next();
        }
		int id = scan.nextInt();
		user.setId(id);
		UserDAO.update(user);
		System.out.println("User edited.");
		UserDAO.readAllUsers();
	}
	public static void deleteUser(Scanner scan) throws SQLException {
		System.out.println("Deleting user. Enter user id:");
		while (!scan.hasNextInt()) {
			System.out.println("Input not a number. Please type a number:");
            scan.next();
        }
		int id = scan.nextInt();
		UserDAO.delete(id);
		System.out.println("User with id: " + id + " deleted");
		UserDAO.readAllUsers();
	}
	public static void addToGroup(Scanner scan) {
		System.out.println("Enter user id:");
		while (!scan.hasNextInt()) {
			System.out.println("Input not a number. Please type a number:");
            scan.next();
        }
		int id = scan.nextInt();
		System.out.println("Enter user group id: ");
		while (!scan.hasNextInt()) {
			System.out.println("Input not a number. Please type a number:");
            scan.next();
        }
		int groupId = scan.nextInt();
		User user =UserDAO.read(id);
		user.setId(groupId);
		UserDAO.addToGroup((user));
	}
}
