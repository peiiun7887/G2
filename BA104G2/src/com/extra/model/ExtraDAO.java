package com.extra.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ExtraDAO implements Extra_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA104G2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_EXTRA =
			"INSERT INTO EXTRA (EXT_NUM, EXT_NAME, STO_NUM, EXT_AMOUNT, STATUS) "
			+ "VALUES ('EX'||LPAD(to_char(SEQ_EXT_NUM.NEXTVAL),10,'0'),?, ?, ?, ?)";
	private static final String UPDATE = 
			"UPDATE EXTRA SET EXT_NAME=?, EXT_AMOUNT=?, STATUS=? WHERE EXT_NUM=?";
	private static final String GET_EXTRAS = 
			"SELECT * FROM EXTRA WHERE STO_NUM=? AND STATUS<>'刪除'";
	private static final String GET_ONE_EXT =
			"SELECT * FROM EXTRA WHERE EXT_NUM=?";
	@Override
	public String insert(ExtraVO extraVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		String ext_num = null;
		try {
			String[] col = {"ext_num"};
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_EXTRA,col);
			pstmt.setString(1, extraVO.getExt_name());
			pstmt.setString(2, extraVO.getSto_num());
			pstmt.setInt(3, extraVO.getExt_amount());
			pstmt.setString(4, extraVO.getStatus());
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			if (rs.next()) {
				do {
					for (int i = 1; i <= columnCount; i++) {
						ext_num = rs.getString(i);						
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
		return ext_num;
	}

	@Override
	public void update(ExtraVO extraVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1,extraVO.getExt_name());
			pstmt.setInt(2, extraVO.getExt_amount());
			pstmt.setString(3, extraVO.getStatus());
			pstmt.setString(4, extraVO.getExt_num());
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
	public List<ExtraVO> getExtras(String sto_num) {
		List<ExtraVO> extraList = new ArrayList<ExtraVO>();
		ExtraVO extraVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_EXTRAS);
			pstmt.setString(1,sto_num);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				extraVO = new ExtraVO();
				extraVO.setExt_num(rs.getString("EXT_NUM"));
				extraVO.setExt_name(rs.getString("EXT_NAME"));
				extraVO.setSto_num(sto_num);
				extraVO.setExt_amount(rs.getInt("EXT_AMOUNT"));
				extraVO.setStatus(rs.getString("STATUS"));
				extraList.add(extraVO);
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
		return extraList;
	}

	@Override
	public ExtraVO getOneExtra(String ext_num) {
		ExtraVO extraVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_EXT);			
			pstmt.setString(1, ext_num);
			rs = pstmt.executeQuery();
			while(rs.next()){
				extraVO = new ExtraVO();
				extraVO.setExt_num(rs.getString("ext_num"));
				extraVO.setExt_name(rs.getString("ext_name"));
				extraVO.setSto_num(rs.getString("sto_num"));
				extraVO.setExt_amount(rs.getInt("ext_amount"));
				extraVO.setStatus(rs.getString("status"));			
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
		return extraVO;
	}	
}