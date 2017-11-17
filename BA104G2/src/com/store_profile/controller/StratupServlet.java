package com.store_profile.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/StratupServlet")
public class StratupServlet extends HttpServlet {
	
	

	
//	public void init(){
//		//載入keyword檔案
//		FileReader in ;
//		BufferedReader br;
//	    	try {
//				in = new FileReader("key.txt");
//				br = new BufferedReader(in);
//				String str = "";
//				while((str = br.readLine())!=null){
//				String[] stuInfo = str.split(",");
//				keywordMap.put(stuInfo[0],Integer.parseInt(stuInfo[1]));
//				System.out.println("key："+stuInfo[0]+" value："+stuInfo[1]);
//				}
//				br.close();
//				in.close();
//			} catch (FileNotFoundException e ) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			} 
//	}
//	
//	public void destroy(){
//		super.destroy();
//		try {
//			FileWriter out;
//			BufferedWriter br;
//			try {
//				out = new FileWriter("key.txt");
//				br = new BufferedWriter(out);
//				for (Map.Entry<String,Integer> entry : sorted_keyword.entrySet()) {
//			   
//					br.write(entry.getKey()+","+entry.getValue());
//					br.newLine();
//					System.out.println(entry.getKey()+","+ entry.getValue());
//				}	
//				br.flush();
//				br.close();
//				out.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			} 
//			System.out.println("DONE");
//		} catch (IOException e) {
//			e.printStackTrace();
//		} 
//	}
	
	
	
	
	
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
