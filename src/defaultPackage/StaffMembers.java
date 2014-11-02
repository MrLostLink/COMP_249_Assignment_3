package defaultPackage;

import java.util.Scanner;

public class StaffMembers extends ConcordiaMembers implements ConcordiaInterface {
		
		private StaffStatus status;
		private double contractHours;
		Scanner myKey = new Scanner(System.in);
		final private double hourlyRate= 11.50;
		final private double annualSalary = 65000;
		private double commission;

		
		
		public StaffMembers(String Firstname,String Secondname,String id, StaffStatus status, double contractHours,double commission){
		
		super(Firstname,Secondname,id);
			this.status=status;
			this.contractHours=contractHours;
			this.commission=commission;
		
		
		}
		
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
				double monthlySalary=(annualSalary/12)+commission;
				return monthlySalary;
			}
			
			else
				return 0;
			
			}
		
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
		
		public String toString() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(getFirstName()).append(" ");
			sb.append(getLastName()).append(" ");
			sb.append(getConcordiaID()).append(" ");
			sb.append(getStatus().toString()).append("\n");
			
			return sb.toString();
		}		
		
		public StaffStatus getStatus() {
			return status;
		}

		
		public double getContractHours() {
			return contractHours;
		}

		public void setContractHours(double contractHours) {
			this.contractHours = contractHours;
		}

		public double getCommission() {
			return commission;
		}

		public void setCommission(double commission) {
			this.commission = commission;
		}

		public void setStatus(StaffStatus status) {
			this.status = status;
		}

		
		public String viewFullInfo() {
			
			StringBuilder sb = new StringBuilder();
			sb.append(getFirstName()).append(" ").append(getLastName()).append(" ");
			sb.append(getConcordiaID()).append(" ").append(getStatus()).append(" ");
			
			if(getStatus() == StaffStatus.PERM_STAFF)
				sb.append("Annual_Salary: ").append(annualSalary);
			
			if(getStatus() == StaffStatus.TEMP_STAFF)
				sb.append("Contract Hours: ").append(getContractHours()).append(", Hourly Rate: ").append(hourlyRate);
			
			if(getStatus() == StaffStatus.OTHER)
				sb.append("Annual_Salary: ").append(annualSalary).append(", Commission: ").append(getCommission());
			
			return sb.toString();
		}

		public double payStub(){
			int hoursWorked;
			
			if(status==StaffStatus.PERM_STAFF)
				return (annualSalary/12);
			
			if (status==StaffStatus.TEMP_STAFF){
				hoursWorked = hoursWorked();
				return hoursWorked*hourlyRate;
				}
			
			if (status==StaffStatus.OTHER){	
				double monthlySalary=(annualSalary/12)+commission;
				return monthlySalary;
			}
			
			else
				return 0;
			
			}

		}
	