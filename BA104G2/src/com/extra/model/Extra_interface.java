package com.extra.model;

import java.util.List;

public interface Extra_interface {
	
	public String insert(ExtraVO extraVO);
	
	public void update(ExtraVO extraVO);

	public List<ExtraVO> getExtras(String sto_num);
	
	public ExtraVO getOneExtra(String ext_num);
}
