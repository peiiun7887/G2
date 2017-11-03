package com.report_member.model;

import java.util.List;

public class ReportMemberService {

	private ReportMemberDAO_interface dao;
	
	public ReportMemberService(){
		dao = new ReportMemberDAO();
	}
	
	public ReportMemberVO addReport(ReportMemberVO reportMemberVO){
		dao.insert(reportMemberVO);
		return reportMemberVO;		
	}
	
	public ReportMemberVO memUpdate(ReportMemberVO reportMemberVO){
		dao.memUpdate(reportMemberVO);
		return reportMemberVO;		
	}
	
	public List<ReportMemberVO> memGetAll(String mem_num){
		return dao.memGetAllRpt(mem_num);
	}
	
	public ReportMemberVO getOne(String rpt_mnum){		
		return dao.getRptbyPrimaryKey(rpt_mnum);
	}
	
	public List<ReportMemberVO> getAll(){
		return dao.getAll();
	}
	
	public ReportMemberVO stfUpdate(ReportMemberVO reportMemberVO){
		dao.stfUpdate(reportMemberVO);
		return reportMemberVO;
	}
}
