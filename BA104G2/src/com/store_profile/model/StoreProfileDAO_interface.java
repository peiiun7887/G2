package com.store_profile.model;

import java.util.List;

public interface StoreProfileDAO_interface {
	
	public List<StoreProfileVO> getAllgeo();
	
	public List<StoreProfileVO> search(String keyword);
}
