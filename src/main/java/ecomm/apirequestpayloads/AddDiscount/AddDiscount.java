package ecomm.apirequestpayloads.AddDiscount;

import java.util.List;

public class AddDiscount {
	
	private int version;
	
	private List<Discount> actions;

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public List<Discount> getActions() {
		return actions;
	}

	public void setActions(List<Discount> actions) {
		this.actions = actions;
	}

}
