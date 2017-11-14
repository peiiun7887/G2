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
import java.util.Date;
import java.util.List;

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
		double curlat = Double.parseDouble(req.getParameter("lat"));
		double curlng = Double.parseDouble(req.getParameter("lng"));
		System.out.println(curlat+","+curlng);
//		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
//        String json = "";
//        if(br != null){
//            json = br.readLine();
//        }
//        System.out.println(json.toString());
        
		
		
		//地址JSON
		StoreProfileService spSvc = new StoreProfileService();
		List<StoreProfileVO> orgList = spSvc.getAllgeo();//查出上架狀態的店家

		List<StoreProfileVO> newList = new ArrayList<StoreProfileVO>();
		for(StoreProfileVO stoVO : orgList){			
			String addr = stoVO.getArea()+stoVO.getAddress();
			
			double[] latlng = Geoget(addr);//轉經緯度
			double lat = latlng[0];
			double lng = latlng[1];

			double distence = Disget(curlat,curlng,lat,lng);//算距離
			
			
			stoVO.setAddress(addr);
			stoVO.setLat(lat);
			stoVO.setLng(lng);
			stoVO.setDistance(distence);
			newList.add(stoVO);	//spVO(sto_num,sto_name,area,addr(完整),lat,lng,distance)	
		}
		
		System.out.println("-----2----------");
			Gson gson = new Gson();
			String addrList =gson.toJson(newList);	
			
			res.setContentType("application/json ; charset=UTF-8");
		    PrintWriter out = res.getWriter();
		    
		    out.println(addrList);
			System.out.println("-----3----------");
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action =req.getParameter("action");
		HttpSession se = req.getSession();
		
		if("search".equals(action)){
			String keyword = req.getParameter("keyword");
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
						distance=Double.parseDouble((data.substring(data.indexOf("<value>") + 7, data.indexOf("</value>"))));
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
