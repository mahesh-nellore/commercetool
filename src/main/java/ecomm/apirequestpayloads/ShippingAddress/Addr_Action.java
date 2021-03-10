package ecomm.apirequestpayloads.ShippingAddress;

public class Addr_Action {

	private String action = "setShippingAddress";
    public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	private Address address;
}
