package service;

import java.util.ArrayList;

import domain.Cart;
import domain.Order;

public interface OrderService {
	public Order generateOrder(Cart cart);
	public String makeOrder(Cart cart);
	public ArrayList<Order> getList(int start,int end,String uid);
	public int getListNum(String uid);
	public Order getOrder(String oid);
	public boolean delOrder(String oid);
	public boolean addOrder(Order order);
}
