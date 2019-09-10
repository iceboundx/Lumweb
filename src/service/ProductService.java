package service;

import java.util.ArrayList;

import domain.Cart;
import domain.CartItem;
import domain.Product;

public interface ProductService {
	public ArrayList<Product> getList(int start,int end,String state);
	public int getTotalNum(String state);
	public Product getProduct(String pid);
	public boolean addProduct(Product product);
	public boolean delProduct(String pid);
	public boolean changePro(Product product);
	public Cart getCart(String uid);
	public boolean addCart(String pid,String uid);
	public boolean clearCart(String uid);
	public boolean changeCart(Cart cart);
	public boolean decCart(String pid, String uid);
	public boolean delCartItem(String pid, String uid);
}
