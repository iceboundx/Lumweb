package web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;

import domain.Article;
import domain.Manager;
import domain.Product;
import domain.User;
import service.ArticleService;
import service.ManagerService;
import service.ProductService;
import service.UserService;
import service.impl.ArticleServiceimpl;
import service.impl.ManagerServiceimpl;
import service.impl.ProductServiceimpl;
import service.impl.UserServiceimpl;
import utils.WebUtils;

public class ManagerServlet extends HttpServlet {
	
	private UserService userService=new UserServiceimpl();
	private ProductService proService=new ProductServiceimpl();
	private ManagerService manService=new ManagerServiceimpl();
	private ArticleService artService=new ArticleServiceimpl();
	private boolean isManager(HttpServletRequest request,HttpServletResponse response) 
	throws IOException,ServletException {
		HttpSession session=request.getSession();
		if(session.getAttribute("adminid")==null) {
			response.getWriter().print(WebUtils.getRes("nologin"));
			return false;
		}
		else if(session.getAttribute("permission")==null) {
			response.getWriter().print(WebUtils.getRes("nopermission"));
			return false;
		}
		return true;
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)
			throws IOException,ServletException {
		String cmd=WebUtils.getCmd(request);
		cmd=cmd.substring(6);
		System.out.println(cmd);
		if(cmd.equals("/manager.do/login")) {
			HttpSession session=request.getSession();
			if(session.getAttribute("permission")!=null) {
				response.getWriter().print(WebUtils.getRes("nologin"));
				return;
			}
			String uid=request.getParameter("uid");
			String password=request.getParameter("password");
			System.out.println(uid+" "+password);
			if(manService.login(uid, password))
			{
				session.setAttribute("adminid", uid);
				session.setAttribute("permission",manService.getMan(uid).getPermission());
				response.getWriter().print(WebUtils.getRes("yes"));
			}
			else response.getWriter().print(WebUtils.getRes("no"));
			return;
		}
		if(!isManager(request,response))return;
		if(cmd.equals("/manager.do/exit")) {
			HttpSession session=request.getSession();
			session.setAttribute("uid", null);
			session.setAttribute("permission",null);
			response.getWriter().print(WebUtils.getRes("yes"));
		}
		else if(cmd.equals("/manager.do/product/add")) {
			String req=WebUtils.getBody(request);
			Product product=(Product)JSON.parseObject(req,Product.class);
			if(proService.addProduct(product))response.getWriter().print(WebUtils.getRes("yes"));
			else response.getWriter().print(WebUtils.getRes("no"));
		}
		else if(cmd.equals("/manager.do/product/del")) {
			String pid=request.getParameter("pid");
			if(proService.delProduct(pid))response.getWriter().print(WebUtils.getRes("yes"));
			else response.getWriter().print(WebUtils.getRes("no"));
		}
		else if(cmd.equals("/manager.do/product/change")) {
			String req=WebUtils.getBody(request);
			Product product=(Product)JSON.parseObject(req,Product.class);
			if(proService.changePro(product))response.getWriter().print(WebUtils.getRes("yes"));
			else response.getWriter().print(WebUtils.getRes("no"));
		}
		else if(cmd.equals("/manager.do/article/add")) {
			String req=WebUtils.getBody(request);
			Article article=(Article)JSON.parseObject(req,Article.class);
			if(artService.addArt(article))response.getWriter().print(WebUtils.getRes("yes"));
			else response.getWriter().print(WebUtils.getRes("no"));
		}  
		else if(cmd.equals("/manager.do/article/del")) {
			String id=request.getParameter("id");
			if(artService.delArt(id))response.getWriter().print(WebUtils.getRes("yes"));
			else response.getWriter().print(WebUtils.getRes("no"));
		}
		else if(cmd.equals("/manager.do/article/change")) {
			String req=WebUtils.getBody(request);
			Article article=(Article)JSON.parseObject(req,Article.class);
			if(artService.changeArt(article))response.getWriter().print(WebUtils.getRes("yes"));
			else response.getWriter().print(WebUtils.getRes("no"));
		} else
		{
			HttpSession session=request.getSession();
			String per=(String)session.getAttribute("permission");
			if(!per.equals("1")) {
				response.getWriter().print(WebUtils.getRes("nopermission"));
				return;
			}
			if(cmd.equals("/manager.do/add")) {
				String req=WebUtils.getBody(request);
				Manager man=(Manager)JSON.parseObject(req,Manager.class);
				if(manService.addMan(man))response.getWriter().print(WebUtils.getRes("yes"));
				else response.getWriter().print(WebUtils.getRes("no"));
			}
			else if(cmd.equals("/manager.do/changepermission")) {
				int permi=Integer.parseInt(request.getParameter("permission"));
				if(permi<0||permi>5) {response.getWriter().print(WebUtils.getRes("no"));return;}
				String uid=request.getParameter("uid");
				if(manService.ChangePermission(uid, permi))response.getWriter().print(WebUtils.getRes("yes"));
				else response.getWriter().print(WebUtils.getRes("no"));
			}
			else if(cmd.equals("/manager.do/del")) {
				String uid=request.getParameter("uid");
				if(manService.delMan(uid))response.getWriter().print(WebUtils.getRes("yes"));
				else response.getWriter().print(WebUtils.getRes("no"));
			}
			else response.sendError(404);
		}
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		if(!isManager(request,response))return;
		String cmd=WebUtils.getCmd(request);
		cmd=cmd.substring(6);
		System.out.println(cmd);
		if(cmd.equals("/manager.do/user/list")) {
			int p=Integer.parseInt(request.getParameter("page"));
			int num=Integer.parseInt(request.getParameter("num"));
			ArrayList<User>ul=userService.GetAll((p-1)*num, p*num-1);
			for(User user:ul) {
				user.setPassword(null);
				user.setPasswordSalt(null);
			}
			response.getWriter().print(JSON.toJSONString(ul));
		}
		else if(cmd.equals("/manager.do/islogin")) {
			response.getWriter().print(WebUtils.getRes("yes"));
		}
		else if(cmd.equals("/manager.do/")) {
			String uid=request.getParameter("uid");
			HttpSession session=request.getSession();
			if(uid==null)uid=(String)session.getAttribute("adminid");
			Manager man=manService.getMan(uid);
			man.setPassword(null);
			man.setPasswordsalt(null);
			response.getWriter().print(JSON.toJSONString(man));
		}
		else if(cmd.equals("/manager.do/user/num")) {
			response.getWriter().print(WebUtils.getRes(userService.getTotalNum()));
		}
		else if(cmd.equals("/manager.do/product/num")) {
			response.getWriter().print(WebUtils.getRes(proService.getTotalNum(null)));
		}
		else if(cmd.equals("/manager.do/product/list")) {
			int p=Integer.parseInt(request.getParameter("page"));
			int num=Integer.parseInt(request.getParameter("num"));
			ArrayList<Product>ul=proService.getList((p-1)*num, p*num-1,null);
			response.getWriter().print(JSON.toJSONString(ul));
		}
		else if(cmd.equals("/manager.do/product/detail")) {
			String pid=request.getParameter("pid");
			Product pro=proService.getProduct(pid);
			response.getWriter().print(JSON.toJSONString(pro));
		}
		else if(cmd.equals("/manager.do/num")) {
			response.getWriter().print(WebUtils.getRes(manService.getTotalNum()));
		}
		else if(cmd.equals("/manager.do/list")) {
			int p=Integer.parseInt(request.getParameter("page"));
			int num=Integer.parseInt(request.getParameter("num"));
			ArrayList<Manager>ml=manService.getList((p-1)*num, p*num-1);
			for(Manager man:ml) {
				man.setPassword(null);
				man.setPasswordsalt(null);
			}
			response.getWriter().print(JSON.toJSONString(ml));
		}
		else response.sendError(404);
	}
}
