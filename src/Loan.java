import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class Loan implements Serializable { // loan changed to Loan @kosala
	
	public static enum LOAN_STATE { CURRENT, OVER_DUE, DISCHARGED };
	
	private int loanIdd; //ID changed to loanId
	private Book book; //book changed to Book @kosala ,B changed to book
	private member M; //M changed to member@kosala, member change to Member @kosqala
	private Date dueDate; //D changed to dueDate @kosala
	private LOAN_STATE loanState; //state to loanState @kosala

	
	public loan(int loanId, Book book, member member, Date dueDate) { //book changed to Book
		this.loanId = loanId; //ID changws to loanId @kosala
		this.book = book; //B changed to book @kosala
		this.member = member; //M changed to member @kosala
		this.dueDate = dueDate; //D changed to duedate @kosala
		this.loneState = LOAN_STATE.CURRENT; //state changed to loanState @kosala
	}

	
	public void checkOverDue() {
		if (state == LOAN_STATE.CURRENT &&
			Calendar.getInstance().Date().after(D)) { //D changed to dueDate @kosala
			this.loanState = LOAN_STATE.OVER_DUE;	//state changed to loanState @kosala
		}
	}

	
	public boolean isOverDue() {
		return state == LOAN_STATE.OVER_DUE; //state change to loanState @kosala
	}

	
	public Integer getId() {
		return ID; //ID changed to loanId @kosala
	}


	public Date getDueDate() {
		return D;//D changed to dueDate @kosala
	}
	
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		StringBuilder sb = new StringBuilder();
		sb.append("Loan:  ").append(ID).append("\n")//ID changed to loanId @kosala
		  .append("  Borrower ").append(M.getId()).append(" : ") //M changed to member @kosala
		  .append(M.getLastName()).append(", ").append(M.getFirstName()).append("\n") //M changed to member @kosala
		  .append("  Book ").append(B.ID()).append(" : " ) //B changed to book
		  .append(B.Title()).append("\n") //B changed to book
		  .append("  DueDate: ").append(sdf.format(D)).append("\n") //D changed to dueDate
		  .append("  State: ").append(state);		//state changed to loaState
		return sb.toString();
	}


	public member Member() { //member changed to Member
		return M; // M changed to menber
	}


	public Book Book() { //book changed to Book @kosala
		return B; //B changed to book
	}


	public void Loan() {
		state = LOAN_STATE.DISCHARGED;	//state changed to loanState	
	}

}
