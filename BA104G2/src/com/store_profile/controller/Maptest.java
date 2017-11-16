package com.store_profile.controller;


import java.util.Comparator;

import java.util.Hashtable;

import java.util.Map;
import java.util.TreeMap;

import com.google.gson.Gson;

public class Maptest {

	Map<String,Integer> mapp = new Hashtable<String,Integer>();


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
		
		mt.describe();
		
		

	}

}
