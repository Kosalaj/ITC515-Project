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


	public void setUiState(UI_STATE state) {
		this.uiState = state;
	}

	
	public void run() {
		PrintOutput("Fix Book Use Case UI\n");//output() chaneged to PrintOutput() @kosala
		
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
						PrintOutput("Invalid bookId");//output() chaneged to PrintOutput() @kosala
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
				PrintOutput("Fixing process complete");//output() chaneged to PrintOutput() @kosala
				return;
			
			default:
				PrintOutput("Unhandled state");//output() chaneged to PrintOutput() @kosala
				throw new RuntimeException("FixBookUI : unhandled state :" + uiState);	//state changed to uiState @kosala		
			
			}		
		}
		
	}

	
	private String GetInput(String prompt) {
		System.out.print(prompt);
		return userInput.nextLine(); //input changed to userInput @kosala
	}	
		
		
	private void PrintOutput(Object object) { //output() chaneged to PrintOutput() @kosala
		System.out.println(object);
	}
	

	public void display(Object object) {
		PrintOutput(object);//output() chaneged to PrintOutput() @kosala
	}
	
	
}
