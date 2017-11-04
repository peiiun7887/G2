package com.sweetness.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SweetnessDAO implements SweetnessDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA104G2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	//INSERT改成序列版本
	private static final String INSERT_SWEETNESS = 
			"INSERT INTO SWEETNESS (SWEET_NUM , STO_NUM , SWEET_TYPE, STATUS)"
			+ " VALUES ('SW'||LPAD(to_char(SEQ_SWEET_NUM.NEXTVAL),10,'0'),?,?,?)";
	private static final String UPDATE = "UPDATE SWEETNESS SET SWEET_TYPE=?,STATUS=? WHERE SWEET_NUM=?";
	private static final String GET_SWEETNESS = "SELECT * FROM SWEETNESS WHERE STO_NUM=? AND STATUS<>'刪除'";

	//多加一個getone的指令
	private static final String GET_ONE_SWEETNESS="SELECT * FROM SWEETNESS WHERE SWEET_NUM=?";
	
	@Override
	public String insert(SweetnessVO sweetnessVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		String sweet_num = null;
		try {
			String[] col = {"sweet_num"};
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_SWEETNESS,col);

			pstmt.setString(1, sweetnessVO.getSto_num());
			pstmt.setString(2, sweetnessVO.getSweet_type());
			pstmt.setString(3, sweetnessVO.getStatus());

			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			if (rs.next()) {
				do {
					for (int i = 1; i <= columnCount; i++) {
						sweet_num = rs.getString(i);						
					}
				} while (rs.next());
			}			
			rs.close();

		} catch (SQLException se) {
				throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return sweet_num;
	}

	@Override
	public void update(SweetnessVO sweetnessVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, sweetnessVO.getSweet_type());
			pstmt.setString(2, sweetnessVO.getStatus());
			pstmt.setString(3, sweetnessVO.getSweet_num());

			pstmt.executeUpdate();

		} catch (SQLException se) {
				throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<SweetnessVO> getSweetness(String sto_num) {

		SweetnessVO sweetnessVO = null;
		List<SweetnessVO> sweetList = new ArrayList<SweetnessVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_SWEETNESS);
			pstmt.setString(1, sto_num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				sweetnessVO = new SweetnessVO();
				sweetnessVO.setSweet_num(rs.getString("sweet_num"));
				sweetnessVO.setSto_num(sto_num);
				sweetnessVO.setSweet_type(rs.getString("sweet_type"));
				sweetnessVO.setStatus(rs.getString("status"));
				sweetList.add(sweetnessVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return sweetList;
	}

	public SweetnessVO getOneSweetness(String sweet_num){
		
		SweetnessVO sweetnessVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_SWEETNESS);			
			pstmt.setString(1, sweet_num);
			rs = pstmt.executeQuery();
			while(rs.next()){
				sweetnessVO = new SweetnessVO();
				sweetnessVO.setSweet_num(rs.getString("sweet_num"));
				sweetnessVO.setSto_num(rs.getString("sto_num"));
				sweetnessVO.setSweet_type(rs.getString("sweet_type"));
				sweetnessVO.setStatus(rs.getString("status"));
			}
			
		} catch (Exception se){
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
		return sweetnessVO;
	}
}