package dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import dao.ArticleDao;
import domain.Article;
import utils.JdbcUtils;

public class ArticleDaoimpl implements ArticleDao {

	@Override
	public Article getArt(String ID) {
 		String sql = "select * from article where id=?";
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			return (Article)runner.query(sql, new BeanHandler<Article>(Article.class), ID);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public ArrayList<Article> getList(int start, int end) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select id,title,createTime from article order by createTime desc limit ?,?";
			return (ArrayList<Article>)runner.query(sql, new BeanListHandler<Article>(Article.class), start, end-start+1);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public ArrayList<Article> getList(int start, int end, String type) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select id,title,createTime from article where type=? order by createTime desc limit ?,?";
			return (ArrayList<Article>)runner.query(sql, new BeanListHandler<Article>(Article.class), type, start, end-start+1);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean existArt(String ID) {
		Article getart=getArt(ID);
		if(getart==null)
		return false;
		else return true;
	}

	@Override
	public void addArt(Article article) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "insert into article values(?,?,?,?,?,?)";
			Object params[] = {article.getId(),article.getTitle(),article.getCreateTime(),article.getAuthor(),article.getType(),article.getContent()};
			runner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean delArt(String ID) {
		if(existArt(ID)) {
			try {
				QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
				String sql = "delete from article where id=?";
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
		String sql = "select count(*) from article";
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
	public int getTotalRec(String type) {
		String sql = "select count(*) from article where type=?";
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			long totalrecord = (Long)runner.query(sql, new ScalarHandler<Long>(), type);
			return (int)totalrecord;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
