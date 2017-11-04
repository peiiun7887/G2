package com.ice_list.model;

import java.util.List;

public interface IceListDAO_interface {
	
	public String insert(IceListVO iceListVO);
	
	public void update(IceListVO iceListVO);

	public List<IceListVO> getIceList(String sto_num);
	
	public IceListVO getOneIce(String ice_num);
}
