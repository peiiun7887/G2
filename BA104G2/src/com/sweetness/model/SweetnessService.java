package com.sweetness.model;

import java.util.List;

public class SweetnessService {

	private SweetnessDAO_interface dao;

	public SweetnessService() {
		dao = new SweetnessDAO();
	}

	public SweetnessVO insertSweetness(SweetnessVO sweetnessVO) {
		String sweet_num = dao.insert(sweetnessVO);
		sweetnessVO.setSweet_num(sweet_num);
		return sweetnessVO;
	}

	public SweetnessVO updateSweetness(SweetnessVO sweetnessVO) {
		dao.update(sweetnessVO);
		return sweetnessVO;
	}

	public List<SweetnessVO> getSweetness(String sto_num) {
		return dao.getSweetness(sto_num);
	}

	public SweetnessVO getOneSweetness(String sweet_num){
		return dao.getOneSweetness(sweet_num);
	}
}
