import java.util.Scanner;


// i have change the naming conflict of the code.

public class BorrowBookUI {
	
	public static enum UI_STATE { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };

	private BorrowBookControl control;
	private Scanner input;
	private UI_STATE state;

	
	public BorrowBookUI(BorrowBookControl control) {
		this.control = control;
		input = new Scanner(System.in);
		state = UI_STATE.INITIALISED;
		control.setUI(this);
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}
	
			
	public void setState(UI_STATE state) {
		this.state = state;
	}

	
	public void run() {
		output("Borrow Book Use Case UI\n");
		
		while (true) {
			
			switch (state) {			
			
			case CANCELLED:
				output("Borrowing Cancelled");
				return;

				
			case READY:
				String memberCardCancel = input("Swipe member card (press <enter> to cancel): ");// change the memStr to the memberCardCancel for avoid 
                                // the conflict with the namming conflict.@ tharindu
				if (memberCardCancel.length() == 0) {// change the memStr to the memberCardCancel for avoid 
                                // the conflict with the namming conflict.@ tharindu
					control.cancel();
					break;
				}
				try {
					int memberId = Integer.valueOf(memberCardCancel).intValue();
					control.Swiped(memberId);
				}
				catch (NumberFormatException e) {
					output("Invalid Member Id");
				}
				break;

				
			case RESTRICTED:
				input("Press <any key> to cancel");
				control.cancel();
				break;
			
				
			case SCANNING:
				String bookComplete = input("Scan Book (<enter> completes): ");// change the bookStr to the bookComplete for avoid 
                                // the conflict with the namming conflict.@ tharindu
				if (bookComplete.length() == 0) {// change the bookStr to the bookComplete for avoid 
                                // the conflict with the namming conflict.@ tharindu
					control.Complete();
					break;
				}
				try {
					int bookId = Integer.valueOf(bookComplete).intValue();// change the bookStr to the bookComplete for avoid 
                                // the conflict with the namming conflict.@ tharindu
					control.Scanned(bookId);
					
				} catch (NumberFormatException e) {
					output("Invalid Book Id");
				} 
				break;
					
				
			case FINALISING:
				String answerForQA = input("Commit loans? (Y/N): ");// change the ans to the answerForQA for avoid 
                                // the conflict with the namming conflict.@ tharindu
				if (answerForQA.toUpperCase().equals("N")) {// change the ans to the answerForQA for avoid 
                                // the conflict with the namming conflict.@ tharindu
					control.cancel();
					
				} else {
					control.commitLoans();
					input("Press <any key> to complete ");
				}
				break;
				
				
			case COMPLETED:
				output("Borrowing Completed");
				return;
	
				
			default:
				output("Unhandled state");
				throw new RuntimeException("BorrowBookUI : unhandled state :" + state);			
			}
		}		
	}


	public void displayDetails(Object object) {// change the display to the displayDetails for avoid 
                                // the conflict with the namming conflict.@ tharindu
		output(object);		
	}


}
