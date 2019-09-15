package dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import dao.OrderDao;
import domain.Item;
import domain.Order;
import domain.Product;
import utils.JdbcUtils;

public class OrderDaoimpl implements OrderDao {

	@Override
	public void addOrder(Order order) {
		String sql="insert into orderinfo values(?,?,?,?,?,?)";
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			Object params[] = {order.getOid(),order.getUid(),order.getAddress(),order.getPrice(),order.getState(),order.getOrderTime()};
			runner.update(sql, params);
			Set<Item>oi=order.getOrderitems();
			for(Item it:oi) {
				String sql2="insert into orderitem values(?,?,?,?)";
				Object para[]= {it.getPid(),it.getOid(),it.getPrice(),it.getQuantity()};
				runner.update(sql2,para);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public Order getOrder(String orderID) {
 		String sql = "select * from orderitem where oid=?";
 		ProductDaoimpl proDao=new ProductDaoimpl();
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			ArrayList<Item>all=(ArrayList<Item>)runner.query(sql, new BeanListHandler<Item>(Item.class), orderID);
			for(int i=0;i<all.size();i++) {
				Product nowpro=proDao.getProduct(all.get(i).getPid());
				if(nowpro==null)
				{
					System.out.println(all.get(i).getPid());
					continue;
				}
				all.get(i).setName(nowpro.getName());
			}
			String sql2="select * from orderinfo where oid=?";
			Order now=(Order)runner.query(sql2,new BeanHandler<Order>(Order.class), orderID);
			now.getOrderitems().addAll(all);
			return now;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean delOrder(String orderID) {
		if(existOrder(orderID)) {
			try {
				QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
				String sql = "delete from orderitem where oid=?";
				Object params[] = {orderID};
				runner.update(sql,params);
				sql="delete from orderinfo where oid=?";
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
	public boolean existOrder(String orderID) {
		if(getOrder(orderID)!=null)return true;
		return false;
	}
	
	@Override
	public ArrayList<Order> getList(int start,int end) {
		String sql="select * from orderinfo order by ordertime desc limit ?,? ";
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			return (ArrayList<Order>)runner.query(sql, new BeanListHandler<Order>(Order.class), start, end-start+1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public int getTotalRec() {
		String sql = "select count(*) from orderinfo";
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
	public ArrayList<Order> getList(int start,int end,String uid) {
		String sql="select * from orderinfo where uid=? order by ordertime desc limit ?,? ";
		try {
			ArrayList<Order>ret=new ArrayList<Order>();
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			ArrayList<Order>odl=(ArrayList<Order>)runner.query(sql, new BeanListHandler<Order>(Order.class), uid, start, end-start+1);
			for(int i=0;i<odl.size();i++) {
				ret.add(getOrder(odl.get(i).getOid()));
			}
			return ret;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public int getTotalRec(String uid) {
		String sql = "select count(*) from orderinfo where uid=?";
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			long totalrecord = (Long)runner.query(sql, new ScalarHandler<Long>(),uid);
			return (int)totalrecord;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
