package com.extra.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ExtraService {

	private Extra_interface dao;

	public ExtraService() {
		dao = new ExtraDAO();
	}

	public ExtraVO addExtra(ExtraVO extraVO) {
		String ext_num = dao.insert(extraVO);
		extraVO.setExt_num(ext_num);
		return extraVO;
	}

	public ExtraVO updateExtra(ExtraVO extraVO){
		dao.update(extraVO);
		return extraVO;		
	}

	public List<ExtraVO> getExtras(String sto_num) {
		return dao.getExtras(sto_num);
	}
	
	public ExtraVO getOneExtra(String ext_num){
		return dao.getOneExtra(ext_num);
	}
}
