package com.report_store.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ReportStoreDAO implements ReportStoreDAO_interface{
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
			"INSERT INTO REPORT_STORE (RPT_SNUM,STO_NUM,MEM_NUM,COM_NUM,RPT_TIME,STATUS)"
			+ " VALUES ('RS'||LPAD(to_char(SEQ_RPT_SNUM.NEXTVAL),10,'0'),?,?,?,CURRENT_TIMESTAMP,?)";
	//會員修改滿意度
	private static final String STORE_UPDATE = 
				"UPDATE REPORT_STORE SET SCORE=? WHERE RPT_SNUM=?";
	//會員查詢自己的檢舉紀錄
	private static final String STORE_GET_ALL_RPT = 
				"SELECT * FROM REPORT_STORE WHERE STO_NUM=?";
	//選一個檢舉紀錄
	private static final String GET_ONE_RPT=
				"SELECT * FROM REPORT_STORE WHERE RPT_SNUM=?";
	//後台查詢所有會員檢舉紀錄
	private static final String GET_ALL=
				"SELECT * FROM REPORT_STORE ORDER BY RPT_SNUM DESC";
	//後台更新處理狀態
	private static final String STAFF_UPDATE = 
			"UPDATE REPORT_STORE SET STATUS=?, STAFF_NUM=?, WAY=? WHERE RPT_SNUM=?";	
	
	
	
	
	@Override
	public void insert(ReportStoreVO reportStoreVO) {
		Connection con = null;
		PreparedStatement pstmt = null;	
		try {
			con = ds.getConnection();			
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setString(1, reportStoreVO.getSto_num());
			pstmt.setString(2, reportStoreVO.getMem_num());
			pstmt.setString(3, reportStoreVO.getCom_num());
			pstmt.setString(4, reportStoreVO.getStatus());//預設待處理
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
	public void stoUpdate(ReportStoreVO reportStoreVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(STORE_UPDATE);
			
			pstmt.setInt(1, reportStoreVO.getScore());
			pstmt.setString(2, reportStoreVO.getRpt_snum());
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
	public List<ReportStoreVO> stoGetAllRpt(String sto_num) {
		ReportStoreVO reportStoreVO = null;
		List<ReportStoreVO> list = new ArrayList<ReportStoreVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(STORE_GET_ALL_RPT);
			
			pstmt.setString(1,sto_num);
			rs = pstmt.executeQuery();
			while(rs.next()){
				reportStoreVO = new ReportStoreVO();
				reportStoreVO.setRpt_snum(rs.getString("rpt_snum"));
				reportStoreVO.setSto_num(rs.getString("sto_num"));
				reportStoreVO.setMem_num(rs.getString("mem_num"));
				reportStoreVO.setCom_num(rs.getString("com_num"));
				reportStoreVO.setRpt_time(rs.getDate("rpt_time"));
				reportStoreVO.setStatus(rs.getString("status"));
				reportStoreVO.setStaff_num(rs.getString("staff_num"));
				reportStoreVO.setScore(rs.getInt("score"));
				reportStoreVO.setWay(rs.getString("way"));
				list.add(reportStoreVO);
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
	public ReportStoreVO getRptbyPrimaryKey(String rpt_snum) {
		ReportStoreVO reportStoreVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_RPT);
			pstmt.setString(1, rpt_snum);
			rs = pstmt.executeQuery();
			while(rs.next()){
				reportStoreVO= new ReportStoreVO();
				reportStoreVO.setRpt_snum(rs.getString("rpt_snum"));
				reportStoreVO.setSto_num(rs.getString("sto_num"));
				reportStoreVO.setMem_num(rs.getString("mem_num"));
				reportStoreVO.setCom_num(rs.getString("com_num"));
				reportStoreVO.setRpt_time(rs.getDate("rpt_time"));
				reportStoreVO.setStatus(rs.getString("status"));
				reportStoreVO.setStaff_num(rs.getString("staff_num"));
				reportStoreVO.setScore(rs.getInt("score"));
				reportStoreVO.setWay(rs.getString("way"));
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
		return reportStoreVO;
	}

	@Override
	public List<ReportStoreVO> getAll() {
		ReportStoreVO reportStoreVO = null;
		List<ReportStoreVO> list = new ArrayList<ReportStoreVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);			
			rs = pstmt.executeQuery();
			while(rs.next()){
				reportStoreVO = new ReportStoreVO();
				reportStoreVO.setRpt_snum(rs.getString("rpt_snum"));
				reportStoreVO.setSto_num(rs.getString("sto_num"));
				reportStoreVO.setMem_num(rs.getString("mem_num"));
				reportStoreVO.setCom_num(rs.getString("com_num"));
				reportStoreVO.setRpt_time(rs.getDate("rpt_time"));
				reportStoreVO.setStatus(rs.getString("status"));
				reportStoreVO.setStaff_num(rs.getString("staff_num"));
				reportStoreVO.setScore(rs.getInt("score"));
				reportStoreVO.setWay(rs.getString("way"));
				list.add(reportStoreVO);
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
	public void stfUpdate(ReportStoreVO reportStoreVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(STAFF_UPDATE);
			
			pstmt.setString(1, reportStoreVO.getStatus());
			pstmt.setString(2, reportStoreVO.getStaff_num());
			pstmt.setString(3, reportStoreVO.getWay());
			pstmt.setString(4, reportStoreVO.getRpt_snum());
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
