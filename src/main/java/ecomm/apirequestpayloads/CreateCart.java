package ecomm.apirequestpayloads;

public class CreateCart {
	
	private String currency = "USD";
	private String taxMode = "ExternalAmount";

	public String getTaxMode() {
		return taxMode;
	}

	public void setTaxMode(String taxMode) {
		this.taxMode = taxMode;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
