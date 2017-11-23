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
	
	public List<StoreProfileVO> getByKeyword(String keyword){
		return dao.search(keyword);
	}
	
	public List<StoreProfileVO> getNoKeyword(){
		return dao.search();
	}
	
	public StoreProfileVO getOneStoName(String sto_num){
		return dao.getOneByPrimary(sto_num);
	}
	
	public StoreProfileVO getOneByPrimary(String sto_num){
		return dao.getOneByPrimary(sto_num);
	}
}
