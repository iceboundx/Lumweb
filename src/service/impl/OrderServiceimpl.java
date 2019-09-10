package service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import dao.OrderDao;
import dao.ProductDao;
import dao.UserDao;
import dao.impl.OrderDaoimpl;
import dao.impl.ProductDaoimpl;
import dao.impl.UserDaoimpl;
import domain.Cart;
import domain.CartItem;
import domain.Item;
import domain.Order;
import domain.Product;
import domain.User;
import service.OrderService;

public class OrderServiceimpl implements OrderService {

	OrderDao orderDao=new OrderDaoimpl();
	UserDao userDao=new UserDaoimpl();
	ProductDao proDao=new ProductDaoimpl();
	@Override
	public Order generateOrder(Cart cart) {
		User user=userDao.getUser(cart.getUid());
		Map<String, CartItem> cartContent=cart.getCartContent();
		Set<Item> orderitems=new HashSet<Item>();
		Date date = new Date();
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyyMMddhhmmss");
		String oid=dateFormat.format(date)+Math.abs(user.getUid().hashCode());
		Order order=new Order();
		order.setOid(oid);
		order.setUid(cart.getUid());
		order.setAddress(user.getAddress());
		order.setOrderTime(date);
		order.setState("paying");
		double allprice=0.0;
		for(CartItem cit:cartContent.values()) {
			Item it=new Item();
			it.setOid(oid);
			it.setPid(cit.getPid());
			it.setQuantity(cit.getQuantity());
			Product pr=proDao.getProduct(cit.getPid());
			double price=(double)cit.getQuantity()*pr.getPrice();
			allprice+=price;
			it.setPrice(price);
			it.setName(pr.getName());
			orderitems.add(it);
		}
		order.setPrice(allprice);
		order.setOrderitems(orderitems);
		return order;
	}
	
	@Override
	public String makeOrder(Cart cart) {
		Order order=generateOrder(cart);
		for(Item it:order.getOrderitems()) {
			String pid=it.getPid();
			Product pro=proDao.getProduct(pid);
			if(pro.getStock()<it.getQuantity()) {
				return null;
			}
		}
		for(Item it:order.getOrderitems()) {
			String pid=it.getPid();
			Product pro=proDao.getProduct(pid);
			pro.setStock(pro.getStock()-it.getQuantity());
			proDao.delPro(pid);
			proDao.addPro(pro);
		}
		orderDao.addOrder(order);
		return order.getOid();
	}
	@Override
	public ArrayList<Order> getList(int start, int end, String uid) {
		if(uid==null) {
			return orderDao.getList(start, end);
		}
		else return orderDao.getList(start, end, uid);
	}

	@Override
	public int getListNum(String uid) {
		if(uid==null) {
			return orderDao.getTotalRec();
		}
		else return orderDao.getTotalRec(uid);
	}

	@Override
	public Order getOrder(String oid) {
		return orderDao.getOrder(oid);
	}

	@Override
	public boolean delOrder(String oid) {
		return orderDao.delOrder(oid);
	}

	@Override
	public boolean addOrder(Order order) {
		if(orderDao.existOrder(order.getOid()))return false;
		orderDao.addOrder(order);
		return true;
	}

}
