
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		try {
		
			//IN THE CURRENT VERSION BOTH USER AND ADMIN PROGRAM ARE CALLED FROM A SHARED MENU IN MAIN METHOD
			Scanner scan =new Scanner(System.in);
			System.out.println("Press 1 to enter User view");
			System.out.println("Press 2 to enter Admin view");
			System.out.println("Press q to quit");
			String inputStr = scan.nextLine();
			while(!inputStr.equals("q")) {
				if(inputStr.equals("1")) {
					System.out.println("User view");
					UserProgram.userManagementView();
					inputStr = scan.nextLine();
				}else if (inputStr.equals("2")) {
					System.out.println("Admin view");
					Manager.manage();
					inputStr = scan.nextLine();				
				}else {
					System.out.println("Choose one of the options:");
					inputStr = scan.nextLine();
				}
			}
			
			scan.close();	
					
		} catch (SQLException e) {
			e.printStackTrace();		
		}catch (NullPointerException e) {
			e.printStackTrace();		
		}catch (Exception e) {
			e.printStackTrace();		
		}
	}	

	}


