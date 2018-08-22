public class PayFineControl {
		
	/**
	* @author  Chathuranga Muthukumarana.
	* @date   2018-08-19
	*/
	
	//Change variable name ui to payfineui.
	private PayFineUI payfineui ;
	//Remove variable space name CONTROL_STATE to CONTROLSTATE.
	private enum CONTROLSTATE { INITIALISED, READY, PAYING, COMPLETED, CANCELLED };
	//Remove variable space name CONTROL_STATE to CONTROLSTATE.
	private CONTROLSTATE state;
	
	private library library;
	private member member;;


	public PayFineControl() {
		this.library = library.INSTANCE();
		//Remove variable space name CONTROL_STATE to CONTROLSTATE.
		state = CONTROLSTATE.INITIALISED;
	}
	
	
	public void setUI(PayFineUI ui) {
		//Remove variable space name CONTROL_STATE to CONTROLSTATE.
		if (!state.equals(CONTROLSTATE.INITIALISED)) {
			throw new RuntimeException("PayFineControl: cannot call setUI except in INITIALISED state");
		}
		//Change variable name ui to payfineui.	
		this.payfineui = ui;
		ui.setState(PayFineUI.UI_STATE.READY);
		//Remove variable space name CONTROL_STATE to CONTROLSTATE.
		state = CONTROLSTATE.READY;		
	}


	public void cardSwiped(int memberId) {
		//Remove variable space name CONTROL_STATE to CONTROLSTATE.
		if (!state.equals(CONTROLSTATE.READY)) {
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
		//Remove variable space name CONTROL_STATE to CONTROLSTATE.
		state = CONTROLSTATE.PAYING;
	}
	
	
	public void cancel() {
		//Change variable name ui to payfineui
		payfineui.setState(PayFineUI.UI_STATE.CANCELLED);
		//Remove variable space name CONTROL_STATE to CONTROLSTATE.
		state = CONTROLSTATE.CANCELLED;
	}


	public double payFine(double amount) {
		//Remove variable space name CONTROL_STATE to CONTROLSTATE.
		if (!state.equals(CONTROLSTATE.PAYING)) {
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
		//Remove variable space name CONTROL_STATE to CONTROLSTATE.
		state = CONTROLSTATE.COMPLETED;
		return change;
	}
	


}
