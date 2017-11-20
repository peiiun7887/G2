package com.backstage_management.model;

import java.util.*;

public interface BackstageManagementDAO_interface {
	public String insert(BackstageManagementVO bmVO);
	public void update(BackstageManagementVO bmVO);
	public int checkBm_num (String bm_num);
	public BackstageManagementVO findbyPrimaryKey(String bm_no);
	public List<BackstageManagementVO> getAll();
}
