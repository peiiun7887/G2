package com.sweetness.model;

import java.util.List;

public class SweetnessService {

	private SweetnessDAO_interface dao;

	public SweetnessService() {
		dao = new SweetnessDAO();
	}

	public SweetnessVO insertSweetness(String sto_num, String sweet_type) {

		SweetnessVO sweetnessVO = new SweetnessVO();

		sweetnessVO.setSto_num(sto_num);
		sweetnessVO.setSweet_type(sweet_type);
		sweetnessVO.setStatus("上架");

		dao.insert(sweetnessVO);

		return sweetnessVO;
	}

	public SweetnessVO updateSweetness(String sweet_num, String Status) {

		SweetnessVO sweetnessVO = new SweetnessVO();

		sweetnessVO.setSweet_num(sweet_num);
		sweetnessVO.setStatus(Status);

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
