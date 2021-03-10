package ecomm.apirequestpayloads.AddLineItem;

import java.util.List;

public class AddLineItem {

	private int version = 1;

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

	private List<Action> actions;

}




