package com.server.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

public class RequestHelper {
	   HttpServletRequest request;
	   public RequestHelper(HttpServletRequest request){
	      this.request = request;
	   }
	   public String[] getParameterStringValues(String key){
	      
	      String[] val = request.getParameterValues(key);
	      if(val == null){
	         return new String[0];
	      }
	      else if(!request.getServerName().endsWith("postech.ac.kr") && !request.getServerName().endsWith("141.223.3.17") && !request.getServerName().startsWith("141.223.3.") && !request.getServerName().startsWith("141.223.5.") && request.getMethod().equals("GET")){
	         try {
	            for(int i = 0 ; i < val.length ; i++){
	               val[i] = new String(val[i].getBytes("ISO-8859-1"), "UTF-8");
	            }
	         } catch (UnsupportedEncodingException e) {
	         }
	      }
	      return val;
	      
	   }
	   public String getParameterString(String key, String def){
	      String val = request.getParameter(key);
	      if(val == null){
	         return def;
	      }
	      else if(request.getMethod().equals("GET")){
	         try {
	            val = new String(val.getBytes("ISO-8859-1"), "UTF-8");
	         } catch (UnsupportedEncodingException e) {
	         }
	      }
	      return val;
	   }
	   public int getParameterInt(String key, int def){
	      String val = request.getParameter(key);
	      if(val == null){
	         return def;
	      }
	      return Integer.parseInt(val);
	   }
	   
	   public BigDecimal getParameterBigDecimal(String key, BigDecimal def){
	      String val = request.getParameter(key);
	      if(val == null){
	         return def;
	      }
	      
	      return new BigDecimal(Integer.parseInt(val));
//	      return Integer.parseInt(val);
	   }
	}