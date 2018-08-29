public class PayFineControl {
		
	/**
	* @author  Chathuranga Muthukumarana.
	* @date   2018-08-19
	*/
	
	//Change variable name ui to payfineui.
	private PayFineUI payfineui ;
        //remove the space in CONTROLSTATE 
	private enum CONTROLSTATE { INITIALISED, READY, PAYING, COMPLETED, CANCELLED };
	//remove the space in CONTROLSTATE 
	private CONTROLSTATE state;
	
	private library library;
	private member member;;


	public PayFineControl() {
		this.library = library.INSTANCE();
		state = CONTROL_STATE.INITIALISED;
	}
	
	
	public void setUI(PayFineUI ui) {
		if (!state.equals(CONTROL_STATE.INITIALISED)) {
			throw new RuntimeException("PayFineControl: cannot call setUI except in INITIALISED state");
		}
		//Change variable name ui to payfineui.	
		this.payfineui = ui;
		ui.setState(PayFineUI.UI_STATE.READY);
		state = CONTROL_STATE.READY;		
	}


	public void cardSwiped(int memberId) {
		if (!state.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("PayFineControl: cannot call cardSwiped except in READY state");
		}	
		member = library.getMember(memberId);
		
		if (member == null) {
			//Change variable name ui to payfineui
			payfineui.display("Invalid Member Id");
			return;
		}
		//Change variable name ui to payfineui
		payfineui.display(member.toString());
		//Change variable name ui to payfineui
		payfineui.setState(PayFineUI.UI_STATE.PAYING);
		state = CONTROL_STATE.PAYING;
	}
	
	
	public void cancel() {
		//Change variable name ui to payfineui
		payfineui.setState(PayFineUI.UI_STATE.CANCELLED);
		state = CONTROL_STATE.CANCELLED;
	}


	public double payFine(double amount) {
		if (!state.equals(CONTROL_STATE.PAYING)) {
			throw new RuntimeException("PayFineControl: cannot call payFine except in PAYING state");
		}	
		double change = member.payFine(amount);
		if (change > 0) {
			//Change variable name ui to payfineui
			payfineui.display(String.format("Change: $%.2f", change));
		}
		//Change variable name ui to payfineui
		payfineui.display(member.toString());
		//Change variable name ui to payfineui
		payfineui.setState(PayFineUI.UI_STATE.COMPLETED);
		state = CONTROL_STATE.COMPLETED;
		return change;
	}
	


}
