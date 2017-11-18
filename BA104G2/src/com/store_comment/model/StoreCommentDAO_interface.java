package com.store_comment.model;

import java.util.List;
import java.util.Map;

public interface StoreCommentDAO_interface {
	
	public List<StoreCommentVO> geStoreCommentBySto_num(String sto_num);

	
	public Map<String, Integer> getStoreStars();
	
	

	

}
