package utils;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {
	
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	public static DataSource getDataSource() {
		return dataSource;
	}
	public static Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
			}
	}
	public static void release(Connection con, PreparedStatement par, ResultSet re) {
		if (re != null) {
			try {
				re.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (par != null) {
			try {
				par.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
