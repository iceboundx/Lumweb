package web;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import domain.Manager;
import domain.User;
import service.ManagerService;
import service.UserService;
import service.impl.ManagerServiceimpl;
import service.impl.UserServiceimpl;
import utils.DateTrans;
import utils.JdbcUtils;
import utils.WebUtils;

public class UserServlet extends HttpServlet {
	UserService userService=new UserServiceimpl();
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)
			throws IOException,ServletException {
		String cmd=WebUtils.getCmd(request);
        System.out.println(cmd);
		if(cmd.equals("/user.do/login")) {
			String res="";
			HttpSession session=request.getSession();
			if(session.getAttribute("uid")!=null) {
				response.getWriter().print(WebUtils.getRes("already"));
				return;   
			}
			if(userService.UserLogin(request.getParameter("uid"), request.getParameter("password")) ) {
				res="yes";
				session.setAttribute("uid", request.getParameter("uid"));
			}
			else res="no";
			System.out.println(res);
			response.getWriter().print(WebUtils.getRes(res));
		}
		else if(cmd.equals("/user.do/reg")) {
			System.out.println("reg");
			HttpSession session=request.getSession();
			if(session.getAttribute("uid")!=null) {
				response.getWriter().print(WebUtils.getRes("alreadylogin"));
				return;
			}
			User user;
			String userString=WebUtils.getBody(request);
			System.out.println(userString);
			//vaild
			user=(User)JSON.parseObject(userString,User.class);
			if(userService.UserReg(user))response.getWriter().print(WebUtils.getRes("yes"));
			else response.getWriter().print(WebUtils.getRes("no"));
		}
		else if(cmd.equals("/user.do/exit")) {
			HttpSession session=request.getSession();
			if(session.getAttribute("uid")==null) {
				response.getWriter().print(WebUtils.getRes("no"));
			}
			else {
				session.invalidate();
				response.getWriter().print(WebUtils.getRes("yes"));
			}
		}
		else if(cmd.equals("/user.do/update")) {
			HttpSession session=request.getSession();
			String uid=(String)session.getAttribute("uid");
			if(uid==null) {
				response.getWriter().print(WebUtils.getRes("nologin"));
			}
			else {
				String req=WebUtils.getBody(request);
				User user=(User)JSON.parseObject(req,User.class);
				if(user.getUid()!=null) {
					response.getWriter().print(WebUtils.getRes("nouser"));
				}
				else {
					user.setUid(uid);
					if(userService.ChangeInfo(user))response.getWriter().print(WebUtils.getRes("yes"));
					else response.getWriter().print(WebUtils.getRes("no"));
				}    
			}
		}
		else if(cmd.equals("/user.do/changepass")) {
			HttpSession session=request.getSession();
			String uid=(String)session.getAttribute("uid");
			if(uid==null) {
				response.getWriter().print(WebUtils.getRes("nologin"));
			}
			else {
				String oldPass=request.getParameter("oldpassword");
				String newPass=request.getParameter("newpassword");
				System.out.println(oldPass+" "+newPass);
				if(userService.ChangePass(uid, oldPass, newPass)) {
					response.getWriter().print(WebUtils.getRes("yes"));
				}
				else response.getWriter().print(WebUtils.getRes("no"));
			}
		}
		else response.sendError(404);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String cmd=WebUtils.getCmd(request);
		if(cmd.equals("/user.do/")) {
			HttpSession session=request.getSession();
			if(session.getAttribute("uid")==null) {
				response.getWriter().print(WebUtils.getRes("no"));
			}
			else {
				String uid=(String)session.getAttribute("uid");
				User user=userService.getUser(uid);
				user.setPassword(null);
				user.setPasswordSalt(null);
				response.getWriter().print(JSON.toJSONString(user));
			}

		}
		else if(cmd.equals("/user.do/islogin")) {
			HttpSession session=request.getSession();
			if(session.getAttribute("uid")==null) {
				response.getWriter().print(WebUtils.getRes("no"));
			}
			else response.getWriter().print(WebUtils.getRes("yes"));
		}    
		
		else if(cmd.equals("/user.do/exist")) {
			if(userService.existUser(request.getParameter("uid")))response.getWriter().print(WebUtils.getRes("yes"));
			else response.getWriter().print(WebUtils.getRes("no"));
		}
		else response.sendError(404);
	}
}
