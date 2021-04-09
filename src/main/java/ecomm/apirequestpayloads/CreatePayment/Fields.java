package ecomm.apirequestpayloads.CreatePayment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Fields {
	int amount;

	private String adyenMerchantAccount = "NirmanECOM";
	private String commercetoolsProjectKey = "breville-trial";
	private String makePaymentRequest;
	private String cartIdReference;

	public String getCartIdReference() {
		return cartIdReference;
	}

	public void setCartIdReference(String cartIdReference) {
		this.cartIdReference = cartIdReference;
	}

	public Fields(int amount) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("mmddHHmmss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));

		String paymentreferal = dtf.format(now).toString();
		this.amount = amount;
		makePaymentRequest = "{\"amount\":{\"currency\":\"USD\",\"value\":" + amount + "},\"reference\":\""
				+ paymentreferal
				+ "\",\"paymentMethod\":{\"type\":\"scheme\",\"encryptedCardNumber\":\"test_4111111111111111\",\"encryptedExpiryMonth\":\"test_03\",\"encryptedExpiryYear\":\"test_2030\",\"encryptedSecurityCode\":\"test_737\"},\"returnUrl\":\"https://your-company.com/...\",\"merchantAccount\":\"NirmanECOM\",\"shopperReference\":\"65f6c3cc-d87a-4ca9-ba6e-1759641277567\",\"shopperEmail\":\"shashi12.mishra@breville.com\",\"recurring\":{\"contract\":\"RECURRING\"}}";
	}

	public String getAdyenMerchantAccount() {
		return adyenMerchantAccount;
	}

	public void setAdyenMerchantAccount(String adyenMerchantAccount) {
		this.adyenMerchantAccount = adyenMerchantAccount;
	}

	public String getCommercetoolsProjectKey() {
		return commercetoolsProjectKey;
	}

	public void setCommercetoolsProjectKey(String commercetoolsProjectKey) {
		this.commercetoolsProjectKey = commercetoolsProjectKey;
	}

	public String getMakePaymentRequest() {
		return makePaymentRequest;
	}

	public void setMakePaymentRequest(String makePaymentRequest) {
		this.makePaymentRequest = makePaymentRequest;
	}
}
