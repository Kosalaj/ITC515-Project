public class ReturnBookControl {

	private ReturnBookUI ui;
	private enum CONTROL_STATE { INITIALISED, READY, INSPECTING };
	private CONTROL_STATE state;
	
	private library libraryOfReturn;//Change the varibale name with meaningfull @tharindu 23/08/2018
	private loan currentLoan;
	//chnage the code stadndalize @tharindu 22/08/2018

	public ReturnBookControl() {
		this.library = library.INSTANCE();
		state = CONTROL_STATE.INITIALISED;
	}
	
	
	public void setOfReturnBookUI(ReturnBookUI ui) {//change the method name for meaningfulled name @tharindu 26/08/2018
		if (!state.equals(CONTROL_STATE.INITIALISED)) {
			throw new RuntimeException("ReturnBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.ui = ui;
		ui.setState(ReturnBookUI.UI_STATE.READY);
		state = CONTROL_STATE.READY;//change the code structure with standad way and remove the unnecessary spaces and		
	}
	//chnage the code stadndalize @tharindu 22/08/2018

	public void bookScannedControl(int bookId) {//change the method name for meaningfulled name @tharindu 26/08/2018
		if (!state.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("ReturnBookControl: cannot call bookScanned except in READY state");
		}	
		book currentBook = libraryOfReturn.Book(bookId);//Change the varibale name with meaningfull @tharindu 23/08/2018
		
		if (currentBook == null) {
			ui.display("Invalid Book Id");
			return;
		}
		if (!currentBook.On_loan()) {
			ui.display("Book has not been borrowed");
			return;
		}		
		currentLoan = libraryOfReturn.getLoanByBookId(bookId);	//Change the varibale name with meaningfull @tharindu 23/08/2018
		double overDueFine = 0.0;
		if (currentLoan.isOverDue()) {
			overDueFine = libraryOfReturn.calculateOverDueFine(currentLoan);//Change the varibale name with meaningfull @tharindu 23/08/2018
		}
		ui.display("Inspecting");
		ui.display(currentBook.toString());
		ui.display(currentLoan.toString());
		
		if (currentLoan.isOverDue()) {
			ui.display(String.format("\nOverdue fine : $%.2f", overDueFine));
		}
		ui.setState(ReturnBookUI.UI_STATE.INSPECTING);
		state = CONTROL_STATE.INSPECTING;		
	}



	public void scanningCompleteOfBoook() {//change the method name for meaningfulled name @tharindu 26/08/2018
		if (!state.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("ReturnBookControl: cannot call scanningComplete except in READY state");
		}	
		ui.setState(ReturnBookUI.UI_STATE.COMPLETED);		
	}


	public void dischargeLoan(boolean isDamaged) {
		if (!state.equals(CONTROL_STATE.INSPECTING)) {
			throw new RuntimeException("ReturnBookControl: cannot call dischargeLoan except in INSPECTING state");
		}	
		libraryOfReturn.dischargeLoan(currentLoan, isDamaged);
		currentLoan = null;
		ui.setState(ReturnBookUI.UI_STATE.READY);
		state = CONTROL_STATE.READY;				
	}


}
