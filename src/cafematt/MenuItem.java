package cafematt;

public class MenuItem {
	public MenuItem(String itemName, double price, boolean serviceChargeRequired) {
		super();
		this.itemName = itemName;
		this.price = price;
		this.serviceChargeRequired = serviceChargeRequired;
	}
	
	public boolean isServiceChargeRequired() {
		return serviceChargeRequired;
	}
	public void setServiceChargeRequired(boolean serviceChargeRequired) {
		this.serviceChargeRequired = serviceChargeRequired;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	protected boolean serviceChargeRequired;
	protected String  itemName;
	protected double  price = 0.0;
}
