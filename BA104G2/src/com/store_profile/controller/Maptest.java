package com.store_profile.controller;


import java.io.*;

import java.util.Comparator;

import java.util.Hashtable;

import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import com.google.gson.Gson;

public class Maptest {

	Map<String,Integer> mapp = new Hashtable<String,Integer>();

//	public void savtoFile() {
//		
//		FileWriter out;
//		PrintWriter br;
//		try {
//			
//			out = new FileWriter("/front-end/data/key.txt");
//			br = new PrintWriter(out);
//			for (Map.Entry<String,Integer> entry : mapp.entrySet()) {
//		   
//				br.println(entry.getKey()+","+entry.getValue());
//			
//				System.out.println(entry.getKey()+","+ entry.getValue());
//			}	
//			br.flush();
//			br.close();
//			out.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//
//		System.out.println("DONE");
//
//	}
	
//	public void getfromfile(){
//		FileReader in ;
//		BufferedReader br;
//	    	try {
//				in = new FileReader("key.txt");
//				br = new BufferedReader(in);
//				String str = "";
//				while((str = br.readLine())!=null){
//				String[] stuInfo = str.split(",");
//				mapp.put(stuInfo[0],Integer.parseInt(stuInfo[1]));
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
	
    public void savekey(String key){		
			if(mapp.containsKey(key)){				//如果table已經有相同的key
				mapp.put(key, mapp.get(key)+1);		//把value找出來+1再放入table
			}else{
				mapp.put(key, 1);					//新的key把value設為1放入table
			}		
	}
    
   
	
	public void describe(){
	    ValueComparator bvc = new ValueComparator(mapp);
	    Map<String, Integer> sorted_map = new TreeMap<String, Integer>(bvc);
System.out.println("unsorted map: " + mapp);
	    sorted_map.putAll(mapp);
System.out.println("sorted map: " + sorted_map);
	    mapp.clear();
System.out.println("sorted map: " + sorted_map);
	    for(String key :sorted_map.keySet()){
	    	mapp.put(key, sorted_map.get(key));
	    }
System.out.println("???: " );	    
	    System.out.println("sorted map: " + mapp);
	}
	
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
	
	public static void main(String[] args) {
	    
		Maptest mt = new Maptest();
		
		mt.savekey("茶");
		mt.savekey("紅茶");
		mt.savekey("綠茶");
		mt.savekey("茶");
		mt.savekey("茶");
		mt.savekey("清新");
		mt.savekey("奶茶");
		mt.savekey("50嵐");
		mt.savekey("50嵐");
		mt.savekey("50嵐");
		mt.savekey("茶湯");
		mt.savekey("茶");
//		
	
//		mt.savtoFile();
//		try {
//			mt.save();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//		Hashtable<String,String> mapp2 = new Hashtable<String,String>();
//		mt.getfromfile();
//		
		
//		Map<String,Integer> mp= mt.laod();
//		System.out.println(mp.size());
//		for(String key : mp.keySet()){
//			
//			System.out.println(key+"--");
//		}
//		
		mt.describe();
		
		

	}
	


}
