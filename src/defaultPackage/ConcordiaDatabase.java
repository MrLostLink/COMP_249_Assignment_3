package defaultPackage;

import java.text.DecimalFormat;
import java.util.Scanner;

import org.apache.commons.lang3.ArrayUtils;

public class ConcordiaDatabase {

	//Declaring Constants and Variables
	private ConcordiaMembers[] concordiaMembers;
	Scanner myKey = new Scanner(System.in);
	final private int MAX_ARRAY_SIZE =100;
	private int memberCount=0;
	
	//Contructor Class
	public ConcordiaDatabase() {
		this.concordiaMembers = new ConcordiaMembers[MAX_ARRAY_SIZE];
	}
	
	//Implemented to add member to Array
	public void addMember(Object o){
				concordiaMembers[memberCount]=(ConcordiaMembers) o;
				memberCount++;
			}
	
	//Implemented to remove user from Array, using ArrayUtils Apache class which has been imported
	public boolean removeUser(String firstName, String lastName, String concordiaID){
		
		int index = 0;
		
		for(ConcordiaMembers member: concordiaMembers){
			if(member!=null){
			if(member.getFirstName().equals(firstName) && member.getLastName().equals(lastName) && member.getConcordiaID().equals(concordiaID)){
				concordiaMembers = ArrayUtils.remove(concordiaMembers, index);
				return true;
				}
			}
			index++;
		}
		
		return false;
	}
	
	//Implemented to list select Users
	public String listDatabase(){
		StringBuilder sb = new StringBuilder();
		boolean validEntry = false;
		int userOption;
		
		
		do{
			System.out.println("Press 1 for to list all members\nPress 2 for Students\nPress 3 for Faculty\nPress 4 for Staff Members\n");
			userOption = myKey.nextInt();
			switch(userOption){
				case 1: { 
					for(ConcordiaMembers member: concordiaMembers){
						if(member!= null)
						sb.append(member.toString());
					}
					validEntry=true;
					break;
					
				}
				
				case 2: { 
					for(ConcordiaMembers member: concordiaMembers){
						if(member!= null && (member instanceof Students))
						sb.append(member.toString());
					}
					validEntry=true;
					break;
					
				}
				
				case 3: { 
					for(ConcordiaMembers member: concordiaMembers){
						if(member!= null && (member instanceof FacultyMembers))
						sb.append(member.toString());
					}
					validEntry=true;
					break;
					
				}
				
				case 4: { 
					for(ConcordiaMembers member: concordiaMembers){
						if(member!= null && (member instanceof StaffMembers) )
						sb.append(member.toString());
					}
					validEntry=true;
					break;
					
				}
				default:
					System.out.println("Invalid Entry!\n");
					
			}
		}
		while(validEntry == false);
			
		if(sb.length()==0)
			sb.append("No such members on Database.\n");
		
		return sb.toString();
		
	}
	
	//Returns size of Array
	public int concordiaMembersSize(){
		return concordiaMembers.length;
	}

	//Returns full information of a Member
	public String viewMember(String firstName, String lastName, String concordiaID){
		
		String foundMember;
		DecimalFormat df = new DecimalFormat("#.##");
		
		for(ConcordiaMembers member: concordiaMembers){
			if(member!=null){
			if(member.getFirstName().equals(firstName) && member.getLastName().equals(lastName) && member.getConcordiaID().equals(concordiaID)){
				foundMember= member.viewFullInfo();
				return foundMember;
				}
			}
			
		}
		
		;
		return "No one matching that information has been found.\n";
	}

	//Prints payStub of each member, even if they return 0
	public String payStub(){
		StringBuilder sb = new StringBuilder();
		DecimalFormat df = new DecimalFormat("#.##");
		
		for(ConcordiaMembers member: concordiaMembers){
			if(member!= null)
			sb.append(member.toString()).append("Payment: ").append(df.format(member.payStub())).append("\n");
		}
		
		if(sb.length()==0){
			sb.append("0.00");
		}
		return sb.toString();
	}

	//Returns total amount to be paid by Concordia
	public double totalMonth(){
		double totalMonth=0;
		DecimalFormat df = new DecimalFormat("#.##");
		for(ConcordiaMembers member: concordiaMembers){
			if(member!= null)
				totalMonth += member.payment();
				}
		return totalMonth;
	}

	//Verifies if select member is a qualified to be a TA
	public String QualifiedTA(String firstName, String lastName, String concordiaID){
		for(ConcordiaMembers member: concordiaMembers){
			if(member!=null){
			if(member.getFirstName().equals(firstName) && member.getLastName().equals(lastName) && member.getConcordiaID().equals(concordiaID)){
				if(member instanceof Students){
					if (((Students) member).getStatus()==StudentStatus.GRADUATE_TA || ((Students) member).getStatus()==StudentStatus.UNDERGRADUATE_TA)
						return "\nThis Member of Concordia is already a TA!";
					
					else if(((Students) member).getStatus()==StudentStatus.GRADUATE || ((Students) member).getStatus() == StudentStatus.UNDERGRADUATE)
						return "\nThis Student has the potential to be a TA!";
					
					else
						return "\nThis Student cannot unfortunately be a TA!";	
					}
						return "\nThis individual is a not a Student, hence cannot be a TA!";
				}
			}
		}
		
		return "\nNo such member found!";	
	}

	//Returns ConcordiaMember if select user is found within the database
	public ConcordiaMembers searchMember(String firstName, String lastName, String concordiaID){
		
		for(ConcordiaMembers member: concordiaMembers){
			if(member!=null){
			if(member.getFirstName().equals(firstName) && member.getLastName().equals(lastName) && member.getConcordiaID().equals(concordiaID)){
				return member;
				}
			}
		}
		return null;
	}


}
