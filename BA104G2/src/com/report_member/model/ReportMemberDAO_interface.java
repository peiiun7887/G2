package com.report_member.model;
import java.util.List;

public interface ReportMemberDAO_interface {
	
	public void insert(ReportMemberVO reportMemberVO);	
	public void memUpdate(ReportMemberVO reportMemberVO);
	public List<ReportMemberVO> memGetAllRpt(String mem_num);	
	public ReportMemberVO getRptbyPrimaryKey(String rpt_mnum);
	public List<ReportMemberVO> getAll();
	public void stfUpdate(ReportMemberVO reportMemberVO);
	
}
