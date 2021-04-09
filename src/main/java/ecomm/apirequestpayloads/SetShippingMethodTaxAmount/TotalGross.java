package ecomm.apirequestpayloads.SetShippingMethodTaxAmount;

public class TotalGross {
	private int centAmount = 2000000;

	public int getCentAmount() {
		return centAmount;
	}

	public void setCentAmount(int centAmount) {
		this.centAmount = centAmount;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	private String currencyCode = "USD";
}
