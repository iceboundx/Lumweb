  package dao;

import java.util.ArrayList;

import domain.Cart;
import domain.CartItem;

public interface CartDao {
	public ArrayList<CartItem> getCartItem(String uid);
	public boolean existItem(CartItem item,String uid);
	public boolean addCart(CartItem item,String uid);
	public boolean delCart(CartItem item,String uid);
}
