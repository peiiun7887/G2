package com.store_profile.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.store_profile.model.*;

@WebServlet("/IndexServlet.do")
public class IndexServlet extends HttpServlet/* implements Runnable*/{
	
	double curlat=24.969;
	double curlng=121.192;
	
	
//	public void destroy(){
//		searcher = null;
//	}
//	
//	public void init(){
//		searcher = new Thread(this);
//		searcher.setPriority(Thread.MIN_PRIORITY);  
//	    searcher.start();
//	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		curlat = Double.parseDouble(req.getParameter("lat"));
		curlng = Double.parseDouble(req.getParameter("lng"));
	
		//地址JSON
		StoreProfileService spSvc = new StoreProfileService();
		List<StoreProfileVO> orgList = spSvc.getAllgeo();//查出上架狀態的店家
		TreeSet<StoreProfileVO> newList = new TreeSet<>();
		for(StoreProfileVO stoVO : orgList){			
			String addr = stoVO.getArea()+stoVO.getAddress();
			
			double[] latlng = Geoget(addr);//轉經緯度
			double lat = latlng[0];
			double lng = latlng[1];

			double distance = Disget(curlat,curlng,lat,lng);//算距離		
			
			stoVO.setAddress(addr);
			stoVO.setLat(lat);
			stoVO.setLng(lng);
			stoVO.setDistance(distance);
			newList.add(stoVO);	//spVO(sto_num,sto_name,area,addr(完整),lat,lng,distance)	
		}


			Gson gson = new Gson();
			String addrList =gson.toJson(newList);	
			
			res.setContentType("application/json ; charset=UTF-8");
		    PrintWriter out = res.getWriter();		    
		    out.println(addrList);
		    out.flush();
		    out.close();

	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action =req.getParameter("action");
		List<StoreProfileVO> oldList = null;
		
		if("search".equals(action)){
			try{
				String keyword = req.getParameter("keyword");
				StoreProfileService spSvc = new StoreProfileService();
				oldList = spSvc.getByKeyword(keyword);//查出有關鍵字且上架狀態的店家
			} catch (Exception e){
				
				StoreProfileService spSvc = new StoreProfileService();	
				oldList = spSvc.getNoKeyword();				
			} finally {

			TreeSet<StoreProfileVO> newList = new TreeSet<>();
			for(StoreProfileVO stoVO : oldList){			
				String addr = stoVO.getArea()+stoVO.getAddress();
				
				double[] latlng = Geoget(addr);//轉經緯度
				double lat = latlng[0];
				double lng = latlng[1];

				double distance = Disget(curlat,curlng,lat,lng);//算距離		
				System.out.println(distance);
				stoVO.setAddress(addr);
				stoVO.setLat(lat);
				stoVO.setLng(lng);
				stoVO.setDistance(distance);
				newList.add(stoVO);	//spVO(sto_num,sto_name,area,addr(完整),lat,lng,distance)	
			}
			req.setAttribute("stoList", newList);
			RequestDispatcher successView = req.getRequestDispatcher("/front-end/storeList.jsp"); 
			successView.forward(req, res);
			}
		}
	}
//	@Override
//	public void run() {
//
//		while(true){
//			System.out.println("running");
//			StoreProfileService spSvc = new StoreProfileService();
//			stoAllgeo = spSvc.getAllgeo();
//			for(StoreProfileVO stoInfo : stoAllgeo){
//				String addrs = stoInfo.getArea()+stoInfo.getAddress();
//				System.out.println(addrs);
//			}
//			ServletContext context = getServletContext();
//			context.setAttribute("StoreInfo", stoAllgeo);
//			System.out.println(stoAllgeo.toString());
//			try {
//		          Thread.sleep(10000);
//		        }
//		    catch (InterruptedException ignored) { }
//		}
//	}

	//用地址轉出經緯度
	private double[] Geoget(String addr){	
		double[] latlng= new double[2];
		URL url = null;		
		try {
			url = new URL("http://maps.googleapis.com/maps/api/geocode/xml?address="+java.net.URLEncoder.encode(addr,"UTF-8")+"&sensor=false&language=zh-TW"); // 建立URL物件url , 以 中文台北市(之地址換算經緯度為例)
		} catch (MalformedURLException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}	
		try {
			// 以URL物件建立URLConnection物件
			URLConnection urlConn = url.openConnection();
			// 以URLConnection物件取得輸入資料流
			InputStream ins = urlConn.getInputStream();	
			// 建立URL資料流
			BufferedReader br = new BufferedReader(new InputStreamReader(ins, "UTF-8"));
			String data;
			while ((data = br.readLine()) != null) {
				if(data.contains("<location>")){
					data = br.readLine();
					if (data.contains("<lat>")) {
						latlng[0]=Double.parseDouble((data.substring(data.indexOf("<lat>") + 5, data.indexOf("</lat>"))));
					}
					data = br.readLine();
					if (data.contains("<lng>")) {
						latlng[1]=Double.parseDouble((data.substring(data.indexOf("<lng>") + 5, data.indexOf("</lng>"))));
					}
					break;
				}
			}	
			br.close();
			ins.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return latlng;		
	}
	
	//兩點距離轉換
	private double Disget(double curlat , double curlng , double lat , double lng ){
		double distance= 0;
		URL url = null;
	
	
		try {
			url = new URL("http://maps.googleapis.com/maps/api/distancematrix/xml?units=imperial&origins="+curlat+","+curlng+"&destinations="+lat+","+lng+"&sensor=false&language=zh-TW");
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} 		
	
		try {
			// 以URL物件建立URLConnection物件
			URLConnection urlConn = url.openConnection();
			// 以URLConnection物件取得輸入資料流
			InputStream ins = urlConn.getInputStream();
	
			// 建立URL資料流
			BufferedReader in = new BufferedReader(new InputStreamReader(ins, "UTF-8"));
			String data;
			while ((data = in.readLine()) != null) {
				if(data.contains("<distance>")){
					data = in.readLine();
					if (data.contains("<value>")) {
						distance=Double.parseDouble((data.substring(data.indexOf("<value>") + 7, data.indexOf("</value>"))))/1000;
					}					
					break;
				}
			}			
		}catch (IOException e) {
			e.printStackTrace();
		}
		return distance;
	}
}
