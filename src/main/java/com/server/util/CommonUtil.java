package com.server.util;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

public class CommonUtil {
	   private static Map<String, Object> request2MapFromJson(HttpServletRequest request) {
		      HashMap<String, Object> map = new HashMap<String, Object>();

		      try {
		         InputStream iStream = request.getInputStream();
		         StringWriter writer = new StringWriter();
		         IOUtils.copy(iStream, writer, "UTF-8");
		         String response = writer.toString();
		         if (!response.equals("")) {
		            JSONObject obj = new JSONObject(response);
		            String[] names = JSONObject.getNames(obj);
		            for (String name : names) {
		               String value = obj.getString(name);
		               map.put(name, value);
		            }
		         }
		      } catch (Exception e) {
		         System.out.println(e.getMessage());
		      }

		      return map;
		   }
	   
	   /**
	    * request 파라메터를 Map으로 변환
	    * 
	    * @param request
	    * @return
	    */
	   public static Map<String, Object> request2Map(HttpServletRequest request) {
	      HashMap<String, Object> map = new HashMap<String, Object>();

	      try {
	         if (request.getContentType() != null && request.getContentType().toLowerCase().equals("application/json")) {
	            return request2MapFromJson(request);
	         }
	         Enumeration<?> param = request.getParameterNames();
	         RequestHelper helper = new RequestHelper(request);
	         while (param.hasMoreElements()) {
	            String pName = (String) param.nextElement();
	            // String pValue = request.getParameter(pName);
	            String pValue = helper.getParameterString(pName, "");

	            map.put(pName, pValue);
	         }
	      } catch (Exception e) {
	         System.out.println(e.getMessage());
	      }

	      return map;
	   }
}
