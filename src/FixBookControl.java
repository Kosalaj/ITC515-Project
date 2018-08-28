public class FixBookControl {
	
	private FixBookUI fixBookUI;//ui changed to fixBookUI @kosala
	private enum CONTROL_STATE { INITIALISED, READY, FIXING };
	private CONTROL_STATE controlState;
	
	private Library library;//library changed to Library @kosala
	private Book currentBook; //book changed to Book @kosala


	public FixBookControl() {
		this.library = library.INSTANCE();
		controlState = CONTROL_STATE.INITIALISED; //state changed to controlState 
	}
	
	
	public void setUI(FixBookUI fixBookUI) {//ui changed to fixBookUI @kosala
		if (!controlState.equals(CONTROL_STATE.INITIALISED)) {//state changed to controlState 
			throw new RuntimeException("FixBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.fixBookUI = fixBookUI;//ui changed to fixBookUI @kosala
		fixBookUI.setState(FixBookUI.UI_STATE.READY);//ui changed to fixBookUI @kosala
		controlState = CONTROL_STATE.READY;	//state changed to controlState 	
	}


	public void bookScanned(int bookId) {
		if (!controlState.equals(CONTROL_STATE.READY)) {//state changed to controlState 
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
		}	
		currentBook = library.Book(bookId);
		
		if (currentBook == null) {
			fixBookUI.display("Invalid bookId");//ui changed to fixBookUI @kosala
			return;
		}
		if (!currentBook.Damaged()) {
			fixBookUI.display("\"Book has not been damaged");//ui changed to fixBookUI @kosala
			return;
		}
		controlState.display(currentBook.toString());//ui changed to fixBookUI @kosala
		controlState.setState(FixBookUI.UI_STATE.FIXING);//ui changed to fixBookUI @kosala
		controlState = CONTROL_STATE.FIXING;//state changed to controlState 		
	}


	public void fixBook(boolean fix) {
		if (!controlState.equals(CONTROL_STATE.FIXING)) {//state changed to controlState 
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
		}	
		if (fix) {
			library.repairBook(currentBook);
		}
		currentBook = null;
		controlState.setState(FixBookUI.UI_STATE.READY);//ui changed to fixBookUI @kosala
		controlState = CONTROL_STATE.READY;//state changed to controlState 		
	}

	
	public void scanningComplete() {
		if (!controlState.equals(CONTROL_STATE.READY)) {//state changed to controlState 
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
		}	
		controlState.setState(FixBookUI.UI_STATE.COMPLETED);	//ui changed to fixBookUI @kosala	
	}






}
