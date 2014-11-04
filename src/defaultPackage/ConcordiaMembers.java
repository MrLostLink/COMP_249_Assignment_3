package defaultPackage;

import java.io.Serializable;

public  abstract class ConcordiaMembers implements Serializable{

		//Parent class  which links all the child classes due to the similarities in members

		// declaring constants and variables
		private String firstName;		
		private String lastName;
		private String concordiaID;		
		private static final long serialVersionUID = 1L;
	
		
		
		//constructors
		ConcordiaMembers(String firstName, String lastName, String concordiaID){
			this.firstName=firstName;
			this.lastName=lastName;
			this.concordiaID=concordiaID;
		}

		//Getters and Setters
		public String getFirstName() {
			return firstName;
		}


		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}


		public String getLastName() {
			return lastName;
		}


		public void setLastName(String lastName) {
			this.lastName = lastName;
		}


		public String getConcordiaID() {
			return concordiaID;
		}


		public void setConcordiaID(String concordiaID) {
			this.concordiaID = concordiaID;
		}

		//Abstract classes implemented on Child Classes; Check Child classes for descriptions
		public abstract String viewFullInfo();
	
		public abstract double payment();

		public abstract double payStub(); 
			
}
