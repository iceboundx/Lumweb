package service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import dao.ArticleDao;
import dao.impl.ArticleDaoimpl;
import domain.Article;
import service.ArticleService;

public class ArticleServiceimpl implements ArticleService {

	ArticleDao artDao=new ArticleDaoimpl();
	@Override
	public ArrayList<Article> getList(String type, int start, int end) {
		if(type==null) {
			return artDao.getList(start, end);
		}
		else return artDao.getList(start, end, type);
	}

	@Override
	public int getTotalNum(String type) {
		if(type==null) {
			return artDao.getTotalRec();
		}
		else return artDao.getTotalRec(type);
	}

	@Override
	public Article getContent(String id) {
		return artDao.getArt(id);
	}

	@Override
	public boolean addArt(Article article) {
		if(artDao.existArt(article.getId()))return false;
		artDao.addArt(article);
		return true;
	}

	@Override
	public boolean delArt(String id) {
		return artDao.delArt(id);
	}

	@Override
	public boolean changeArt(Article article) {
		if(!existID(article.getId()))return false;
		delArt(article.getId());
		addArt(article);
		return true;
	}
	
	@Override
	public String createID() {
		Date date = new Date();
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyyMMddhhmmss");
		return dateFormat.format(date);
	}
	@Override
	public boolean existID(String id) {
		if(artDao.existArt(id))return true;
		return false;
	}
}
