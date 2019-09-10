package web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import domain.Product;
import service.ProductService;
import service.impl.ProductServiceimpl;
import utils.WebUtils;

public class ProductServlet extends HttpServlet {
	private ProductService proService=new ProductServiceimpl();
	public void doPost(HttpServletRequest request,HttpServletResponse response)
			throws IOException,ServletException {
		 response.sendError(404);
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String cmd=WebUtils.getCmd(request);
		if(cmd.equals("/product.do/num")) {
			int t=proService.getTotalNum(null);
			response.getWriter().print(t);
		}
		else if(cmd.equals("/product.do/list")) {
			int p=Integer.parseInt(request.getParameter("page"));
			int num=Integer.parseInt(request.getParameter("num"));
			ArrayList<Product> ap=proService.getList((p-1)*num, p*num-1,null);
			for(int i=0;i<ap.size();i++) {
				ap.get(i).setShortdesc(null);
			}
			response.getWriter().print(JSON.toJSONString(ap));
		}
		else if(cmd.equals("/product.do/detail")) {
			String pid=request.getParameter("pid");
			Product pro=proService.getProduct(pid);
			if(pro!=null)
			response.getWriter().print(JSON.toJSONString(pro));
			else response.getWriter().print(WebUtils.getRes("noproduct"));
		}
		else response.sendError(404);
	}
}
