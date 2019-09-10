package domain;

public class Product {
	private String pid;
	private String name;
	private double price;
	private int stock;
	private String shortdesc;
	private String state;
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getShortdesc() {
		return shortdesc;
	}
	public void setShortdesc(String shortdesc) {
		this.shortdesc = shortdesc;
	}

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Product [pid=" + pid + ", name=" + name + ", price=" + price + ", stock=" + stock + ", shortDesc="
				+ shortdesc + ", state=" + state + "]";
	}
}
