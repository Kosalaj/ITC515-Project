import java.util.Scanner;


public class FixBookUI {

	public static enum UI_STATE { INITIALISED, READY, FIXING, COMPLETED };

	private FixBookControl fixBookControl; //control changed to fixBookControl
	private Scanner userInput; //input changed to userInput @kosala
	private UI_STATE uiState;//state changed to UiState @kosala

	
	public FixBookUI(FixBookControl control) {
		this.fixBookControl = control;
		userInput = new Scanner(System.in);
		uiState = UI_STATE.INITIALISED;
		fixBookControl.setUI(this);
	}


	public void setState(UI_STATE state) {
		this.uiState = state;
	}

	
	public void run() {
		output("Fix Book Use Case UI\n");
		
		while (true) {
			
			switch (uiState) {//state changed to uiState @kosala
			
			case READY:
				String bookStr = input("Scan Book (<enter> completes): ");
				if (bookStr.length() == 0) {
					fixBookControl.scanningComplete();
				}
				else {
					try {
						int bookId = Integer.valueOf(bookStr).intValue();
						fixBookControl.bookScanned(bookId);
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}
				}
				break;	
				
			case FIXING:
				String ans = input("Fix Book? (Y/N) : ");
				boolean fix = false;
				if (ans.toUpperCase().equals("Y")) {
					fix = true;
				}
				fixBookControl.fixBook(fix);
				break;
								
			case COMPLETED:
				output("Fixing process complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("FixBookUI : unhandled state :" + uiState);	//state changed to uiState @kosala		
			
			}		
		}
		
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		return userInput.nextLine(); //input changed to userInput @kosala
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}
	

	public void display(Object object) {
		output(object);
	}
	
	
}
