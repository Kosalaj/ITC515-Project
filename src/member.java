import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class member implements Serializable {
	/**
	* @author  Chathuranga Muthukumarana.
	* @date   2018-08-09 
	*/
	//change the LN as 'lastname'
	private String lastname;
	//change the FN as 'firstName'
	private String firstName;
	//change the EM as 'email'
	private String email;
	//change the PN as 'phoneNo'
	private int phoneNo;	
	//change the ID as 'id'
	private int id;
	//change the FINES as 'fines'
	private double fines;

	//change the FNS as 'loans'	
	private Map<Integer, loan> loans;

	
	public member(String lastName, String firstName, String email, int phoneNo, int id) {
		//change the property call as lastName
		this.lastName = lastName;
		//change the property call as firstName
		this.firstName = firstName;
		//change the property call as email
		this.email = email;
		//change the property call as phoneNo
		this.phoneNo = phoneNo;
		//change the property call as id
		this.id = id;

		//change the property call as loans
		this.loans = new HashMap<>();//
	}

	
	public String toString() {
		//change the sb as 'objStringbuilder '
		StringBuilder objStringbuilder = new StringBuilder();
		//change the ID as 'id'
		objStringbuilder.append("Member:  ").append(id).append("\n")
		//change the FN as 'firstName' and LN as 'lastname'
		  .append("  Name:  ").append(lastName).append(", ").append(firstName).append("\n")
		//change the EM as 'email'
		  .append("  Email: ").append(email).append("\n")
		//change the PN as 'phoneNo'
		  .append("  Phone: ").append(phoneNo)
		  .append("\n")
		//change the FINES as 'fines'
		  .append(String.format("  Fines Owed :  $%.2f", fines))//
		  .append("\n");
		
		//change the property call as loans
		for (loan loan : loans.values()) //{
			//change the sb as 'objStringbuilder '
			objStringbuilder .append(loan).append("\n");
		}		  
		return objStringbuilder .toString();
	}

	
	public int getId() {
		//change the ID as 'id'
		return id;
	}

	
	public List<loan> getLoans() {
		//change the property call as loans
		return new ArrayList<loan>(loans.values());
	}

	
	public int getNumberOfCurrentLoans() {
		//change the property call as loans
		return loans.size();
	}

	
	public double getFinesOwed() {
		//change the FINES as 'fines'
		return fines;
	}

	
	public void takeOutLoan(loan loan) {
		//change the property call as loans
		if (!loans.containsKey(loan.getId())) {
			loans.put(loan.getId(), loan);
		}
		else {
			throw new RuntimeException("Duplicate loan added to member");
		}		
	}

	
	public String getLastName() {
		//change the LN as 'lastname'
		return lastname;
	}

	
	public String getFirstName() {
		//change the FN as 'firstName'
		return firstname;
	}


	public void addFine(double fine) {
		//change the FINES as 'fines'
		fines += fine;
	}
	
	public double payFine(double amount) {
		if (amount < 0) {
			throw new RuntimeException("Member.payFine: amount must be positive");
		}
		double change = 0;
		//change the FINES as 'fines'
		if (amount > fines) {
			//change the FINES as 'fines'
			change = amount - fines;
			fines = 0;
		}
		else {
			//change the FINES as 'fines'
			fines -= amount;
		}
		return change;
	}


	public void dischargeLoan(loan loan) {
		//change the FNS as 'loans'
		if (loans.containsKey(loan.getId())) {
			//change the FNS as 'loans'
			loans.remove(loan.getId());
		}
		else {
			throw new RuntimeException("No such loan held by member");
		}		
	}

}
