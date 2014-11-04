/**********************************************************************************
 *	CONCORDIA UNIVERSITY - ASSIGNMENT #3
 * 		Mandeep Tahim - 6935478
 * 		Ronnie Pang - 7041551
 * 		Shareef Serhan - 6804098
 * 	
 * Due Date: November 2nd 2014
 * 
 * This program is designed to implement a small a miniature version of a Concordia 
 * Payment system.
 * 		
 * NOTE: advanceToNextMonth() is implemented differently in this program, 
 * 		implemented by totalMonth() in the ConcordiaDatabase class, keeping
 * 		track of the hours in assigned contracts.
 * 	
 * 		ALSO NOTE THAT APACHE LANG HAS BEEN IMPORTED IN CONCORDIADATABASE CLASS
 * 
 ********************************************************************************* 
 */

package defaultPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Driver {

	public static void main(String[] args) {
		
		//Declaration of Variables and Constants
		int userInput = 0;
		boolean quit = false;
		Scanner myKey = new Scanner(System.in);
		ConcordiaDatabase database = readFromSerializedFile(new File("concordiaDatabase.dat"));
		
		String menu = "\nMenu\nPress 1 to List User(s)\nPress 2 to Add User(s)\nPress 3 to Remove a User"
				+ "\nPress 4 to Search/View Member\nPress 5 for all PayStubs of the Current Month"
				+ "\nPress 6 to Calculate Total Amount to be Paid"
				+ "\nPress 7 to Verify if a Member is Qualified to be a TA"
				+ "\nPress 8 to modify Member"
				+ "\nPress 0 to Quit and Save\n";

		//Welcome Message
		System.out.println("Welcome to the Concordia Payment System!\n\n"
				+ "--------------------------------------------------------\n"
				+"Alpha-Test v0.1 || (Supports up to 100 members)\n"
				+ "--------------------------------------------------------");
		if(database == null){
			database = new ConcordiaDatabase();
		Students student1 = new Students("Mandeep", "Tahim", "6935478", StudentStatus.GRADUATE_TA,100);
		Students student2= new Students("Manpreet", "Tahim", "123456", StudentStatus.ALUMNI,0);
		FacultyMembers faculty1 = new FacultyMembers("Shareef", "Serhan", "6804098", FacultyStatus.PERMANENT,0,0,0);
		FacultyMembers faculty2 = new FacultyMembers("Ronnie", "Pang", "7041551", FacultyStatus.PART_TIME,150,60,20);
		StaffMembers staff1 = new StaffMembers("Cheery","Berry","987654", StaffStatus.OTHER,150);
		database.addMember(staff1);
		database.addMember(faculty1);
		database.addMember(faculty2);
		database.addMember(student1);
		database.addMember(student2);
		}
		//While Loop (Main Menu) prompting users for options until user request to terminate program
		while (quit == false){
			try{
				System.out.println(menu);
				userInput = myKey.nextInt();
			}
			catch(InputMismatchException e){
				myKey.next(); // removes invalid token
			}

			switch(userInput){		
				//LIST ALL MEMBERS IN DATABASE
			case 1:
			{
				System.out.println(database.listDatabase());
				break;
			}
				//ADD USERS TO DATABASE
			case 2:
			{
				addUser(database);
				break;
			}
				//REMOVES USERS FROM DATABASE
			case 3:
			{
				System.out.print("What is the first name of this individual? ");
				String firstName=myKey.next();
				System.out.print("What is the last name of " + firstName + "? ");
				String lastName=myKey.next();
				System.out.print("What is the Concordia ID of " + firstName + " " + lastName + "? ");
				String concordiaID=myKey.next();

				boolean removed = database.removeUser(firstName, lastName, concordiaID);
				if(removed){
					System.out.println("User was removed! Redirecting you the the Main Menu.\n");
				}
				else {
					System.out.println("User was not found, redirecting you to the Main Menu.\nPlease verify list of users in databse.\n");
				}	
				break;
			}
				//VIEW SELECT USERS
			case 4:{
				System.out.print("What is the first name of this individual? ");
				String firstName=myKey.next();
				System.out.print("What is the last name of " + firstName + "? ");
				String lastName=myKey.next();
				System.out.print("What is the Concordia ID of " + firstName + " " + lastName + "? ");
				String concordiaID=myKey.next();
		
				System.out.println(database.viewMember(firstName, lastName, concordiaID));
				break;
			}
				//VIEW PAYSTUB OF VERY MEMBER IN DATABASE
			case 5:{
				System.out.print(database.payStub());
				break;
			}
				//CALCULATE TOTAL IN THE MONTH
			case 6:{
				DecimalFormat df = new DecimalFormat("#.##");
				System.out.println(df.format(database.totalMonth()));
				break;
			}
				//VERIFIES IF QUALIFIED TO BE A TA
			case 7:{
				System.out.print("What is the first name of this individual? ");
				String firstName=myKey.next();
				System.out.print("What is the last name of " + firstName + "? ");
				String lastName=myKey.next();
				System.out.print("What is the Concordia ID of " + firstName + " " + lastName + "? ");
				String concordiaID=myKey.next();
		
				System.out.println(database.QualifiedTA(firstName, lastName, concordiaID));
				break;
			}
				//MODIFIES USERS
			case 8:{
				modifyUser(database);
				break;
			}
			case 0:
			{
				quit = true;
				break;
			}
				//DEFAULT CASE WHEN USER ENTERS INVALID OPTION
			default:
				System.out.println("I'm sorry, I didn't understand what you entered. Try again!\n");
				break;
			}
		}
		
		writeToSerializedFile(new File("concordiaDatabase.dat"), database);
		//Termination Message
	System.out.println("\nThank you for testing the Concordia Payment System!");
	

	} // End of Main Class

		//Method to add User
	public static void addUser(ConcordiaDatabase database){

		Scanner myKey = new Scanner(System.in);
		boolean successful = false;
		
		//Prompts user for basic information
		System.out.print("What is the first name of this individual? ");
		String firstName=myKey.next();
		System.out.print("What is the last name of " + firstName + "? ");
		String lastName=myKey.next();
		System.out.print("What is the Concordia ID of " + firstName + " " + lastName + "? ");
		String concordiaID=myKey.next();

		do{
			System.out.println("\nNow Press\n1 If He/She is a Student\n2 If He/She is a Faculty Member\n3 If He/She is a Staff Member\n");
			int userEntry = myKey.nextInt();

			switch(userEntry){
			case 1: // if Member that is to be added is Student
			{
				StudentStatus status = StudentStatus.valueOf(promptStudentStatus());
				int contractHours =0;
				if(status==StudentStatus.GRADUATE_TA || status==StudentStatus.UNDERGRADUATE_TA){
					System.out.print("How many hours does this individual have in his contract? ");
					contractHours = myKey.nextInt();
				}
				Students enteredStudent = new Students(firstName,lastName, concordiaID, status, contractHours);
				database.addMember(enteredStudent);
				successful = true;
				break;
			}

			case 2: // if Member that is to be added is Faculty
			{
				FacultyStatus status = FacultyStatus.valueOf(promptFacultyStatus());
				int contractHours =0;
				int firstClassSize =0;
				int secondClassSize =0;
				
				if(status==FacultyStatus.PART_TIME){
					System.out.print("How many hours does this individual have in his contract? ");
					contractHours = myKey.nextInt();
					System.out.print("How many students does this indivual have in his class? ");
					firstClassSize = myKey.nextInt();
					System.out.print("How many students does this indivual have in his second class?\n(If there isn't another class, input 0)");
					secondClassSize = myKey.nextInt();
				}
				FacultyMembers enteredFaculty = new FacultyMembers(firstName, lastName, concordiaID, status, contractHours, firstClassSize, secondClassSize);
				database.addMember(enteredFaculty);
				successful = true;
				break;
				
			}
			case 3: // if Member that is to be added is Staff
			{
				StaffStatus status = StaffStatus.valueOf(promptStaffStatus());
				int contractHours =0;
				if(status==StaffStatus.TEMP_STAFF){
					System.out.print("How many hours does this individual have in his contract? ");
					contractHours = myKey.nextInt();
				}
				StaffMembers enteredStaff = new StaffMembers(firstName, lastName, concordiaID, status, contractHours);
				database.addMember(enteredStaff);
				successful = true;
				break;
			}

			default:{
				System.out.println("Invalid Entry!\n");
				}
			}
		}
		while(successful==false);

		System.out.println(firstName + " " + lastName + " has been added successfully.\n");
	}
		//Method to Modify User; checks first if member is in the database, then proceeds to modify()
	public static void modifyUser(ConcordiaDatabase database){
		Scanner myKey = new Scanner(System.in);
		System.out.print("What is the first name of this individual? ");
		String firstName=myKey.next();
		System.out.print("What is the last name of " + firstName + "? ");
		String lastName=myKey.next();
		System.out.print("What is the Concordia ID of " + firstName + " " + lastName + "? ");
		String concordiaID=myKey.next();
		
			if(database.searchMember(firstName, lastName, concordiaID)!= null){
				modify(database.searchMember(firstName, lastName, concordiaID));
			
			}
			else{
				System.out.println("You've entered invalid information. Redirecting to the main menu.\nPlease verify the information.\n");
			}
	}
		//Used in addUser() if user requested Staff to be added
	public static String promptStaffStatus(){
		Scanner myKey = new Scanner(System.in);
		String status = null;
		boolean successful = false;
		do{

			System.out.println("What is the status of this StaffMember?\nPress 1 Permanent\nPress 2 for Temporary\nPress 3 Other\n");
			int userEntryStatus = myKey.nextInt();
			switch(userEntryStatus){
			case 1:
			{
				status = "PERM_STAFF";
				successful = true;
				break;
			}
			case 2:
			{
				status = "TEMP_STAFF";
				successful = true;
				break;
			}
			case 3:
			{
				status = "OTHER";
				successful = true;
				break;
			}
			default:{
				System.out.println("INVALID ENTRY! TRY AGAIN: ");
			}
			}
		}
		while(successful == false);

		return status;
	}
	//Used in addUser() if user requested Student to be added
	public static String promptStudentStatus(){
		Scanner myKey = new Scanner(System.in);
		String status = null;
		boolean successful = false;
		do{

			System.out.println("What is the status of this student?\nPress 1 for alumni\nPress 2 for graduate\nPress 3 for undergraduate\nPress 4 for a graduate TA\nPress 5 for a undergraduate TA");
			int userEntryStatus = myKey.nextInt();
			switch(userEntryStatus){
			case 1:
			{
				status = "ALUMNI";
				successful = true;
				break;
			}
			case 2:
			{
				status = "GRADUATE";
				successful = true;
				break;
			}
			case 3:
			{
				status = "UNDERGRADUATE";
				successful = true;
				break;
			}
			case 4:{
				status = "GRADUATE_TA";
				successful = true;
				break;
			}
			case 5:{
				status = "UNDERGRADUATE_TA";
				successful = true;
				break;
			}
			default:{
				System.out.println("INVALID ENTRY! TRY AGAIN: ");
			}
			}
		}
		while(successful == false);
		return status;
	}
	//Used in addUser() if user requested Faculty to be added
	public static String promptFacultyStatus(){
		Scanner myKey = new Scanner(System.in);
		String status = null;
		boolean successful = false;
		do{

			System.out.println("What is the status of this Faculty Member?\nPress 1 Permanent\nPress 2 for Part-Time\n");
			int userEntryStatus = myKey.nextInt();
			switch(userEntryStatus){
			case 1:
			{
				status = "PERMANENT";
				successful = true;
				break;
			}
			case 2:
			{
				status = "PART_TIME";
				successful = true;
				break;
			}
			default:{
				System.out.println("INVALID ENTRY! TRY AGAIN: ");
			}
			}
		}
		while(successful == false);
		return status;
	}
	//Used in modifyUser(). Depending on that the user is reuqesting to be changed, there are different cases.
	public static void modify(ConcordiaMembers concordiaMember){
	
	boolean correctInput = false;
	Scanner myKey = new Scanner(System.in);
	do{
		System.out.println("Press 1 to modify Name\nPress 2 to modify Concordia ID\nPress 3 to modify Status and other Attributes");
		int option = myKey.nextInt();
		switch(option){
		case 1:{
			
			System.out.print("What is the first name of this individual? ");
			String firstName=myKey.next();
			System.out.print("What is the last name of " + firstName + "? ");
			String lastName=myKey.next();
			
			concordiaMember.setFirstName(firstName);
			concordiaMember.setLastName(lastName);
			System.out.println("Changes updated!");
			correctInput =true;
			break;
			}	
		case 2:{
			System.out.print("What is the Concordia ID? :");
			String concordiaID=myKey.next();
			concordiaMember.setConcordiaID(concordiaID);
			System.out.println("Changes updated!");
			correctInput =true;
			break;
			}
		case 3:{

			if(concordiaMember instanceof StaffMembers){
				modifyStaff(concordiaMember);
				}
				
			else if(concordiaMember instanceof Students){
				modifyStudent(concordiaMember);
				}
			
			else if(concordiaMember instanceof FacultyMembers){
				modifyFaculty(concordiaMember);
				}
			System.out.println("Changes updated!");
			correctInput = true;
			break;
		}
		default:{
			System.out.println("Invalid Entry. Try again!");
			break;
			}
		}
	}
	while(correctInput==false);
	}
	/* For simplicity and ease of access, the following three classes were created in order to be implemented along 
	 * the modify() class. Depending on which status the user wants to change, other properties 
	 * along with the status are going to be prompted in order to provide correct data.
	 */
	public static void modifyStaff(ConcordiaMembers concordiaMember){
		Scanner myKey = new Scanner(System.in);
		
		String status = promptStaffStatus();
		((StaffMembers) concordiaMember).setStatus(StaffStatus.valueOf(status));
		int contractHours =0;
			if(status == "TEMP_STAFF"){
				System.out.print("How many hours does this individual have in his contract? ");
				contractHours = myKey.nextInt();
			}
		((StaffMembers) concordiaMember).setContractHours(contractHours);
		}
	
	public static void modifyStudent(ConcordiaMembers concordiaMember){
		Scanner myKey = new Scanner(System.in);
		
		String status = promptStudentStatus();
		((Students) concordiaMember).setStatus(StudentStatus.valueOf(status));
		int contractHours =0;
		if(status =="GRADUATE_TA" || status == "UNDERGRADUATE_TA"){
			System.out.print("How many hours does this individual have in his contract? ");
			contractHours = myKey.nextInt();
		}
		((Students) concordiaMember).setContractHours(contractHours);
	}
	
	public static void modifyFaculty(ConcordiaMembers concordiaMember){
		Scanner myKey = new Scanner(System.in);
		String status = promptFacultyStatus();
		((FacultyMembers) concordiaMember).setStatus(FacultyStatus.valueOf(status));
		int contractHours =0;
		int firstClassSize =0;
		int secondClassSize =0;

			if(status=="PART_TIME"){
				System.out.print("How many hours does this individual have in his contract? ");
				contractHours = myKey.nextInt();
				System.out.print("How many students does this indivual have in his class? ");
				firstClassSize = myKey.nextInt();
				System.out.print("How many students does this indivual have in his second class?\n(If there isn't another class, input 0)");
				secondClassSize = myKey.nextInt();
			}
		((FacultyMembers) concordiaMember).setContractHours(contractHours);
		((FacultyMembers) concordiaMember).setFirstClassSize(firstClassSize);
		((FacultyMembers) concordiaMember).setSecondClassSize(secondClassSize);
	}

	private static void writeToSerializedFile(File file, ConcordiaDatabase database) {
	    try {
	        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));
	        output.writeObject(database);
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	private static ConcordiaDatabase readFromSerializedFile(File file) {
	    ConcordiaDatabase database = null;
	    try {
	        ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
	        database = (ConcordiaDatabase) input.readObject();
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (ClassNotFoundException e) {
	       e.printStackTrace();
	    }
	    return database;
	}

	

} // End of Driver Class