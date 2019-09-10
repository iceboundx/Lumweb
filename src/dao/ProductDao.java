package dao;

import java.util.ArrayList;

import domain.Product;

public interface ProductDao {
	public Product getProduct(String pid);
	public ArrayList<Product> getAll(int start,int end);
	public ArrayList<Product> getAll(int start,int end,String state);
	public boolean existPro(String pid);
	public boolean addPro(Product product);
	public boolean delPro(String pid);
	public int getTotalRec();
	public int getTotalRec(String state);
}
