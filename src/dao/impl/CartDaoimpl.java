package dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import dao.CartDao;
import domain.Article;
import domain.Cart;
import domain.CartItem;
import domain.Item;
import domain.Product;
import domain.User;
import utils.JdbcUtils;

public class CartDaoimpl implements CartDao {

	@Override
	public ArrayList<CartItem> getCartItem(String uid) {
 		String sql = "select pid,quantity from cart where uid=?";
 		ProductDaoimpl proDao=new ProductDaoimpl();
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			ArrayList<CartItem>all=(ArrayList<CartItem>)runner.query(sql, new BeanListHandler<CartItem>(CartItem.class), uid);
			for(int i=0;i<all.size();i++) {
				Product nowpro=proDao.getProduct(all.get(i).getPid());
				all.get(i).setName(nowpro.getName());
				all.get(i).setPrice(nowpro.getPrice());
			}
			return all;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean existItem(CartItem item,String uid) {
 		String sql = "select pid,quantity from cart where uid=? and pid=? ";
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			CartItem it=(CartItem)runner.query(sql, new BeanHandler<CartItem>(CartItem.class), uid, item.getPid());
			if(it==null)return false;
			else return true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public boolean addCart(CartItem item,String uid) {
		if(existItem(item,uid)) {
			return false;
		}
		else {
			try {
				QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
				String sql = "insert into cart values(?,?,?)";
				Object params[] = {item.getPid(),uid, item.getQuantity()};
				runner.update(sql, params);
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}
	@Override
	public boolean delCart(CartItem item,String uid) {
		if(existItem(item,uid)) {
			try {
				QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
				String sql = "delete from cart where pid=? and uid=?";
				Object params[] = {item.getPid(),uid};
				runner.update(sql,params);
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		else return false;
	}

}
