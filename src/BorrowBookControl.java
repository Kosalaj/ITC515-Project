import java.util.ArrayList;
import java.util.List;

public class BorrowBookControl {
	
	private BorrowBookUI ui;
	
	private library L;
	private member M;
	private enum CONTROL_STATE { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };
	private CONTROL_STATE state;
	
	private List<book> PENDING;
	private List<loan> COMPLETED;
	private book BarrowItem;//Change the varibale name with meaningfull @tharindu 23/08/2018
	
	
	public BorrowBookControl() {
		this.L = L.INSTANCE();
		state = CONTROL_STATE.INITIALISED;
	}
	

	public void setUI(BorrowBookUI ui) {
		if (!state.equals(CONTROL_STATE.INITIALISED)) 
			throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");
			
		this.ui = ui;
		ui.setState(BorrowBookUI.UI_STATE.READY);
		state = CONTROL_STATE.READY;		
	}

		
	public void SwipIdOfCard(int memberId) {//change the method name with meaning full @tharindu 23/08/2018
		if (!state.equals(CONTROL_STATE.READY)) 
			throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");
			
		M = L.getMember(memberId);
		if (M == null) {
			ui.display("Invalid memberId");
			return;
		}
		if (L.memberCanBorrow(M)) {
			PENDING = new ArrayList<>();
			ui.setState(BorrowBookUI.UI_STATE.SCANNING);
			state = CONTROL_STATE.SCANNING; }
		else 
		{
			ui.display("Member cannot borrow at this time");
			ui.setState(BorrowBookUI.UI_STATE.RESTRICTED); }}
	
	
	public void ScannIdOfCard(int bookId) {//change the method name with meaning full @tharindu 23/08/2018
		BarrowItem = null;//Change the varibale name with meaningfull @tharindu 23/08/2018
		if (!state.equals(CONTROL_STATE.SCANNING)) {
			throw new RuntimeException("BorrowBookControl: cannot call bookScanned except in SCANNING state");
		}	
		BarrowItem = L.Book(bookId);//Change the varibale name with meaningfull @tharindu 23/08/2018
		if (BarrowItem == null) {
			ui.display("Invalid bookId");
			return;
		}
		if (!BarrowItem.Available()) {//Change the varibale name with meaningfull @tharindu 23/08/2018
			ui.display("Book cannot be borrowed");
			return;
		}
		PENDING.add(BarrowItem);
		for (book BarrowItem : PENDING) {//Change the varibale name with meaningfull @tharindu 23/08/2018
			ui.display(BarrowItem.toString());
		}
		if (L.loansRemainingForMember(M) - PENDING.size() == 0) {
			ui.display("Loan limit reached");
			Complete();
		}
	}
	
	
	public void CompleteIdOfCard() {//change the method name with meaning full @tharindu 23/08/2018
		if (PENDING.size() == 0) {
			cancel();
		}
		else {
			ui.display("\nFinal Borrowing List");
			for (book BorrowBookUI : PENDING) {
				ui.display(BorrowBookUI.toString());
			}
			COMPLETED = new ArrayList<loan>();
			ui.setState(BorrowBookUI.UI_STATE.FINALISING);//Change the varibale name with meaningfull @tharindu 23/08/2018
			state = CONTROL_STATE.FINALISING;
		}
	}


	public void commitLoansOfExcept() {//change the method name with meaning full @tharindu 23/08/2018
		if (!state.equals(CONTROL_STATE.FINALISING)) {
			throw new RuntimeException("BorrowBookControl: cannot call commitLoans except in FINALISING state");
		}	
		for (book BarrowItem : PENDING) {
			loan loan = L.issueLoan(BarrowItem, M);
			COMPLETED.add(loan);			
		}
		ui.display("Completed Loan Slip");
		for (loan loan : COMPLETED) {
			ui.display(loan.toString());
		}
		ui.setState(BorrowBookUI.UI_STATE.COMPLETED);//Change the varibale name with meaningfull @tharindu 23/08/2018
		state = CONTROL_STATE.COMPLETED;
	}

	
	public void cancelOfLoan() {//change the method name with meaning full @tharindu 23/08/2018
		ui.setState(BorrowBookUI.UI_STATE.CANCELLED);
		state = CONTROL_STATE.CANCELLED;
	}
	
	
}
