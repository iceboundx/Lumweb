package service;

import java.util.ArrayList;

import domain.Article;

public interface ArticleService {
	public ArrayList<Article> getList(String type, int start,int end);
	public int getTotalNum(String type);
	public Article getContent(String id);
	public boolean addArt(Article article);
	public Article createArt(Article article);
	public boolean delArt(String id);
	public boolean changeArt(Article art);
	public String createID();
	public boolean existID(String id);
}
