package com.store_profile.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
import com.store_profile.controller.Maptest.ValueComparator;
import com.store_profile.model.*;

@WebServlet("/IndexServlet.do")
public class IndexServlet extends HttpServlet/* implements Runnable*/{
	
	Map<String,Integer> keywordMap = new Hashtable<String,Integer>();;
	TreeMap<String, Integer> sorted_keyword;


	public void destroy(){
		super.destroy();
		try {
			FileOutputStream fos = new FileOutputStream("/WEB-INF/data/Keyword.csv");
		    ObjectOutputStream oos = new ObjectOutputStream(fos);
		    oos.writeObject(keywordMap);
		    oos.close();
		    fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void init(){
		//載入關鍵字檔案
		 
		BufferedReader bufferedReader = null;
		try {
			FileReader fileReader = new FileReader("/WEB-INF/data/Keyword.csv");
			bufferedReader = new BufferedReader(fileReader);
			String initial = bufferedReader.readLine();
			String str[] = line.split(",");
	        for(int i=1;i<str.length;i++){
	            String arr[] = str[i].split(":");
	            Interger arr2 
	            sorted_keyword.put(arr[0], arr[1]);
	        }
			return;
		}
		
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		double curlat=24.969;
		double curlng=121.192;
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
		double curlat=24.969;
		double curlng=121.192;
		req.setCharacterEncoding("UTF-8");
		String action =req.getParameter("action");
		List<StoreProfileVO> oldList = null;
		
		
		if("search".equals(action)){
			try{
				String keyword = req.getParameter("keyword");
				StoreProfileService spSvc = new StoreProfileService();
				oldList = spSvc.getByKeyword(keyword);//查出有關鍵字且上架狀態的店家
				//把關鍵字存在map
				if(keywordMap.containsKey(keyword)){
					keywordMap.put(keyword, keywordMap.get(keyword)+1);
System.out.println("gain key:"+ keyword);
				}else{
					keywordMap.put(keyword, 1);
System.out.println("add one key: "+keyword);
				}			
				
			} catch (Exception e){	//沒有輸入關鍵字				
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
			ValueComparator bvc = new ValueComparator(keywordMap);
			sorted_keyword = new TreeMap<String, Integer>(bvc);
			sorted_keyword.putAll(keywordMap);
			System.out.println("sorted map: " + sorted_keyword);
			ServletContext context = getServletContext();
		    context.setAttribute("keywordMap", sorted_keyword);
		    
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
	
	//map排序
	public class ValueComparator implements Comparator<String> {
	    Map<String, Integer> base;
	    public ValueComparator(Map<String, Integer> base) {
	        this.base = base;
	    }
	    public int compare(String a, String b) {
	        if (base.get(a) >= base.get(b)) {
	            return -1;
	        } else {
	            return 1;
	        }
	    }
	}

	private String quoteToString(String word){
		return "\""+ word + "\"";
	}
	
//保存關鍵字
	public void writeToCSVFile(File path) throws IOException{
		FileWriter filewriter = new FileWriter(path);		
		BufferedWriter writer = new BufferedWriter(filewriter);
		for(String key : keywordMap.keySet()){
			//為了加上""，key/value使用.toString轉成字串
			writer.write(quoteToString(key.toString())+","+quoteToString(keywordMap.get(key).toString()));
			writer.newLine();
		}
		writer.close();
	}
}
