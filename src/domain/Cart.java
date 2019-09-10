package domain;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	private Map<String, CartItem> cartContent = new HashMap<String,CartItem>();
	private String uid;
	
	public void add(Product product){
		CartItem item = cartContent.get(product.getPid());
		if(item ==null){
			item = new CartItem();
			item.setPid(product.getPid());
			item.setName(product.getName());
			item.setPrice(product.getPrice());
			item.setQuantity(1);
			cartContent.put(product.getPid(), item);
		}else{
			item.setQuantity(item.getQuantity() + 1);
		}
	}

	public Map<String, CartItem> getCartContent() {
		return cartContent;
	}

	public void setCartContent(Map<String, CartItem> cartContent) {
		this.cartContent = cartContent;
	}


	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
}
