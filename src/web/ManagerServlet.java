package web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import domain.Article;
import domain.Item;
import domain.Manager;
import domain.Order;
import domain.Product;
import domain.User;
import service.ArticleService;
import service.ManagerService;
import service.OrderService;
import service.ProductService;
import service.UserService;
import service.impl.ArticleServiceimpl;
import service.impl.ManagerServiceimpl;
import service.impl.OrderServiceimpl;
import service.impl.ProductServiceimpl;
import service.impl.UserServiceimpl;
import utils.WebUtils;

public class ManagerServlet extends HttpServlet {
	
	private UserService userService=new UserServiceimpl();
	private ProductService proService=new ProductServiceimpl();
	private ManagerService manService=new ManagerServiceimpl();
	private ArticleService artService=new ArticleServiceimpl();
	private OrderService ordService=new OrderServiceimpl();
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
		}
		else if(cmd.equals("/manager.do/user/update")) {
			System.out.println("qqqqq");
			String req=WebUtils.getBody(request);
			User user=(User)JSON.parseObject(req,User.class);
			if(user.getUid()==null)response.getWriter().print(WebUtils.getRes("no"));
			if(user.getPassword().equals("")) {
				System.out.println("nochangepass");
				if(userService.ChangeInfo(user))response.getWriter().print(WebUtils.getRes("yes"));
				else response.getWriter().print(WebUtils.getRes("no")); 
			}
			else {
				System.out.println(user.getPassword());
				String newPass=user.getPassword();
				if(!userService.ChangeInfo(user))response.getWriter().print(WebUtils.getRes("no")); 
				System.out.println("ok");
				if(userService.manChangePass(user.getUid(), newPass)) {
					response.getWriter().print(WebUtils.getRes("yes"));
				}
				else response.getWriter().print(WebUtils.getRes("no"));
			}
		}
		else if(cmd.equals("/manager.do/user/add")) {
			String req=WebUtils.getBody(request);
			User user=(User)JSON.parseObject(req,User.class);
			if(userService.UserReg(user))response.getWriter().print(WebUtils.getRes("yes"));
			else response.getWriter().print(WebUtils.getRes("no"));
		}
		else if(cmd.equals("/manager.do/user/del")) {
			String id=request.getParameter("uid");
			if(userService.delUser(id))response.getWriter().print(WebUtils.getRes("yes"));
			else response.getWriter().print(WebUtils.getRes("no"));
		}
		else if(cmd.equals("/manager.do/upload")) {
			System.out.println("upload");
	        DiskFileItemFactory factory=new DiskFileItemFactory();
	        //factory.setRepository(new File("‪D:"));
	        ServletFileUpload upload=new ServletFileUpload(factory);
	        upload.setSizeMax(20*1024*1024);
	        upload.setFileSizeMax(20*1024*1024);
	        upload.setHeaderEncoding("UTF-8");
	        try {
	            List<FileItem> itemList=upload.parseRequest(request);
	            for(FileItem item:itemList){
	                if(item.isFormField()){
	                    String name=item.getFieldName();
	                    String value=item.getString("UTF-8");
	                    System.out.println("name="+name+"  value="+value);
	                }else{
	                    String fileName=item.getName();
	                    System.out.println(fileName);
	                    String namede=item.getFieldName();
	                    System.out.println(namede);
	                    
	                    InputStream is=item.getInputStream();
	                    FileOutputStream fos=new FileOutputStream("d:/file/"+fileName);
	                    byte[] buff=new byte[1024*1024];
	                    int len=0;
	                    while((len=is.read(buff))>0){
	                        fos.write(buff);
	                    }
	                    is.close();
	                    fos.close();
	                }
	            }
	            response.getWriter().print(WebUtils.getRes("yes"));
	            return;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        response.getWriter().print(WebUtils.getRes("error"));
	        return;
		}
		else
		{
			HttpSession session=request.getSession();
			String per=session.getAttribute("permission").toString();
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
			else if(cmd.equals("/manager.do/update")) {
				String req=WebUtils.getBody(request);
				System.out.println("xxxxx");
				Manager man=(Manager)JSON.parseObject(req,Manager.class);
				System.out.println("xxxxx");
				String uid=man.getUid();
				if(!manService.delMan(uid))response.getWriter().print(WebUtils.getRes("no"));
				System.out.println("xxxxx");
				if(manService.addMan(man))response.getWriter().print(WebUtils.getRes("yes"));
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
		else if(cmd.equals("/manager.do/user/")) {
			String uid=request.getParameter("uid");
			if(uid==null) {
				response.getWriter().print(WebUtils.getRes("no"));
			}
			else {
				User user=userService.getUser(uid);
				user.setPassword(null);
				user.setPasswordSalt(null);
				response.getWriter().print(JSON.toJSONString(user));
			}

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
		else if(cmd.equals("/manager.do/order/num")) {
			response.getWriter().print(WebUtils.getRes(ordService.getListNum(null)));
		}
		else if(cmd.equals("/manager.do/order/list")) {
			int p=Integer.parseInt(request.getParameter("page"));
			int num=Integer.parseInt(request.getParameter("num"));
			ArrayList<Order> olist=ordService.getList((p-1)*num, p*num-1,null);
			for(int i=0;i<olist.size();i++) {
				olist.get(i).setOrderitems(null);
			}
			JSONObject.DEFFAULT_DATE_FORMAT="yyyy-MM-dd HH:mm:ss";
			response.getWriter().print(JSON.toJSONString(olist,SerializerFeature.WriteDateUseDateFormat));
		}
		else if(cmd.equals("/manager.do/order/detail")) {
			String oid=request.getParameter("oid");
			Order ord=ordService.getOrder(oid);
			if(ord==null){
				response.getWriter().print(WebUtils.getRes("no"));
				return;
			}
			for(Item it:ord.getOrderitems()) {
				System.out.println(it.getPrice());
			}
			JSONObject.DEFFAULT_DATE_FORMAT="yyyy-MM-dd HH:mm:ss";
			response.getWriter().print(JSON.toJSONString(ord,SerializerFeature.WriteDateUseDateFormat));
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
		else if(cmd.equals("/manager.do/article/num")) {
			String type=request.getParameter("type");
			response.getWriter().print(WebUtils.getRes(artService.getTotalNum(type)));
		}
		else if(cmd.equals("/manager.do/article/list")) {
			String type=request.getParameter("type");
			int p=Integer.parseInt(request.getParameter("page"));
			int num=Integer.parseInt(request.getParameter("num"));
			ArrayList<Article> al=artService.getList(type, (p-1)*num, p*num-1);
			response.getWriter().print(JSON.toJSONString(al));
		}
		else if(cmd.equals("/manager.do/article/detail")) {
			String id=request.getParameter("id");
			Article ar=artService.getContent(id);
			if(ar==null)response.getWriter().print(WebUtils.getRes("no"));
			response.getWriter().print(JSON.toJSONString(ar));
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
		else if(cmd.equals("/manager.do/file")) {
			ArrayList<String>filelist=new ArrayList();
			String path = "d:/file/";		//要遍历的路径
			File file = new File(path);		//获取其file对象
			File[] fs = file.listFiles();	//遍历path下的文件和目录，放在File数组中
			for(File f:fs){					//遍历File[]数组
				if(!f.isDirectory())		//若非目录(即文件)，则打印
					filelist.add(f.getName());
			}
			response.getWriter().print(JSON.toJSONString(filelist));
		}
		else response.sendError(404);
	}
}
