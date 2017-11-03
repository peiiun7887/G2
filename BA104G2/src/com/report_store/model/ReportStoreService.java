package com.report_store.model;

import java.util.List;

public class ReportStoreService {
	private ReportStoreDAO_interface dao;
	
	public ReportStoreService(){
		dao = new ReportStoreDAO();
	}
	
	public ReportStoreVO addReport(ReportStoreVO reportStoreVO){
		reportStoreVO.setStatus("待處理");
		dao.insert(reportStoreVO);
		return reportStoreVO;		
	}
	
	public ReportStoreVO stoUpdate(ReportStoreVO reportStoreVO){
		dao.stoUpdate(reportStoreVO);
		return reportStoreVO;		
	}
	
	public List<ReportStoreVO> memGetAll(String mem_num){
		return dao.stoGetAllRpt(mem_num);
	}
	
	public ReportStoreVO getOne(String rpt_mnum){		
		return dao.getRptbyPrimaryKey(rpt_mnum);
	}
	
	public List<ReportStoreVO> getAll(){
		return dao.getAll();
	}
	
	public ReportStoreVO stfUpdate(ReportStoreVO reportStoreVO){
		dao.stfUpdate(reportStoreVO);
		return reportStoreVO;
	}
}
