package com.sweetness.model;

import java.util.List;

public interface SweetnessDAO_interface {
	
	public void insert(SweetnessVO sweetnessVO);
	
	public void update(SweetnessVO sweetnessVO);

	public List<SweetnessVO> getSweetness(String sto_num);
	
	public SweetnessVO getOneSweetness(String sweet_num);
}
