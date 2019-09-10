package dao;

import java.util.ArrayList;

import domain.Order;

public interface OrderDao {
	public void addOrder(Order order);
	public Order getOrder(String orderID);
	public boolean delOrder(String orderID);
	public boolean existOrder(String orderID);
	public ArrayList<Order> getList(int start,int end);
	public ArrayList<Order> getList(int start,int end,String uid);
	public int getTotalRec();
	public int getTotalRec(String uid);
}
