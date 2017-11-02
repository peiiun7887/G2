package com.ice_list.model;

import java.util.List;

public class IceListService {

	private IceListDAO_interface dao;

	public IceListService() {
		dao = new IceListDAO();
	}

	public IceListVO insertIceList(String sto_num, String ice_type) {

		IceListVO iceListVO = new IceListVO();

		iceListVO.setSto_num(sto_num);
		iceListVO.setIce_type(ice_type);
		iceListVO.setStatus("上架");

		dao.insert(iceListVO);

		return iceListVO;
	}

	public IceListVO updateIceList(String ice_num, String Status) {

		IceListVO iceListVO = new IceListVO();

		iceListVO.setIce_num(ice_num);
		iceListVO.setStatus(Status);

		dao.update(iceListVO);

		return iceListVO;
	}

	public List<IceListVO> getSweetness(String sto_num) {

		IceListVO iceListVO = new IceListVO();

		iceListVO.setSto_num(sto_num);

		return dao.getIceList(iceListVO);
	}
}
