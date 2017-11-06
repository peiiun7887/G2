package com.merged_commodity.model;

import java.util.List;

public class MergedCommodityService {

	private MergedCommodityDAO_interface dao;

	public MergedCommodityService() {
		dao = new MergedCommodityDAO();
	}

	public String addMergedCommodity(List<String> list) {
		String mercom_num = dao.insert(list);		
		return mercom_num;
	}
}
