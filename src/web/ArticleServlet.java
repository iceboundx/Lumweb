package web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import domain.Article;
import domain.Product;
import service.ArticleService;
import service.impl.ArticleServiceimpl;
import utils.WebUtils;

public class ArticleServlet extends HttpServlet {
	private ArticleService artService=new ArticleServiceimpl();
	public void doPost(HttpServletRequest request,HttpServletResponse response)
			throws IOException,ServletException {
		response.sendError(404);
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String cmd=WebUtils.getCmd(request);
		if(cmd.equals("/article.do/num")) {
			String type=request.getParameter("type");
			response.getWriter().print(WebUtils.getRes(artService.getTotalNum(type)));
		}
		else if(cmd.equals("/article.do/list")) {
			String type=request.getParameter("type");
			int p=Integer.parseInt(request.getParameter("page"));
			int num=Integer.parseInt(request.getParameter("num"));
			ArrayList<Article> al=artService.getList(type, (p-1)*num, p*num-1);
			response.getWriter().print(JSON.toJSONString(al));
		}
		else if(cmd.equals("/article.do/detail")) {
			String id=request.getParameter("id");
			Article ar=artService.getContent(id);
			if(ar==null)response.getWriter().print(WebUtils.getRes("no"));
			response.getWriter().print(JSON.toJSONString(ar));
		}
		else response.sendError(404);
	}
}
