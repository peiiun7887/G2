package com.bm.model;

import java.util.*;

public interface BmDAO_interface {
	public void insert(BmVO bmVO);
	public void update(BmVO bmVO);
	public void delete(String bm_no);
	public BmVO findbyPrimaryKey(String bm_no);
	public List<BmVO> getAll();
}
