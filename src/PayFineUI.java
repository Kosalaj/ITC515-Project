
import java.util.Scanner; 


public class PayFineUI {

	/**
	* @author  Chathuranga Muthukumarana.
	* @date   2018-08-18 
	*/

	public static enum UISTATE { INITIALISED, READY, PAYING, COMPLETED, CANCELLED };

	private PayFineControl control;
	private Scanner input;
	private UISTATE state;

	
	public PayFineUI(PayFineControl control) {
		this.control = control;
		input = new Scanner(System.in);
		state = UISTATE.INITIALISED;
		control.setUI(this);
	}
	
	
	public void setState(UISTATE state) {
		this.state = state;
	}


	public void run() {
		output("Pay Fine Use Case UI\n");
		
		while (true) {
			
			switch (state) {
			
			case READY:
				//change memstr to memberStr 
				String memberStr = input("Swipe member card (press <enter> to cancel): ");
				//change memstr to memberStr 
				if (memberStr.length() == 0) {
					control.cancel();
					break;
				}
				try {
					int memberId = Integer.valueOf(memStr).intValue();
					control.cardSwiped(memberId);
				}
				catch (NumberFormatException e) {
					output("Invalid memberId");
				}
				break;
				
			case PAYING:
				double amount = 0;
				//change amtStr to amountStr 
				String amountStr = input("Enter amount (<Enter> cancels) : ");
				//change amtStr to amountStr 
				if (amountStr.length() == 0) {
					control.cancel();
					break;
				}
				try {
					//change amtStr to amountStr 
					amount = Double.valueOf(amountStr).doubleValue();
				}
				catch (NumberFormatException e) {}
				if (amount <= 0) {
					output("Amount must be positive");
					break;
				}
				control.payFine(amount);
				break;
								
			case CANCELLED:
				output("Pay Fine process cancelled");
				return;
			
			case COMPLETED:
				output("Pay Fine process complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("FixBookUI : unhandled state :" + state);			
			
			}		
		}		
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}	
			

	public void display(Object object) {
		output(object);
	}


}
