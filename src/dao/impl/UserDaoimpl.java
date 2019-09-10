package dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import dao.UserDao;
import domain.User;
import utils.JdbcUtils;

public class UserDaoimpl implements UserDao {

	@Override
	public User getUser(String userID) {
 		String sql = "select * from user where uid=?";
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			return (User)runner.query(sql, new BeanHandler<User>(User.class), userID);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public ArrayList<User> getAll(int start,int end) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from user limit ?,?";
			return (ArrayList<User>)runner.query(sql, new BeanListHandler<User>(User.class), start, end-start+1);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean existUser(String userID) {
		User getUsr=getUser(userID);
		if(getUsr==null)
		return false;
		else return true;
	}

	@Override
	public boolean addUser(User user) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "insert into user values(?,?,?,?,?,?)";
			Object params[] = {user.getUid(),user.getPassword(),user.getPasswordSalt(),user.getNickname(),user.getBirthday(),user.getAddress()};
			runner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return false;
	}

	@Override
	public boolean delUser(String userID) {
		if(existUser(userID)) {
			try {
				QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
				String sql = "delete from user where uid=?";
				Object params[] = {userID};
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
		String sql = "select count(*) from user";
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
