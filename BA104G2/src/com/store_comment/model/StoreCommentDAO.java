package com.store_comment.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class StoreCommentDAO implements StoreCommentDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA104G2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String GET_STORE_COMMENT = 
			"SELECT * FROM STORE_COMMENT WHERE sto_num=? ORDER BY COM_TIME DESC";
	
	private static final String GET_STORE_STAR = 
			"SELECT SP.sto_num , SP.sto_name ,AVG(STARS)FROM STORE_PROFILE SP "
			+ " LEFT JOIN STORE_COMMENT SC ON SP.STO_NUM = SC.STO_NUM WHERE SP.STO_STATUS = '已上架' AND SC.STATUS = '一般'  "
			+ " GROUP BY SP.STO_NUM, SP.STO_NAME ORDER BY AVG(STARS) DESC";
			
	
	
	
	@Override
	public List<StoreCommentVO> geStoreCommentBySto_num(String sto_num) {
		
		List<StoreCommentVO> storeCommentList = new ArrayList<StoreCommentVO>();
		StoreCommentVO storeCommentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_STORE_COMMENT);
			pstmt.setString(1, sto_num);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				storeCommentVO = new StoreCommentVO();
				storeCommentVO.setCom_num(rs.getString("com_num"));
				storeCommentVO.setCom_title(rs.getString("com_title"));
				storeCommentVO.setSto_num(rs.getString("sto_num"));
				storeCommentVO.setMem_num(rs.getString("mem_num"));
				storeCommentVO.setStars(rs.getInt("stars"));
				storeCommentVO.setCommentt(rs.getString("commentt"));
				storeCommentVO.setCom_time(rs.getDate("com_time"));
				storeCommentVO.setStatus(rs.getString("status"));
				storeCommentList.add(storeCommentVO);
			}

			// Handle any driver errors
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
		return storeCommentList;
	}
	
	public Map<String, Integer> getStoreStars() {
		Map<String, Integer> storeCommentList = new HashMap<String, Integer>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_STORE_STAR);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				storeCommentList.put(rs.getString("STO_Num"),rs.getInt("AVG(STARS)"));
				
			}
			System.out.println(storeCommentList);
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
		return storeCommentList;
	}

}
