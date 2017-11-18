package com.store_comment.model;

import java.util.List;
import java.util.Map;

public class StoreCommentService {
	
	private StoreCommentDAO_interface dao;

	public StoreCommentService() {
		dao = new StoreCommentDAO();
	}

	public List<StoreCommentVO> geStoreCommentBySto_num(String sto_num) {
		return dao.geStoreCommentBySto_num(sto_num);
	}
	
	public Map<String, Integer> getStoreStars(){
		return dao.getStoreStars();
	}

}
