package com.merged_commodity.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.Part;

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
