package ecomm.apirequestpayloads.SetShippingMethodTaxAmount;

public class SetShippingMethodTaxAmountAction {

	private String action = "setShippingMethodTaxAmount";
	private ExternalTaxAmount externalTaxAmount;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public ExternalTaxAmount getExternalTaxAmount() {
		return externalTaxAmount;
	}

	public void setExternalTaxAmount(ExternalTaxAmount externalTaxAmount) {
		this.externalTaxAmount = externalTaxAmount;
	}
}
