package defaultPackage;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Students extends ConcordiaMembers implements ConcordiaInterface, Serializable{

	//Declaring Constants and Variables
	private StudentStatus status;
	private int contractHours;
	final private double HOURLY_RATE = 11.50;
	final private double HOURLY_RATE_GRADUATE = HOURLY_RATE*1.2;
	private static final long serialVersionUID = 1L;
	
	
	//Constructor
	public Students(String firstName, String lastName, String concordiaID, StudentStatus status, int contractHours) {
	
		super(firstName, lastName, concordiaID);
		
		this.status = status;
		this.contractHours = contractHours;
		}
	
	
	
	//Getters and Setters
	public StudentStatus getStatus() {
		return status;
	}
	
	public void setStatus(StudentStatus status) {
		this.status = status;
	}
	
	public int getContractHours() {
		return contractHours;
	}

	public void setContractHours(int contractHours) {
		this.contractHours = contractHours;
	}

	//Determines monthly payments, adjusting the members hours if needed be
	public double payment() {
		if(status == StudentStatus.GRADUATE_TA){
			int hoursWorked = hoursWorked();
			setContractHours(getContractHours() - hoursWorked);
			return HOURLY_RATE_GRADUATE*hoursWorked;
		}
		else if(status == StudentStatus.UNDERGRADUATE_TA){
			int hoursWorked = hoursWorked();
			setContractHours(getContractHours() - hoursWorked);
			return HOURLY_RATE*hoursWorked;
		}
		else
		return 0;
	}
	
	//Returns hours worked, verifying if the member has enough hours to be paid for in his contract
	public int hoursWorked(){
		Scanner myKey = new Scanner(System.in);
		System.out.print("How many hours has " + getFirstName() + " worked? ");
		int userEntry = myKey.nextInt();
		boolean entry = false;
		
		while(entry==false){
			
			if (userEntry <= getContractHours()){
				entry = true;
				break;
			}
			else
			System.out.println("You've entered an invalid number. " + getContractHours() + " hours remaining.\nTry again!");
			userEntry = myKey.nextInt();
			
		}
		
		return userEntry;
		
	}
	
	//Returns formated information using String Builder
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(getFirstName()).append(" ");
		sb.append(getLastName()).append(" ");
		sb.append(getConcordiaID()).append(" ");
		sb.append(getStatus().toString()).append(" Student").append("\n");
		
		return sb.toString();
	}
	//Prints Full Information using String Builder. Unlike toString() method, viewFullInfo() returns attributes as well
	public String viewFullInfo() {
		DecimalFormat df = new DecimalFormat("#.##");
		StringBuilder sb = new StringBuilder();
		sb.append(getFirstName()).append(" ").append(getLastName()).append(" ");
		sb.append(getConcordiaID()).append(" ").append(getStatus()).append(" ").append("Student ");
		
		if(getStatus() == StudentStatus.NO_LONGER)
			sb.append("No Longer a Student");
		
		if(getStatus() == StudentStatus.GRADUATE_TA)
			sb.append("Hourly Rate: ").append(df.format(HOURLY_RATE_GRADUATE)).append(", ").append("Contract Hours: ").append(getContractHours());
	
		if(getStatus() == StudentStatus.UNDERGRADUATE_TA)
			sb.append("Hourly Rate: ").append(HOURLY_RATE).append(", ").append("Contract Hours: ").append(getContractHours());
		
		return sb.toString();
	}
	//Similar to payment() but doesn't change members contract hours and only returns the amount to be gained in current month
	public double payStub(){
			if(status == StudentStatus.GRADUATE_TA){
				int hoursWorked = hoursWorked();
					return HOURLY_RATE_GRADUATE*hoursWorked;
			}
			else if(status == StudentStatus.UNDERGRADUATE_TA){
				int hoursWorked = hoursWorked();
				return HOURLY_RATE*hoursWorked;
			}
			else
			return 0;
		
		
	}
	
}
