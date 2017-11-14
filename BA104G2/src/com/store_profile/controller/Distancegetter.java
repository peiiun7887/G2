package com.store_profile.controller;

import java.net.*;
import java.io.*;

public class Distancegetter {
	
	public static void main(String[] args){
		String distance= null;
		URL url = null;
	
	
			try {
				url = new URL("http://maps.googleapis.com/maps/api/distancematrix/xml?units=imperial&origins=24.969,121.192&destinations=25.0017864,121.3064225&sensor=false&language=zh-TW");
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
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
						distance=(data.substring(data.indexOf("<value>") + 7, data.indexOf("</value>")));
					}					
					break;
				}
			}
//
//			BufferedWriter out = new BufferedWriter(new FileWriter("distance.xml"));
//			char[] buf = new char[1024];
//			int len = 0;
//			while ((len = in.read(buf)) != -1) {
//				out.write(buf, 0, len);
//			}
//			out.close();
//			in.close();
//			ins.close();
			in.close();
			ins.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(distance);
	}
}