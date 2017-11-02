package com.ice_list.model;

import java.util.List;

public interface IceListDAO_interface {
	
	public void insert(IceListVO iceListVO);
	
	public void update(IceListVO iceListVO);

	public List<IceListVO> getIceList(IceListVO iceListVO);
}
