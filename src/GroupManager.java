

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import pl.coderslab.dao.GroupDAO;
import pl.coderslab.dao.UserDAO;
import pl.coderslab.entity.Group;


public class GroupManager {
	
	public static void manageGroups() throws SQLException{
		
		//redo to match current model!!
		
		GroupDAO.readAllGroups();
		// dodac mozliwosc wyswieltlenia w menu
		Scanner scan = new Scanner(System.in);
		System.out.println("Choose one of the options:");
		System.out.println("add - to add group");
		System.out.println("edit - to edit group");
		System.out.println("delete - to delete group");
		System.out.println("view - to view users in the group");
		System.out.println("quit - to quit");
		String inputStr = scan.nextLine();
		while(!inputStr.equals("quit")) {
			if(inputStr.equals("add")) {
				addGroup(scan);
				System.out.println("Choose one of the options:");
				inputStr = scan.nextLine();
			}else if (inputStr.equals("edit")) {
				editGroup(scan);
				System.out.println("Choose one of the options:");
				inputStr = scan.nextLine();
			}else if (inputStr.equals("delete")) {
				deleteGroup(scan);
				System.out.println("Choose one of the options:");			
			}else if (inputStr.equals("view")) {
				viewUsersInGroup(scan);
				System.out.println("Choose one of the options:");			
			}else {
				System.out.println("Choose one of the options:");
				inputStr = scan.nextLine();
			}
			
			GroupDAO.readAllGroups();
		}
		scan.close();
		}
		
		public static void addGroup(Scanner scan) throws SQLException {
			System.out.println("Adding a new group. Enter group data:");
			Group group = new Group();
			System.out.println("Enter group name: ");
			String name = scan.nextLine();
			group.setName(name);
			GroupDAO.create(group);
			System.out.println("Group added.");
			GroupDAO.readAllGroups();
		}
		
		public static void editGroup(Scanner scan) throws SQLException {
			System.out.println("Editing  group. Enter  group data:");
			Group group = new Group();
			System.out.println("Enter group name: ");
			String name = scan.nextLine();
			group.setName(name);
			System.out.println("Enter group id: ");
			while (!scan.hasNextInt()) {
				System.out.println("Input not a number. Please type a number:");
	            scan.next();
	        }
			int id = scan.nextInt();
			group.setId(id);
			GroupDAO.update(group);
			System.out.println("Group edited.");
			GroupDAO.readAllGroups();
		}
		
		public static void deleteGroup(Scanner scan) throws SQLException {
			System.out.println("Deleting group. Enter group id:");
			while (!scan.hasNextInt()) {
				System.out.println("Input not a number. Please type a number:");
	            scan.next();
	        }
			int id = scan.nextInt();
			GroupDAO.delete(id);
			System.out.println("Group with id: " + id + " deleted");
			GroupDAO.readAllGroups();
		}
		public static void viewUsersInGroup(Scanner scan) throws SQLException {
			System.out.println("Viewing users in a group. Enter group id:");
			while (!scan.hasNextInt()) {
				System.out.println("Input not a number. Please type a number:");
	            scan.next();
	        }
			int id = scan.nextInt();
			UserDAO.readByGroup(id);
			
		}
		
}
