package ecomm.apirequestpayloads.AddPaymentToCart;

public class AddPaymentToCartAction {
	private String action = "addPayment";
	private Payment payment;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
}
