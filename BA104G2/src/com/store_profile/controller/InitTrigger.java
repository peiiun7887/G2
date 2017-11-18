package com.store_profile.controller;

import java.io.*;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.*;
import javax.servlet.http.*;
import com.store_comment.model.*;

public class InitTrigger extends HttpServlet {

	Map<String,Integer> keywordMap = new Hashtable<String,Integer>();;
//	TreeMap<String, Integer> sorted_keyword;


  public void doGet(HttpServletRequest req, HttpServletResponse res) 
                               throws ServletException, IOException {
    res.setContentType("text/plain");                                
    PrintWriter out = res.getWriter(); 
    
    for(String key : keywordMap.keySet() ){
    	out.println(key+","+keywordMap.get(key));
    }
                             
  }
  
  public void init() throws ServletException {
	  System.out.println("init");
	  FileReader in ;
	  BufferedReader br;
	  String saveDirectory = "/front-end/data";
	  String realPath = getServletContext().getRealPath(saveDirectory);
	    	try {
				in = new FileReader(realPath+"/key.txt");
				br = new BufferedReader(in);
				String str = "";
				while((str = br.readLine())!=null){
					System.out.println(str);
					String[] stuInfo = str.split(",");
					System.out.println("key："+stuInfo[0]+" value："+stuInfo[1]);
					keywordMap.put(stuInfo[0],Integer.parseInt(stuInfo[1]));
			
				}
				br.close();
				in.close();
			} catch (FileNotFoundException e ) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
	    	ServletContext context = getServletContext();
		    context.setAttribute("keywordMap", keywordMap);  
		    
		    // Store get Stars /////////////////////////////////////////////
		    
		    StoreCommentService scSvc = new StoreCommentService();
		    Map<String, Integer> rankList = scSvc.getStoreStars();
		    for(String key : rankList.keySet()){
		    	System.out.println(key+","+rankList.get(key));
		    }
		    context.setAttribute("rankList", rankList); 
		    
  }
  
	//保存關鍵字
//	public void saveKeyword() throws IOException{
//		FileWriter out = null;
//		BufferedWriter br = null;
//		try {
//			out = new FileWriter("key.txt");
//			br = new BufferedWriter(out);
//			for (Map.Entry<String,Integer> entry : keywordMap.entrySet()) {
//				br.write(entry.getKey()+","+entry.getValue());
//				br.newLine();
//				System.out.println(entry.getKey()+","+ entry.getValue());
//			}	
//			br.flush();
//			br.close();
//			out.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} 
//		System.out.println("SAVED");	 
//	}
}
