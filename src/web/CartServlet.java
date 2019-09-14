package web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import domain.Cart;
import domain.CartItem;
import domain.Product;
import domain.User;
import service.OrderService;
import service.ProductService;
import service.UserService;
import service.impl.OrderServiceimpl;
import service.impl.ProductServiceimpl;
import service.impl.UserServiceimpl;
import utils.WebUtils;

public class CartServlet extends HttpServlet {
	UserService userService=new UserServiceimpl();
	ProductService proService=new ProductServiceimpl();
	OrderService ordService=new OrderServiceimpl();
	public void doPost(HttpServletRequest request,HttpServletResponse response)
			throws IOException,ServletException {
		String cmd=WebUtils.getCmd(request);
		System.out.println("23333 "+cmd);
		if(cmd.equals("/cart.do/add")) {
			HttpSession session=request.getSession();
			String uid=(String)session.getAttribute("uid");
			if(uid==null) {
				
				response.getWriter().print(WebUtils.getRes("nologin"));
				return;
			}
			String pid=request.getParameter("pid");
			System.out.println("123123123123");
			proService.addCart(pid, uid);
			response.getWriter().print(WebUtils.getRes("yes"));
		}
		else if(cmd.equals("/cart.do/dec")) {
			HttpSession session=request.getSession();
			String uid=(String)session.getAttribute("uid");
			if(uid==null) {
				
				response.getWriter().print(WebUtils.getRes("nologin"));
				return;
			}
			String pid=request.getParameter("pid");
			proService.decCart(pid, uid);
			response.getWriter().print(WebUtils.getRes("yes"));
		}
		else if(cmd.equals("/cart.do/remove")) {
			HttpSession session=request.getSession();
			String uid=(String)session.getAttribute("uid");
			if(uid==null) {
				
				response.getWriter().print(WebUtils.getRes("nologin"));
				return;
			}
			String pid=request.getParameter("pid");
			proService.delCartItem(pid, uid);
			response.getWriter().print(WebUtils.getRes("yes"));
		}
		else if(cmd.equals("/cart.do/change")) {
			HttpSession session=request.getSession();
			String uid=(String)session.getAttribute("uid");
			if(uid==null) {
				response.getWriter().print(WebUtils.getRes("nologin"));
				return;
			}
			String cartString=WebUtils.getBody(request);
			ArrayList<CartItem> cl=(ArrayList<CartItem>)JSON.parseArray(cartString,CartItem.class);
			Cart cart=new Cart();
			for(CartItem it:cl) {
				cart.getCartContent().put(it.getPid(), it);
			}
			proService.changeCart(cart);
		}
		else if(cmd.equals("/cart.do/clear")) {
			HttpSession session=request.getSession();
			String uid=(String)session.getAttribute("uid");
			if(uid==null) {
				response.getWriter().print(WebUtils.getRes("nologin"));
				return;
			}
			proService.clearCart(uid);
		}
		else if(cmd.equals("/cart.do/pay")) {
			HttpSession session=request.getSession();
			String uid=(String)session.getAttribute("uid");
			if(uid==null) {
				
				response.getWriter().print(WebUtils.getRes("nologin"));
				return;
			}
			String payString=WebUtils.getBody(request);
			ArrayList<String> payList=(ArrayList<String>)JSON.parseArray(payString,String.class);
			Cart orderCart=proService.payCart(payList, proService.getCart(uid));
			String oid=ordService.makeOrder(orderCart);
			if(oid!=null) {
				proService.payCartok(payList, proService.getCart(uid));
				JSONObject UserJson=new JSONObject();
				UserJson.put("orderid", oid);
				response.getWriter().print(UserJson.toJSONString());
			}
			else
				response.getWriter().print(WebUtils.getRes("stock error"));
			return;
		}
		else response.sendError(404);
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String cmd=WebUtils.getCmd(request);
		
		if(cmd.equals("/cart.do/")) {
			HttpSession session=request.getSession();
			String uid=(String)session.getAttribute("uid");
			if(uid==null) {
				response.getWriter().print(WebUtils.getRes("nologin"));
				return;
			}
			Cart cart=proService.getCart(uid);
			ArrayList<CartItem>cl=new ArrayList<CartItem>();
			for(CartItem it:cart.getCartContent().values()) {
				cl.add(it);
			}
			response.getWriter().print(JSON.toJSONString(cl));
		}
		else response.sendError(404);
	}
}
