import java.util.Scanner;


public class ReturnBookUI {

	public static enum UI_STATE { INITIALISED, READY, INSPECTING, COMPLETED };

	private ReturnBookControl controlOfReturn;// change the variable name and make it meaningfull.
	private Scanner inputScanner;//change the variable name and make it meaningfull.@tharindu 20/08/2018
	private UI_STATE state;

	
	public ReturnBookUI(ReturnBookControl controlOfReturn) {
		this.controlOfReturn = controlOfReturn; // change the variable name and make it meaningfull.
		input = new Scanner(System.in);
		state = UI_STATE.INITIALISED;
		controlOfReturn.setUI(this);// change the variable name and make it meaningfull.
	}


	public void run() {		
		output("Return Book Use Case UI\n");
		
		while (true) {
			
			switch (state) {
			
			case INITIALISED:
				break;
				
			case READY:
				String bookStr = input("Scan Book (<enter> completes): ");
				if (bookStr.length() == 0) {
					controlOfReturn.scanningComplete();// change the variable name and make it meaningfull.
				}
				else {
					try {
						int bookId = Integer.valueOf(bookStr).intValue();
						controlOfReturn.bookScanned(bookId);// change the variable name and make it meaningfull.
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}					
				}
				break;				
				
			case INSPECTING: 
				String answer = input("Is book damaged? (Y/N): ");//chnage the varible name to answer @tharindu 22/08/2018
				boolean isDamaged = false;
				if (answer.toUpperCase().equals("Y")) {//change the varible name to answer @tharindu 22/08/2018					
					isDamaged = true;
				}
				controlOfReturn.dischargeLoan(isDamaged); 
			
			case COMPLETED:
				output("Return processing complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("ReturnBookUI : unhandled state :" + state);			
			}
		}
	}

	
	private String inputData(String prompt) {//chnage the method name with meaningfully @tharindu 22/08/2018
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void outputData(Object object) {//change the method name with meaningfully @tharindu 22/08/2018
		System.out.println(object);
	}
	
			
	public void displayData(Object object) {//change the method name with  meaningfully @tharindu 22/08/2018
		output(object);
	}
	
	public void setState(UI_STATE state) {
		this.state = state;
	}

	
}
