package com.store_profile.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.*;
import javax.servlet.http.*;
import com.store_comment.model.*;


public class InitTrigger extends HttpServlet {


	Map<String,Integer> keywordMap = new HashMap<String,Integer>();
	List<Map.Entry<String, Integer>> list_KeyData;
	List<Map.Entry<String, Integer>> list_RankData;


  public void doGet(HttpServletRequest req, HttpServletResponse res) 
                               throws ServletException, IOException {
    res.setContentType("text/plain");                                
    PrintWriter out = res.getWriter(); 
    
    for(String key : keywordMap.keySet() ){
    	out.println(key+","+keywordMap.get(key));
    }
                             
  }
  
  public void init() throws ServletException {
	  System.out.println("init Start");
	  FileReader in ;
	  BufferedReader br;
	  String saveDirectory = "/front-end/data";
	  String realPath = getServletContext().getRealPath(saveDirectory);
	    	try {
				in = new FileReader(realPath+"/key.txt");
System.out.println(realPath);
				br = new BufferedReader(in);
				String str = "";
				while((str = br.readLine())!=null){
System.out.println(str);
					String[] stuInfo = str.split(",");
					keywordMap.put(stuInfo[0],Integer.parseInt(stuInfo[1]));
				}
				br.close();
				in.close();
				
				list_KeyData = new ArrayList<Map.Entry<String, Integer>>(keywordMap.entrySet());
		    	Collections.sort(list_KeyData, new Comparator<Map.Entry<String, Integer>>(){
		            public int compare(Map.Entry<String, Integer> entry1,
		                               Map.Entry<String, Integer> entry2){
		                return (entry2.getValue() - entry1.getValue());
		            }
		        });
				
			} catch (FileNotFoundException e ) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
	    	ServletContext context = getServletContext();

		    context.setAttribute("list_KeyData", list_KeyData);   
		    
		    
		    // Store get Stars /////////////////////////////////////////////
		    
		    StoreCommentService scSvc = new StoreCommentService();
		    Map<String, Integer> rankList = scSvc.getStoreStars();
		    
		    list_RankData = new ArrayList<Map.Entry<String, Integer>>(rankList.entrySet());
	    	Collections.sort(list_RankData, new Comparator<Map.Entry<String, Integer>>(){
	            public int compare(Map.Entry<String, Integer> entry1,
	                               Map.Entry<String, Integer> entry2){
	                return (entry2.getValue() - entry1.getValue());
	            }
	        });
	    	System.out.println(list_RankData);
		    context.setAttribute("list_RankData", list_RankData); 
		    

  }
  
	//keyword排序
//	public class ValueComparator implements Comparator<String> {
//	    Map<String, Integer> base;
//	    public ValueComparator(Map<String, Integer> base) {
//	        this.base = base;
//	    }
//	    public int compare(String a, String b) {
//	        if (base.get(a) >= base.get(b)) {
//	            return -1;
//	        } else {
//	            return 1;
//	        }
//	    }
//	}
  

  

}
