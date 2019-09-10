package dao;

import java.util.ArrayList;

import domain.Article;

public interface ArticleDao {
	public Article getArt(String ID);
	public ArrayList<Article> getList(int start,int end);
	public ArrayList<Article> getList(int start,int end,String type);
	public boolean existArt(String ID);
	public void addArt(Article article);
	public boolean delArt(String ID);
	public int getTotalRec();
	public int getTotalRec(String type);
}
