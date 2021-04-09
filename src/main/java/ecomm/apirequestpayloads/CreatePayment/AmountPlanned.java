package ecomm.apirequestpayloads.CreatePayment;

public class AmountPlanned {

	private String currencyCode = "USD";
	private int centAmount = 100;

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public int getCentAmount() {
		return centAmount;
	}

	public void setCentAmount(int centAmount) {
		this.centAmount = centAmount;
	}
}
