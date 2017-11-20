package com.backstage_management.model;

import java.util.List;

public class BackstageManagementService {
	
	private BackstageManagementDAO dao;
	
	public BackstageManagementService(){
		dao = new BackstageManagementDAO();
	}
	
	public BackstageManagementVO addStaff(BackstageManagementVO bmVO){
		String bm_no = dao.insert(bmVO);
		bmVO.setBm_no(bm_no);
		return bmVO;
	}
	
	public BackstageManagementVO updateStaff(BackstageManagementVO bmVO){
		dao.update(bmVO);
		return bmVO;
	}
	
	public BackstageManagementVO findbyPrimaryKey(String bm_no){
		return dao.findbyPrimaryKey(bm_no);
	}
	
	public List<BackstageManagementVO> getAll(){
		return dao.getAll();
	}
	
	public int checkBm_num (String bm_num){
		return dao.checkBm_num(bm_num);
	}
	
	
}
