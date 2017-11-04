package com.ice_list.model;

import java.util.List;

public class IceListService {

	private IceListDAO_interface dao;

	public IceListService() {
		dao = new IceListDAO();
	}

	public IceListVO insertIceList(IceListVO iceListVO) {
		String ice_num = dao.insert(iceListVO);
		iceListVO.setIce_num(ice_num);
		return iceListVO;
	}

	public IceListVO updateIceList(IceListVO iceListVO) {
		dao.update(iceListVO);
		return iceListVO;
	}

	public List<IceListVO> getSweetness(String sto_num) {
		return dao.getIceList(sto_num);
	}
	
	public IceListVO getOneIce(String ice_num){
		return dao.getOneIce(ice_num);
	}
}
