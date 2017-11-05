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

	public MergedCommodityVO addMergedCommodity(String com_num) {

		MergedCommodityVO mergedCommodityVO = new MergedCommodityVO();

		mergedCommodityVO.setCom_num(com_num);

		dao.insert(mergedCommodityVO);

		return mergedCommodityVO;
	}
}
