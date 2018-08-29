import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class Loan implements Serializable { //loan changed to Loan
	
	public static enum LOAN_STATE { CURRENT, OVER_DUE, DISCHARGED };
	

	private int loanId; //ID changed to loanId @kosala
	private Book book; //book changed to Book @kosala ,B changed to book
	private Member member; //M changed to member @kosala, member change to Member @kosqala
	private Date dueDate; //D changed to dueDate @kosala
	private LOAN_STATE loanState; //state to loanState @kosala

	
	public loan(int loanId, Book book, member member, Date dueDate) { //book changed to Book
		this.loanId = loanId; //ID changws to loanId @kosala
		this.book = book; //B changed to book @kosala
		this.member = member; //M changed to member @kosala
		this.dueDate = dueDate; //D changed to duedate @kosala
		this.loanState = LOAN_STATE.CURRENT; //state changed to loanState @kosala

	

	}

	
	public void checkOverDue() {
		if (loanState == LOAN_STATE.CURRENT &&
			Calendar.getInstance().Date().after(dueDate)) { //D changed to dueDate @kosala
			this.loanState = LOAN_STATE.OVER_DUE;	//state changed to loanState @kosala
		}
	}

	
	public boolean isOverDue() {
		return loanState == LOAN_STATE.OVER_DUE; //state change to loanState @kosala
	}

	
	public Integer getId() {
		return loanId; //ID changed to loanId @kosala
	}


	public Date getDueDate() {
		return dueDate;//D changed to dueDate @kosala
	}
	
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		StringBuilder sb = new StringBuilder();
		sb.append("Loan:  ").append(loanId).append("\n")//ID changed to loanId @kosala
		  .append("  Borrower ").append(member.getId()).append(" : ") //M changed to member @kosala
		  .append(member.getLastName()).append(", ").append(member.getFirstName()).append("\n") //M changed to member @kosala
		  .append("  Book ").append(member.ID()).append(" : " ) //B changed to book
		  .append(book.Title()).append("\n") //B changed to book
		  .append("  DueDate: ").append(sdf.format(dueDate)).append("\n") //D changed to dueDate
		  .append("  State: ").append(loanState);		//state changed to loaState
		return sb.toString();
	}



	public Member getMember() { //member changed to Member @kosala ,Member changed to getMember()

		return member; // M changed to member
	}



	public Book Book() { //book changed to Book @kosala
		return book; //B changed to book @kosala

		return B; //B changed to book kosala
	}


	public void Loan() {
		loanState = LOAN_STATE.DISCHARGED;	//state changed to loanState @kosala	
	}

}
