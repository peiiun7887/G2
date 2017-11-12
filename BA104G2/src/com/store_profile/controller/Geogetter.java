package com.store_profile.controller;

import java.net.*;
import java.io.*;

public class Geogetter {
	
	public static void main(String[] args){
		String[] latlng= new String[2];
		URL url = null;
	
		try {
			url = new URL("http://maps.googleapis.com/maps/api/geocode/xml?address="+java.net.URLEncoder.encode("桃園市桃園區中正路475號","UTF-8")+"&sensor=false&language=zh-TW"); // 建立URL物件url , 以 中文台北市(之地址換算經緯度為例)
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
						latlng[0]=(data.substring(data.indexOf("<lat>") + 5, data.indexOf("</lat>")));
					}
					data = br.readLine();
					if (data.contains("<lng>")) {
						latlng[1]=(data.substring(data.indexOf("<lng>") + 5, data.indexOf("</lng>")));
					}
					break;
				}
			}
	
			br.close();
			ins.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(latlng[0]+","+latlng[1]);
	}
}