package com.store_profile.model;

import java.util.List;

public interface StoreProfileDAO_interface {
	
	public List<StoreProfileVO> getAllgeo();
	public StoreProfileVO getOneByPrimary(String sto_num);
	
	public List<StoreProfileVO> search(String keyword);
	public List<StoreProfileVO> search();
}
