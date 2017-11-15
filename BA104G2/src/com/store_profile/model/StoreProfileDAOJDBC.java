package com.store_profile.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;


public class StoreProfileDAOJDBC implements StoreProfileDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA104G2";
	String passwd = "BA104G2";
	
	private static final String GET_ADDR = 
			"SELECT STO_NUM, STO_NAME, AREA, ADDRESS FROM STORE_PROFILE WHERE STO_STATUS = '未上架' OR STO_STATUS = '已上架'";
	private static final String KEYWORD_SEARCH =
			"SELECT SP.STO_NUM, SP.STO_NAME , AREA, ADDRESS FROM STORE_PROFILE SP "
			+ " RIGHT JOIN PRODUCT P ON P.STO_NUM = SP.STO_NUM"
			+ " LEFT JOIN PRODUCT_TYPE PT ON P.PT_NUM = PT.PT_NUM"
			+ " WHERE (SP.STO_NAME LIKE ? OR P.COM_NAME LIKE ? OR PT.PT_NAME LIKE ? ) AND STO_STATUS='已上架'"
			+ " GROUP BY SP.STO_NUM, SP.STO_NAME , AREA, ADDRESS";
	private static final String N_KEYWORD_SEARCH =
			"SELECT SP.STO_NUM, SP.STO_NAME , AREA, ADDRESS FROM STORE_PROFILE SP "
			+ " RIGHT JOIN PRODUCT P ON P.STO_NUM = SP.STO_NUM"
			+ " LEFT JOIN PRODUCT_TYPE PT ON P.PT_NUM = PT.PT_NUM"
			+ " WHERE STO_STATUS='已上架'"
			+ " GROUP BY SP.STO_NUM, SP.STO_NAME , AREA, ADDRESS";
	private static final String GET_ONE_STONAME = 
			"SELECT sto_num , sto_name FROM STORE_PROFILE WHERE sto_num=?";

	
	@Override
	public List<StoreProfileVO> getAllgeo() {
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<StoreProfileVO> list = new ArrayList<StoreProfileVO>();
		StoreProfileVO sto_info = null;
//		String addrlist=null;
		try{
			Class.forName(driver);

			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ADDR);			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				sto_info = new StoreProfileVO();	
				sto_info.setSto_num(rs.getString("sto_num"));
				sto_info.setSto_name(rs.getString("sto_name"));
				sto_info.setArea(rs.getString("area"));
				sto_info.setAddress(rs.getString("address"));
				
				list.add(sto_info);
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
	
	public static void main(String[] args) {
		
		StoreProfileDAOJDBC dao = new StoreProfileDAOJDBC();
		List<StoreProfileVO> list = dao.search("茶");
		for( StoreProfileVO s :list){
			System.out.println(s.getAddress());
		}
	}

	@Override
	public List<StoreProfileVO> search(String keyword) {
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<StoreProfileVO> list = new ArrayList<StoreProfileVO>();
		StoreProfileVO sto_info = null;
//		String addrlist=null;
		try{
			Class.forName(driver);

			con = DriverManager.getConnection(url, userid, passwd);
		
			pstmt = con.prepareStatement(N_KEYWORD_SEARCH);
//			pstmt.setString(1,"%" +keyword+"%");
//			pstmt.setString(2,"%" +keyword+"%");
//			pstmt.setString(3,"%" +keyword+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				sto_info = new StoreProfileVO();	
				sto_info.setSto_num(rs.getString("sto_num"));
				sto_info.setSto_name(rs.getString("sto_name"));
				sto_info.setArea(rs.getString("area"));
				sto_info.setAddress(rs.getString("address"));
				
				list.add(sto_info);
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
	public List<StoreProfileVO> search() {
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<StoreProfileVO> list = new ArrayList<StoreProfileVO>();
		StoreProfileVO sto_info = null;

		try{
			Class.forName(driver);

			con = DriverManager.getConnection(url, userid, passwd);
		
			pstmt = con.prepareStatement(N_KEYWORD_SEARCH);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				sto_info = new StoreProfileVO();	
				sto_info.setSto_num(rs.getString("sto_num"));
				sto_info.setSto_name(rs.getString("sto_name"));
				sto_info.setArea(rs.getString("area"));
				sto_info.setAddress(rs.getString("address"));
				
				list.add(sto_info);
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
	public StoreProfileVO getOneByPrimary(String sto_num) {
		StoreProfileVO spVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
	
			pstmt = con.prepareStatement(GET_ONE_STONAME);
			pstmt.setString(1, sto_num);
			rs = pstmt.executeQuery();
		
			while(rs.next()){
				spVO = new StoreProfileVO();
				spVO.setSto_num(rs.getString("sto_num"));
				spVO.setSto_name(rs.getString("sto_name"));	
			}
		
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
		return spVO;
	}



}
