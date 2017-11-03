package com.report_store.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportStoreDAOJDBC implements ReportStoreDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA104G2";
	String passwd = "BA104G2";	
	
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setString(1, reportStoreVO.getSto_num());
			pstmt.setString(2, reportStoreVO.getMem_num());
			pstmt.setString(3, reportStoreVO.getCom_num());
			pstmt.setString(4, reportStoreVO.getStatus());//預設待處理
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());		
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
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(STORE_UPDATE);
			
			pstmt.setInt(1, reportStoreVO.getScore());
			pstmt.setString(2, reportStoreVO.getRpt_snum());
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());		
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(STAFF_UPDATE);
			
			pstmt.setString(1, reportStoreVO.getStatus());
			pstmt.setString(2, reportStoreVO.getStaff_num());
			pstmt.setString(3, reportStoreVO.getWay());
			pstmt.setString(4, reportStoreVO.getRpt_snum());
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());		
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
	
	public static void main(String[] args){
		ReportStoreDAOJDBC dao = new ReportStoreDAOJDBC();
		ReportStoreVO rmVO = new ReportStoreVO();
		
		//insert
		rmVO.setSto_num("ST0000000001");
		rmVO.setMem_num("MB0000000003");
		rmVO.setCom_num("");
		rmVO.setStatus("待處理");
		dao.insert(rmVO);
		
		//sto_update
//		rmVO.setScore(new Integer(5));
//		rmVO.setRpt_snum("RS0000000003");
//		dao.stoUpdate(rmVO);
		
		//sto_get_all_rpt		
//		List<ReportStoreVO> list = dao.stoGetAllRpt("ST0000000001");
//		for(ReportStoreVO all : list){
//			System.out.print(all.getRpt_snum()+" , ");			
//			System.out.print(all.getSto_num()+" , ");
//			System.out.print(all.getMem_num()+" , ");
//			System.out.print(all.getCom_num()+" , ");
//			System.out.print(all.getRpt_time()+" , ");
//			System.out.print(all.getStatus()+" , ");
//			System.out.print(all.getStaff_num()+" , ");
//			System.out.print(all.getScore()+" , ");
//			System.out.print(all.getWay()+" , ");
//			System.out.println();
//		}
		
		//get_one_rpt
//		rmVO = dao.getRptbyPrimaryKey("RM0000000001");
//		System.out.print(rmVO.getRpt_mnum()+" , ");
//		System.out.print(rmVO.getMem_num()+" , ");
//		System.out.print(rmVO.getMem_num2()+" , ");
//		System.out.print(rmVO.getSto_num()+" , ");
//		System.out.print(rmVO.getCom_num()+" , ");
//		System.out.print(rmVO.getRpt_time()+" , ");
//		System.out.print(rmVO.getStatus()+" , ");
//		System.out.print(rmVO.getStaff_num()+" , ");
//		System.out.print(rmVO.getScore()+" , ");
//		System.out.print(rmVO.getWay()+" , ");
//		System.out.println();
//		
		//get_all
//		List<ReportStoreVO> list = dao.getAll();
//		for(ReportStoreVO all : list){
//			System.out.print(all.getRpt_snum()+" , ");			
//			System.out.print(all.getSto_num()+" , ");
//			System.out.print(all.getMem_num()+" , ");
//			System.out.print(all.getCom_num()+" , ");
//			System.out.print(all.getRpt_time()+" , ");
//			System.out.print(all.getStatus()+" , ");
//			System.out.print(all.getStaff_num()+" , ");
//			System.out.print(all.getScore()+" , ");
//			System.out.print(all.getWay()+" , ");
//			System.out.println();
//		}
		
		//staff_update
//		rmVO.setStatus("已處理");
//		rmVO.setStaff_num("BM0000000002");
//		rmVO.setWay("停權");
//		rmVO.setRpt_snum("RS0000000003");
//		dao.stfUpdate(rmVO);
	
	}

}
