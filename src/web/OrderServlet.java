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
import com.alibaba.fastjson.serializer.SerializerFeature;

import domain.Item;
import domain.Order;
import service.OrderService;
import service.impl.OrderServiceimpl;
import utils.WebUtils;

public class OrderServlet extends HttpServlet {
	private OrderService ordService=new OrderServiceimpl();
	public void  doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String cmd=WebUtils.getCmd(request);
        System.out.println(cmd);
		if(cmd.equals("/order.do/list")) {
			HttpSession session=request.getSession();
			String uid=(String)session.getAttribute("uid");
			if(uid==null) {
				response.getWriter().print(WebUtils.getRes("nologin"));
				return;
			}
			int p=Integer.parseInt(request.getParameter("page"));
			int num=Integer.parseInt(request.getParameter("num"));
			ArrayList<Order> olist=ordService.getList((p-1)*num, p*num-1,uid);
			JSONObject.DEFFAULT_DATE_FORMAT="yyyy-MM-dd HH:mm:ss";
			response.getWriter().print(JSON.toJSONString(olist,SerializerFeature.WriteDateUseDateFormat));
		}
		else if(cmd.equals("/order.do/num")) {
			HttpSession session=request.getSession();
			String uid=(String)session.getAttribute("uid");
			if(uid==null) {
				response.getWriter().print(WebUtils.getRes("nologin"));
				return;
			}
			response.getWriter().print(WebUtils.getRes(ordService.getListNum(uid)));
		}
	}

}
