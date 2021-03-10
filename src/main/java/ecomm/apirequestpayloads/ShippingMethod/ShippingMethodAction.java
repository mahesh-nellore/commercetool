package ecomm.apirequestpayloads.ShippingMethod;

public class ShippingMethodAction {
	
	private String action = "setShippingMethod";
	private ShippingMethod shippingMethod;
	
    public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public ShippingMethod getShippingMethod() {
		return shippingMethod;
	}
	public void setShippingMethod(ShippingMethod shippingMethod) {
		this.shippingMethod = shippingMethod;
	}
	

}
