import java.io.Serializable;


@SuppressWarnings("serial")
public class Book implements Serializable { //book changed to Book @kosala
	
	private String title; //T changed to title @kosala 
	private String author; //A changed to author @kosala
	private String callNo; //C changed to callNo @kosala
	private int id; //ID changed to id @kosala
	
	private enum STATE { AVAILABLE, ON_LOAN, DAMAGED, RESERVED };
	private STATE state;
	
	
	public Book(String author, String title, String callNo, int id) { // book changed to Book @kosala
 		this.author = author; //A changed to author @kosala
		this.title = title; //T changed to title @kosala
		this.callNo = callNo; //C changed to callNo @kosala
		this.id = id; //ID changed to id @kosala
		this.state = STATE.AVAILABLE;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Book: ").append(id).append("\n") //ID changed to id @kosala
		  .append("  Title:  ").append(title).append("\n") //T changed to title @kosala
		  .append("  Author: ").append(author).append("\n") //A changed to author @kosala
		  .append("  CallNo: ").append(callNo).append("\n") //C changed to callNo @kosala
		  .append("  State:  ").append(state);
		
		return sb.toString();
	}

	public Integer getID() { //ID changed to getID @kosala
		return id;  
	}

	public String getTitle() { //Title changed to title @kosala
		return title;
	}


	
	public boolean CheckAvailable() { //Available changed to CheckAvailable @kosala
		return state == STATE.AVAILABLE;
	}

	
	public boolean CheckOn_loan() { // On_loan() changed to CheckOn_loan() @kosala
		return state == STATE.ON_LOAN;
	}

	
	public boolean CheckDamaged() { //Damaged() changed to CheckDamaged() @kosala
		return state == STATE.DAMAGED;
	}

	
	public void BorrowBook() { // Borrow() changed to BorrowBook() @kosala
		if (state.equals(STATE.AVAILABLE)) {
			state = STATE.ON_LOAN;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", state));
		}
		
	}


	public void ReturnBook(boolean DAMAGED) {//Return(boolean DAMAGED) changed to ReturnBook(boolean DAMAGED)
		if (state.equals(STATE.ON_LOAN)) {
			if (DAMAGED) {
				state = STATE.DAMAGED;
			}
			else {
				state = STATE.AVAILABLE;
			}
		}
		else {
			throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", state));
		}		
	}

	
	public void RepairBook() {
		if (state.equals(STATE.DAMAGED)) {
			state = STATE.AVAILABLE;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", state));
		}
	}


}
