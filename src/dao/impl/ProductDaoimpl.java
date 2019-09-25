package dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import dao.ProductDao;
import domain.Product;
import utils.JdbcUtils;

public class ProductDaoimpl implements ProductDao {

	@Override
	public Product getProduct(String pid) {
 		String sql = "select * from product where pid=?";
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			return (Product)runner.query(sql, new BeanHandler<Product>(Product.class), pid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public ArrayList<Product> getAll(int start, int end) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from product order by state,pid limit ?,?";
			return (ArrayList<Product>)runner.query(sql, new BeanListHandler<Product>(Product.class), start, end-start+1);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public ArrayList<Product> getAll(int start,int end,String state) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from product where state=? order by pid limit ?,?";
			return (ArrayList<Product>)runner.query(sql, new BeanListHandler<Product>(Product.class),state, start, end-start+1);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public boolean existPro(String pid) {
		Product getpro=getProduct(pid);
		if(getpro==null)
		return false;
		else return true;
	}

	@Override
	public boolean addPro(Product product) {
		if(existPro(product.getPid()))return false;
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "insert into product values(?,?,?,?,?,?)";
			Object params[] = {product.getPid(),product.getName(),product.getPrice(),product.getStock(),product.getShortdesc(),product.getState()};
			runner.update(sql, params);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean delPro(String pid) {
		if(existPro(pid)) {
			try {
				QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
				String sql = "delete from product where pid=?";
				Object params[] = {pid};
				runner.update(sql,params);
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		return false;
	}

	@Override
	public int getTotalRec() {
		String sql = "select count(*) from product";
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			long totalrecord = (Long)runner.query(sql, new ScalarHandler<Long>());
			return (int)totalrecord;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public int getTotalRec(String state) {
		String sql = "select count(*) from product where state=?";
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			long totalrecord = (Long)runner.query(sql, new ScalarHandler<Long>(),state);
			return (int)totalrecord;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
