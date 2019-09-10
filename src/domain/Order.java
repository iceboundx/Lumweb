package domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Order {
	private String oid;
	private Date orderTime;
	private String state;
	private String uid;
	private double price;
	private String address;
	private Set<Item> orderitems = new HashSet<Item>();
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Set<Item> getOrderitems() {
		return orderitems;
	}
	public void setOrderitems(Set<Item> orderitems) {
		this.orderitems = orderitems;
	}

}
