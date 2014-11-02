package defaultPackage;

import java.util.Scanner;
	
public class Students extends ConcordiaMembers implements ConcordiaInterface{
	private StudentStatus status;
	boolean entry;
	private int contractHours;
	final private double HOURLY_RATE = 11.50;
	final private double HOURLY_RATE_GRADUATE = HOURLY_RATE*1.2;
	static Scanner myKey = new Scanner(System.in);
	
	public Students(String firstName, String lastName, String concordiaID, StudentStatus status, int contractHours) {
	
		super(firstName, lastName, concordiaID);
		
		this.status = status;
		this.contractHours = contractHours;
		}
	
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
	
	public int hoursWorked(){
		System.out.print("How many hours has " + getFirstName() + " worked? ");
		int userEntry = myKey.nextInt();
		entry = false;
		
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
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(getFirstName()).append(" ");
		sb.append(getLastName()).append(" ");
		sb.append(getConcordiaID()).append(" ");
		sb.append(getStatus().toString()).append("\n");
		
		return sb.toString();
	}
	public String viewFullInfo() {
		
		StringBuilder sb = new StringBuilder();
		sb.append(getFirstName()).append(" ").append(getLastName()).append(" ");
		sb.append(getConcordiaID()).append(" ").append(getStatus()).append(" ");
		
		if(getStatus() == StudentStatus.NO_LONGER)
			sb.append("No Longer a Student");
		
		if(getStatus() == StudentStatus.GRADUATE_TA)
			sb.append("Hourly Rate: ").append(HOURLY_RATE_GRADUATE).append(", ").append("Contract Hours: ").append(getContractHours());
	
		if(getStatus() == StudentStatus.UNDERGRADUATE_TA)
			sb.append("Hourly Rate: ").append(HOURLY_RATE_GRADUATE).append(", ").append("Contract Hours: ").append(getContractHours());
		
		return sb.toString();
	}
	
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
