package defaultPackage;

import java.io.Serializable;
import java.util.Scanner;

public class FacultyMembers extends ConcordiaMembers implements ConcordiaInterface, Serializable {


	//Declaring Constants and Variables
	FacultyStatus status;
	private int firstClassSize;
	private int secondClassSize;
	private int contractHours;
	private static final long serialVersionUID = 1L;
	
	
	final private double hourlyRate = 20;
	final private double annualSalary = 70000;
	final private double commissionTier1 = 500;
	final private double commissionTier2 = 1000;
	
	//Constructors
	public FacultyMembers(String firstName, String lastName, String concordiaID,FacultyStatus status, int contractHours, int firstClassSize, int secondClassSize) {
		super(firstName, lastName, concordiaID);
		
		this.status=status;
		this.contractHours=contractHours;
		this.firstClassSize=firstClassSize;
		this.secondClassSize=secondClassSize;
	}

	//Used to calculate payment due to Faculty Members, substracting any hours from their Contract Hours
	public double payment(){
		int hoursWorked;
		if (status == FacultyStatus.PERMANENT){
			return (annualSalary/12);
			}
		
		else{
			hoursWorked = hoursWorked();
			setContractHours(getContractHours() - hoursWorked);
		}
		return ( (hoursWorked*hourlyRate) + commission(firstClassSize) + commission(secondClassSize) );
	}
	
	//Using Stringbuilder, returns String in proper format 
	public String toString(){
		
		StringBuilder sb = new StringBuilder();
		sb.append(getFirstName()).append(" ");
		sb.append(getLastName()).append(" ");
		sb.append(getConcordiaID()).append(" ");
		sb.append(getStatus().toString()).append(" Faculty Member ").append("\n");
		
		return sb.toString();
	}
	//Verifies if Member has enough hours and returns the amount of hours worked
	public int hoursWorked(){
		Scanner myKey = new Scanner(System.in);
		System.out.print("How many hours has " + getFirstName() + " worked? ");
		int userEntry = myKey.nextInt();
		boolean entry=false;
		
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
	//Returns commission based on the class size
	public double commission(int classSize){
		if(classSize>=40 && classSize<=60){
			return commissionTier1;
			}
		if(classSize>60){
			return commissionTier2;
			}
		return 0;
	}
	//Getter and Setters
	public FacultyStatus getStatus() {
		return status;
	}

	public void setStatus(FacultyStatus status) {
		this.status = status;
	}

	public int getFirstClassSize() {
		return firstClassSize;
	}

	public void setFirstClassSize(int firstClassSize) {
		this.firstClassSize = firstClassSize;
	}

	public int getSecondClassSize() {
		return secondClassSize;
	}

	public void setSecondClassSize(int secondClassSize) {
		this.secondClassSize = secondClassSize;
	}

	public int getContractHours() {
		return contractHours;
	}

	public void setContractHours(int contractHours) {
		this.contractHours = contractHours;
	}

	//Prints Full Information using String Builder. Unlike toString() method, viewFullInfo() returns attributes as well
	public String viewFullInfo() {

			StringBuilder sb = new StringBuilder();
			sb.append(getFirstName()).append(" ").append(getLastName()).append(" ");
			sb.append(getConcordiaID()).append(" ").append(getStatus()).append(" ").append("Faculty Member ");
			
			if(getStatus() == FacultyStatus.PERMANENT){
				sb.append("Annual Salary: ").append(annualSalary);
			}
			
			if(getStatus() == FacultyStatus.PART_TIME){
				sb.append("Contract Hours: ").append(getContractHours()).append(", Hourly Rate: ");
				sb.append(hourlyRate).append(", Class 1 Size: ").append(getFirstClassSize());
				
				if(getSecondClassSize()!=0){
					sb.append(", Class 2 Size: ").append(getSecondClassSize());
			}
		}	
		
			return sb.toString();
		}
	
	//Similar to payment() but doesn't change members contract hours and only returns the amount to be gained in current month
	public double payStub(){
			int hoursWorked;
			if (status == FacultyStatus.PERMANENT){
				return (annualSalary/12);
				}
			
			else{
				hoursWorked = hoursWorked();
				}
			return ( (hoursWorked*hourlyRate) + commission(firstClassSize) + commission(secondClassSize) );
		
		
	}


}

	

