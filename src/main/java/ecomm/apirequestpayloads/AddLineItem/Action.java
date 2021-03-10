package ecomm.apirequestpayloads.AddLineItem;

public class Action {
	
	private String action = "addLineItem";

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getVariantId() {
		return variantId;
	}

	public void setVariantId(int variantId) {
		this.variantId = variantId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public SupplyChannel getSupplyChannel() {
		return supplyChannel;
	}

	public void setSupplyChannel(SupplyChannel supplyChannel) {
		this.supplyChannel = supplyChannel;
	}

	public DistributionChannel getDistributionChannel() {
		return distributionChannel;
	}

	public void setDistributionChannel(DistributionChannel distributionChannel) {
		this.distributionChannel = distributionChannel;
	}

	private String productId = "eba6b40f-f7a4-4005-9db4-916a17b4d3a1";
	private int variantId = 1;
	private int quantity = 1;
	private SupplyChannel supplyChannel;
	private DistributionChannel distributionChannel;

}
