package com.store_profile.model;

import java.util.List;

public class StoreProfileService {
	private StoreProfileDAO_interface dao;
	
	public StoreProfileService(){
		dao = new StoreProfileDAO();
	}
	
	public List<StoreProfileVO> getAllgeo(){
		return dao.getAllgeo();
	}
}
