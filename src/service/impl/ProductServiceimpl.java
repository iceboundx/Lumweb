package service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dao.CartDao;
import dao.ProductDao;
import dao.impl.CartDaoimpl;
import dao.impl.ProductDaoimpl;
import domain.Cart;
import domain.CartItem;
import domain.Product;
import service.ProductService;

public class ProductServiceimpl implements ProductService {

	ProductDao proDao=new ProductDaoimpl();
	CartDao cartDao=new CartDaoimpl();
	@Override
	public ArrayList<Product> getList(int start, int end, String state) {
		if(state==null) {
			return proDao.getAll(start, end);
		}
		else return proDao.getAll(start, end, state);
	}

	@Override
	public int getTotalNum(String state) {
		if(state==null) {
			return proDao.getTotalRec();
		}
		else return proDao.getTotalRec(state);
	}

	@Override
	public Product getProduct(String pid) {
		return proDao.getProduct(pid);
	}

	@Override
	public boolean addProduct(Product product) {
		return proDao.addPro(product);
	}

	@Override
	public boolean delProduct(String pid) {
		return proDao.delPro(pid);
	}

	@Override
	public boolean changePro(Product product) {
		if(delProduct(product.getPid()))
		return addProduct(product);
		else return false;
	}

	@Override
	public Cart getCart(String uid) {
		ArrayList<CartItem> ct=cartDao.getCartItem(uid);
		Cart cart=new Cart();
		Map<String, CartItem> cartContent=new HashMap<String,CartItem>();
		for(CartItem it:ct) {
			cartContent.put(it.getPid(), it);
		}
		cart.setCartContent(cartContent);
		cart.setUid(uid);
		return cart;
	}

	@Override
	public boolean addCart(String pid, String uid) {
		Cart cart=this.getCart(uid);
		Product pro=this.getProduct(pid);
		if(pro==null)
		{
			System.out.println("null...");
				return false;
		}
		cart.add(pro);
		this.changeCart(cart);
		return true;
	}
	
	public boolean decCart(String pid, String uid) {
		Cart cart=this.getCart(uid);
		Product pro=this.getProduct(pid);
		if(pro==null)return false;
		CartItem item=cart.getCartContent().get(pro.getPid());
		item.setQuantity(item.getQuantity()-1);
		cart.getCartContent().put(item.getPid(), item);
		this.changeCart(cart);
		return true;
	}
	
	public boolean delCartItem(String pid, String uid) {
		Product pro=this.getProduct(pid);
		if(pro==null)return false;
		CartItem ct=new CartItem();
		ct.setPid(pid);
		if(cartDao.delCart(ct, uid))
		return true;
		else return false;
	}

	@Override
	public boolean clearCart(String uid) {
		Cart cart=this.getCart(uid);
		System.out.println("start clear");
		if(cart==null)return false;
		Map<String, CartItem> cartContent=cart.getCartContent();
		boolean flag=true;
		for(CartItem it:cartContent.values()) {
			if(!(cartDao.delCart(it, uid)))flag=false;
		}
		System.out.println("flag"+flag);
		return flag;
	}

	@Override
	public boolean changeCart(Cart cart) {
		if(!clearCart(cart.getUid()))return false;
		Map<String, CartItem> cartContent=cart.getCartContent();
		boolean flag=true;
		for(CartItem it:cartContent.values()) {
			if(!cartDao.addCart(it, cart.getUid()))flag=false;
		}
		return flag;
	}

}
