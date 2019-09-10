package dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import dao.ManagerDao;
import domain.Manager;
import utils.JdbcUtils;

public class ManagerDaoimpl implements ManagerDao {

	@Override
	public Manager getMan(String ID) {
 		String sql = "select * from manager where uid=?";
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			return (Manager)runner.query(sql, new BeanHandler<Manager>(Manager.class), ID);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public ArrayList<Manager> getAll(int start, int end) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from manager limit ?,?";
			return (ArrayList<Manager>)runner.query(sql, new BeanListHandler<Manager>(Manager.class), start, end-start+1);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean existMan(String ID) {
		Manager getUsr=getMan(ID);
		if(getUsr==null)
		return false;
		else return true;
	}

	@Override
	public boolean addMan(Manager manager) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "insert into manager values(?,?,?,?,?)";
			Object params[] = {manager.getUid(),manager.getPassword(),manager.getPasswordsalt(),manager.getPermission(),manager.getName()};
			runner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return false;
	}

	@Override
	public boolean delMan(String ID) {
		if(existMan(ID)) {
			try {
				QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
				String sql = "delete from manager where uid=?";
				Object params[] = {ID};
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
		String sql = "select count(*) from manager";
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			long totalrecord = (Long)runner.query(sql, new ScalarHandler<Long>());
			return (int)totalrecord;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
