package com.store_profile.controller;

import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

import javax.servlet.*;
import javax.servlet.http.*;
import com.store_comment.model.*;


public class InitTrigger extends HttpServlet {


	Map<String,Integer> keywordMap = new HashMap<String,Integer>();
	List<Map.Entry<String, Integer>> list_KeyData;
	List<Map.Entry<String, Integer>> list_RankData;
	Timer timer;

  public void doGet(HttpServletRequest req, HttpServletResponse res) 
                               throws ServletException, IOException {
//    res.setContentType("text/plain");                                
//    PrintWriter out = res.getWriter(); 
//    
//    for(String key : keywordMap.keySet() ){
//    	out.println(key+","+keywordMap.get(key));
//    }
//                             
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

		    
		    // schedule to get star /////////////////////////////////////////
		    TimerTask task = new TimerTask(){ 
		        public void run() {
		        	
		        	 // Store get Stars /////////////////////////////////////
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
		    };
			timer = new Timer(); 
			Calendar calendar = Calendar.getInstance();
			// 設定填入schedule中的 Date firstTime為現在的5秒後
			//calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND)+5);
			timer.schedule(task, calendar.getTime(), 1*5*60*1000);	//5分鐘算一次
			System.out.println("已建立排程!");       
	

  }
  
  public void destroy() {
      timer.cancel();
      System.out.println("已結束排程!");
  }

}
