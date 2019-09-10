package utils;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

public class WebUtils {
	static public String getCmd(HttpServletRequest request) {
		System.out.println(request.getRequestURI());
		return request.getRequestURI().substring(request.getContextPath().length());
	}
	
	static public String getBody(HttpServletRequest request) {
		BufferedReader br;
		String str, wholeStr = "";
		try {
			br = request.getReader();
			while((str = br.readLine()) != null){
				wholeStr += str;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wholeStr;
	}
	
	static public String getRes(String res) {
		JSONObject UserJson=new JSONObject();
		UserJson.put("result", res);
		return UserJson.toJSONString();
	}
	static public String getRes(int res) {
		JSONObject UserJson=new JSONObject();
		UserJson.put("result", ""+res);
		return UserJson.toJSONString();
	}
}
