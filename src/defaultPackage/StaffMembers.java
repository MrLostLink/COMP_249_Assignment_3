package defaultPackage;

import java.util.Scanner;

public class StaffMembers extends ConcordiaMembers implements ConcordiaInterface {
		
		//Variables and Constants
		private StaffStatus status;
		private double contractHours;
		Scanner myKey = new Scanner(System.in);
		final private double hourlyRate= 11.50;
		final private double annualSalary = 65000;
		private double commission;

		
		//Constructor 
		public StaffMembers(String Firstname,String Secondname,String id, StaffStatus status, double contractHours){
		
		super(Firstname,Secondname,id);
			this.status=status;
			this.contractHours=contractHours;
		
		
		}
		
		//Determines monthly payments, adjusting the members hours if needed be
		public double payment() {
			int hoursWorked;
			
			if(status==StaffStatus.PERM_STAFF)
				return (annualSalary/12);
			
			if (status==StaffStatus.TEMP_STAFF){
				hoursWorked = hoursWorked();
				setContractHours(getContractHours() - hoursWorked);
				return hoursWorked*hourlyRate;
				}
			
			if (status==StaffStatus.OTHER){	
				double monthlySalary=((annualSalary/12)*(1+(commission()/100)));
				return monthlySalary;
			}
			
			else
				return 0;
			
			}
		//Returns hours worked, verifying if the member has enough hours to be paid for in his contract
		public int hoursWorked(){
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
			sb.append(getStatus().toString()).append(" Staff Member ").append("\n");
			
			return sb.toString();
		}		
		//Getters and Setters
		public StaffStatus getStatus() {
			return status;
		}
		
		public double getContractHours() {
			return contractHours;
		}

		public void setContractHours(double contractHours) {
			this.contractHours = contractHours;
		}

		public void setStatus(StaffStatus status) {
			this.status = status;
		}

		//Prints Full Information using String Builder. Unlike toString() method, viewFullInfo() returns attributes as well
		public String viewFullInfo() {
			
			StringBuilder sb = new StringBuilder();
			sb.append(getFirstName()).append(" ").append(getLastName()).append(" ");
			sb.append(getConcordiaID()).append(" ").append(getStatus()).append(" ").append("Staff Member ");
			
			if(getStatus() == StaffStatus.PERM_STAFF)
				sb.append("Annual_Salary: ").append(annualSalary);
			
			if(getStatus() == StaffStatus.TEMP_STAFF)
				sb.append("Contract Hours: ").append(getContractHours()).append(", Hourly Rate: ").append(hourlyRate);
			
			if(getStatus() == StaffStatus.OTHER)
				sb.append("Annual_Salary: ").append(annualSalary);
			
			return sb.toString();
		}
		//Similar to payment() but doesn't change members contract hours and only returns the amount to be gained in current month
		public double payStub(){
			int hoursWorked;
			
			if(status==StaffStatus.PERM_STAFF)
				return (annualSalary/12);
			
			if (status==StaffStatus.TEMP_STAFF){
				hoursWorked = hoursWorked();
				return hoursWorked*hourlyRate;
				}
			
			if (status==StaffStatus.OTHER){	
				double monthlySalary=((annualSalary/12)*(1+(commission()/100)));
				return monthlySalary;
			}
			
			else
				return 0;
			
			}
		//Determines commission percentage defined by Admin (user)
		public double commission(){
			System.out.print("What is the commission of "+ getFirstName() +" for the current month in percentage? ");
			double commission = myKey.nextDouble();
			return commission;
			}

		}
	