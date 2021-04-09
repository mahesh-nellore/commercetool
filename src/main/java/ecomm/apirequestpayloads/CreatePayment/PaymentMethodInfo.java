package ecomm.apirequestpayloads.CreatePayment;

public class PaymentMethodInfo {

	private String paymentInterface = "ctp-adyen-integration";

	public String getPaymentInterface() {
		return paymentInterface;
	}

	public void setPaymentInterface(String paymentInterface) {
		this.paymentInterface = paymentInterface;
	}

}
