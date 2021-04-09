package ecomm.apirequestpayloads.SetShippingMethodTaxAmount;

public class ExternalTaxAmount {
	private TotalGross totalGross;
	private TaxRate taxRate;

	public TotalGross getTotalGross() {
		return totalGross;
	}

	public void setTotalGross(TotalGross totalGross) {
		this.totalGross = totalGross;
	}

	public TaxRate getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(TaxRate taxRate) {
		this.taxRate = taxRate;
	}
}
