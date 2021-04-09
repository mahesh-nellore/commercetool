package ecomm.apirequestpayloads.AddPaymentToCart;

public class Payment {
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	private String typeId = "payment";
}
