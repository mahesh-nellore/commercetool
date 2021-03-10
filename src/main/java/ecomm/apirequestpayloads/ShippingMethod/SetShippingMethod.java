package ecomm.apirequestpayloads.ShippingMethod;

import java.util.List;

public class SetShippingMethod {
	
	private int version = 15;
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public List<ShippingMethodAction> getActions() {
		return actions;
	}
	public void setActions(List<ShippingMethodAction> actions) {
		this.actions = actions;
	}
	private List<ShippingMethodAction> actions;

}
