package ecomm.apirequestpayloads.AddPaymentToCart;

import java.util.List;

public class AddpaymentToCart {
	private int version;
	private List<AddPaymentToCartAction> actions;

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public List<AddPaymentToCartAction> getActions() {
		return actions;
	}

	public void setActions(List<AddPaymentToCartAction> actions) {
		this.actions = actions;
	}
}
