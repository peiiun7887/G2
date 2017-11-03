package com.report_member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ReportMemberDAO implements ReportMemberDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA104G2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}	
	//會員檢舉店家or會員or評論
	private static final String INSERT = 
			"INSERT INTO REPORT_MEMBER (RPT_MNUM,MEM_NUM,MEM_NUM2,STO_NUM,COM_NUM,RPT_TIME,STATUS)"
			+ " VALUES ('RM'||LPAD(to_char(SEQ_RPT_MNUM.NEXTVAL),10,'0'),?,?,?,?, CURRENT_TIMESTAMP,?)";
	//會員修改滿意度
	private static final String MEMBER_UPDATE = 
				"UPDATE REPORT_MEMBER SET SCORE=? WHERE RPT_MNUM=?";
	//會員查詢自己的檢舉紀錄
	private static final String MEMBER_GET_ALL_RPT = 
				"SELECT * FROM REPORT_MEMBER WHERE MEM_NUM=?";
	//選一個檢舉紀錄
	private static final String GET_ONE=
				"SELECT * FROM REPORT_MEMBER WHERE RPT_MNUM=?";
	//後台查詢所有會員檢舉紀錄
	private static final String GET_ALL=
				"SELECT * FROM REPORT_MEMBER ORDER BY RPT_TIME DESC";
	//後台更新處理狀態
	private static final String STAFF_UPDATE = 
			"UPDATE REPORT_MEMBER SET STATUS=?, STAFF_NUM=?, WAY=? WHERE RPT_MNUM=?";
	
	@Override
	public void insert(ReportMemberVO reportMemberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setString(1,reportMemberVO.getMem_num());
			pstmt.setString(2, reportMemberVO.getMem_num2());
			pstmt.setString(3, reportMemberVO.getSto_num());
			pstmt.setString(4, reportMemberVO.getCom_num());
			pstmt.setString(5, reportMemberVO.getStatus());//預設待處理
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}				
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}		
		
	}

	@Override
	public void memUpdate(ReportMemberVO reportMemberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {			
			con = ds.getConnection();
			pstmt = con.prepareStatement(MEMBER_UPDATE);
			
			pstmt.setDouble(1,reportMemberVO.getScore());
			pstmt.setString(2, reportMemberVO.getRpt_mnum());
			pstmt.executeUpdate();
		
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}	
		
	}

	@Override
	public List<ReportMemberVO> memGetAllRpt(String mem_num) {
		ReportMemberVO reportMemberVO = null;
		List<ReportMemberVO> list = new ArrayList<ReportMemberVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(MEMBER_GET_ALL_RPT);
			
			pstmt.setString(1,mem_num);
			rs = pstmt.executeQuery();
			while(rs.next()){
				reportMemberVO = new ReportMemberVO();
				reportMemberVO.setRpt_mnum(rs.getString("rpt_mnum"));
				reportMemberVO.setMem_num(rs.getString("mem_num"));
				reportMemberVO.setMem_num2(rs.getString("mem_num2"));
				reportMemberVO.setSto_num(rs.getString("sto_num"));
				reportMemberVO.setCom_num(rs.getString("com_num"));
				reportMemberVO.setRpt_time(rs.getDate("rpt_time"));
				reportMemberVO.setStatus(rs.getString("status"));
				reportMemberVO.setStaff_num(rs.getString("staff_num"));
				reportMemberVO.setScore(rs.getInt("score"));
				reportMemberVO.setWay(rs.getString("way"));
				list.add(reportMemberVO);
			}
		
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public ReportMemberVO getRptbyPrimaryKey(String rpt_mnum) {
		ReportMemberVO reportMemberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE);
			pstmt.setString(1, rpt_mnum);
			rs = pstmt.executeQuery();
			while(rs.next()){
				reportMemberVO = new ReportMemberVO();
				reportMemberVO.setRpt_mnum(rs.getString("rpt_mnum"));
				reportMemberVO.setMem_num(rs.getString("mem_num"));
				reportMemberVO.setMem_num2(rs.getString("mem_num2"));
				reportMemberVO.setSto_num(rs.getString("sto_num"));
				reportMemberVO.setCom_num(rs.getString("com_num"));
				reportMemberVO.setRpt_time(rs.getDate("rpt_time"));
				reportMemberVO.setStatus(rs.getString("status"));
				reportMemberVO.setStaff_num(rs.getString("staff_num"));
				reportMemberVO.setScore(rs.getInt("score"));
				reportMemberVO.setWay(rs.getString("way"));
			}
		
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return reportMemberVO;
	}

	@Override
	public List<ReportMemberVO> getAll() {
		ReportMemberVO reportMemberVO = null;
		List<ReportMemberVO> list = new ArrayList<ReportMemberVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);			
			rs = pstmt.executeQuery();
			while(rs.next()){
				reportMemberVO = new ReportMemberVO();
				reportMemberVO.setRpt_mnum(rs.getString("rpt_mnum"));
				reportMemberVO.setMem_num(rs.getString("mem_num"));
				reportMemberVO.setMem_num2(rs.getString("mem_num2"));
				reportMemberVO.setSto_num(rs.getString("sto_num"));
				reportMemberVO.setCom_num(rs.getString("com_num"));
				reportMemberVO.setRpt_time(rs.getDate("rpt_time"));
				reportMemberVO.setStatus(rs.getString("status"));
				reportMemberVO.setStaff_num(rs.getString("staff_num"));
				reportMemberVO.setScore(rs.getInt("score"));
				reportMemberVO.setWay(rs.getString("way"));
				list.add(reportMemberVO);
			}
		
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public void stfUpdate(ReportMemberVO reportMemberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(STAFF_UPDATE);
			
			pstmt.setString(1, reportMemberVO.getStatus());
			pstmt.setString(2, reportMemberVO.getStaff_num());
			pstmt.setString(3, reportMemberVO.getWay());
			pstmt.setString(4, reportMemberVO.getRpt_mnum());
			pstmt.executeUpdate();
				
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}	
	}

	
}
