package com.report_store.model;

import java.util.List;

public interface ReportStoreDAO_interface {
	public void insert(ReportStoreVO reportStoreVO);	
	public void stoUpdate(ReportStoreVO reportStoreVO);
	public List<ReportStoreVO> stoGetAllRpt(String sto_num);	
	public ReportStoreVO getRptbyPrimaryKey(String rpt_snum);
	public List<ReportStoreVO> getAll();
	public void stfUpdate(ReportStoreVO reportStoreVO);
}
