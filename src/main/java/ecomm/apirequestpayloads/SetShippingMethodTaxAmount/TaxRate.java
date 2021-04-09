package ecomm.apirequestpayloads.SetShippingMethodTaxAmount;

public class TaxRate {
	private String name = "myTaxRate";
	private double amount = 0.20;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	private String country = "US";

}
