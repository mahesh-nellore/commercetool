package ecomm.apirequestpayloads.ShippingAddress;

import java.util.List;

public class ShippingAddress {
	
	private int version = 1;
    private List<Addr_Action> actions;
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public List<Addr_Action> getActions() {
		return actions;
	}
	public void setActions(List<Addr_Action> actions) {
		this.actions = actions;
	}

}
