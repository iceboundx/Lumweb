package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTrans {
	public static Date trans(String str)
	{
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
		Date date=null;
		 try { 
		date=format.parse(str);
		 }catch (Exception e) {    
			 e.printStackTrace();
		 }
		 return date;
	}
	public static String trans(Date date)
	{ 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String ret="";
		 try { 
		ret=formatter.format(date);
		 }catch (Exception e) {    
			 e.printStackTrace();
		 }
		 return ret;
	}
}
