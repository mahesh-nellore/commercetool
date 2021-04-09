package ecomm.apirequestpayloads.CreatePayment;

public class CreatePayment {
	
	 private AmountPlanned amountPlanned;
	 private PaymentMethodInfo paymentMethodInfo;
	 private Custom custom;
	public AmountPlanned getAmountPlanned() {
		return amountPlanned;
	}
	public void setAmountPlanned(AmountPlanned amountPlanned) {
		this.amountPlanned = amountPlanned;
	}
	public PaymentMethodInfo getPaymentMethodInfo() {
		return paymentMethodInfo;
	}
	public void setPaymentMethodInfo(PaymentMethodInfo paymentMethodInfo) {
		this.paymentMethodInfo = paymentMethodInfo;
	}
	public Custom getCustom() {
		return custom;
	}
	public void setCustom(Custom custom) {
		this.custom = custom;
	}

}
