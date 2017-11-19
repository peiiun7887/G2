package com.bm.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BmJNDIDAO implements BmDAO_interface{
	
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
		private static DataSource ds = null;
		static {
			try {
				Context ctx = new javax.naming.InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/xxxDB");
			} catch (NamingException e) {
				e.printStackTrace(System.err);
			}
		}

	private static final String INSERT_STMT = 
			"INSERT INTO BACKSTAGE_MANAGEMENT (bm_no,bm_name,bm_number,bm_mail,bm_banknum,bm_num,bm_pwd,bm_jstatus)"
			+ "VALUES (?,?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = 
			"UPDATE BACKSTAGE_MANAGEMENT SET "
			+ "bm_name=?,bm_number=?,bm_mail=?,bm_banknum=?,bm_num=?,bm_pwd=?,bm_jstatus=? WHERE bm_no=?";
	private static final String DELETE_STMT = 
			"DELETE FROM BACKSTAGE_MANAGEMENT WHERE bm_no=?";
	private static final String GET_ONE_STMT = 
			"SELECT bm_no,bm_name,bm_number,bm_mail,bm_banknum,bm_num,bm_pwd,bm_jstatus "
			+ " FROM BACKSTAGE_MANAGEMENT WHERE bm_no=?";
	private static final String GET_ALL_STMT = 
			"SELECT bm_no,bm_name,bm_number,bm_mail,bm_banknum,bm_num,bm_pwd,bm_jstatus "
			+ "FROM BACKSTAGE_MANAGEMENT ORDER BY bm_no";
	
	@Override
	public void insert(BmVO bmVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, bmVO.getBm_no());
			pstmt.setString(2, bmVO.getBm_name());
			pstmt.setString(3, bmVO.getBm_number());
			pstmt.setString(4, bmVO.getBm_mail());
			pstmt.setString(5, bmVO.getBm_banknum());
			pstmt.setString(6, bmVO.getBm_num());
			pstmt.setString(7, bmVO.getBm_pwd());
			pstmt.setString(8, bmVO.getBm_jstatus());
			//圖片待測試
			pstmt.executeUpdate();
			
		} catch (SQLException se) {	//SQL error
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally{ 			
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
	public void update(BmVO bmVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
						
			pstmt.setString(1, bmVO.getBm_name());
			pstmt.setString(2, bmVO.getBm_number());
			pstmt.setString(3, bmVO.getBm_mail());
			pstmt.setString(4, bmVO.getBm_banknum());
			pstmt.setString(5, bmVO.getBm_num());
			pstmt.setString(6, bmVO.getBm_pwd());
			pstmt.setString(7, bmVO.getBm_jstatus());
			pstmt.setString(8, bmVO.getBm_no());
			//圖片待測試
			pstmt.executeUpdate();
			
			
		} catch (SQLException se) {	//SQL error
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally{ 			
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
	public void delete(String bm_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setString(1,bm_no);
			
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
	public BmVO findbyPrimaryKey(String bm_no) {
		
		BmVO bmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
			try {
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);
				
				pstmt.setString(1, bm_no);
				
				rs = pstmt.executeQuery();
				while(rs.next()){
					bmVO = new BmVO();
					bmVO.setBm_no(rs.getString("bm_no"));
					bmVO.setBm_name(rs.getString("bm_name"));
					bmVO.setBm_number(rs.getString("bm_number"));
					bmVO.setBm_mail(rs.getString("bm_mail"));
					bmVO.setBm_banknum(rs.getString("bm_banknum"));
					bmVO.setBm_num(rs.getString("bm_num"));
					bmVO.setBm_pwd(rs.getString("bm_pwd"));
					bmVO.setBm_jstatus(rs.getString("bm_jstatus"));	
				}
				
			} catch (SQLException se) {				
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
		return bmVO;
	}

	@Override
	public List<BmVO> getAll() {
		List<BmVO> list = new ArrayList<BmVO>();
		BmVO bmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {				
				bmVO = new BmVO();
				bmVO.setBm_no(rs.getString("bm_no"));
				bmVO.setBm_name(rs.getString("bm_name"));
				bmVO.setBm_number(rs.getString("bm_number"));
				bmVO.setBm_mail(rs.getString("bm_mail"));
				bmVO.setBm_banknum(rs.getString("bm_banknum"));
				bmVO.setBm_num(rs.getString("bm_num"));
				bmVO.setBm_pwd(rs.getString("bm_pwd"));
				bmVO.setBm_jstatus(rs.getString("bm_jstatus"));
				list.add(bmVO); // Store the row in the list
			}
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
}
