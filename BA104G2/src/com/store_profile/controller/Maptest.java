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

	public void savtoFile() {
		
		FileWriter out;
		BufferedWriter br;
		try {
			out = new FileWriter("key.txt");
			br = new BufferedWriter(out);
			for (Map.Entry<String,Integer> entry : mapp.entrySet()) {
		   
				br.write(entry.getKey()+","+entry.getValue());
				br.newLine();
				System.out.println(entry.getKey()+","+ entry.getValue());
			}	
			br.flush();
			br.close();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		System.out.println("DONE");

	}
//	public void save() throws IOException{		
//		FileOutputStream output = new FileOutputStream("key.txt");
//		ObjectOutputStream writer = new ObjectOutputStream(output);
//		writer.writeObject(mapp);
//		writer.close();
//		System.out.println("DAONE");
//	}
//	
//	public Map<String,Integer> laod(){
//		FileInputStream input;
//		ObjectInputStream reader;
//		try {
//			input = new FileInputStream("key.txt");
//			reader = new ObjectInputStream(input);
//			try {
//				Map<String,Integer> mapp = (Map<String,Integer>)reader.readObject();
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} //強制從Obj轉為Restaurant
//			reader.close();
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IOException e) {
//		
//		}
//	
//		
//		return mapp;
//	}
	
	public void getfromfile(){
		FileReader in ;
		BufferedReader br;
	    	try {
				in = new FileReader("key.txt");
				br = new BufferedReader(in);
				String str = "";
				while((str = br.readLine())!=null){
				String[] stuInfo = str.split(",");
				System.out.println("key："+stuInfo[0]+" value："+stuInfo[1]);
				}

				
			} catch (FileNotFoundException e ) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
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
//		Gson gs = new Gson();
//		String list = gs.toJson(sorted_map);
//		System.out.println(list);
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
		
//		mt.savekey("茶");
//		mt.savekey("紅茶");
//		mt.savekey("綠茶");
//		mt.savekey("茶");
//		mt.savekey("茶");
//		mt.savekey("清新");
//		mt.savekey("奶茶");
//		mt.savekey("50嵐");
//		mt.savekey("50嵐");
//		mt.savekey("50嵐");
//		mt.savekey("茶湯");
//		mt.savekey("茶");
////		
//	
//		mt.savtoFile();
//		try {
//			mt.save();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//		Hashtable<String,String> mapp2 = new Hashtable<String,String>();
		mt.getfromfile();
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
