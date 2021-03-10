package ecomm.apirequestpayloads.AddDiscount;

public class Discount {
	
	private String action = "addDiscountCode";
	private String code = "GET10%DISCOUNT";
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

}
