package ecomm.apirequestpayloads.SetShippingMethodTaxAmount;

import java.util.List;

public class ShippingMethodTaxAmount {
	private int version;
	private List<SetShippingMethodTaxAmountAction> actions;

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public List<SetShippingMethodTaxAmountAction> getActions() {
		return actions;
	}

	public void setActions(List<SetShippingMethodTaxAmountAction> actions) {
		this.actions = actions;
	}
}
