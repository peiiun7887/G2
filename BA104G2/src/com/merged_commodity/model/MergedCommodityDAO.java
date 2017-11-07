package com.merged_commodity.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class MergedCommodityDAO implements MergedCommodityDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA104G2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT = 
			"INSERT INTO MERGED_COMMODITY (MERCOM_NUM, COM_NUM) "
			+ " VALUES ('MC'||LPAD(to_char(SEQ_MERCOM_NUM.CURRVAL),10,'0'), ? ) ";
	private static final String NEXTVAL= "SELECT SEQ_MERCOM_NUM.NEXTVAL FROM DUAL";
	private static final String CURR= "SELECT * FROM MERGED_COMMODITY";
	
	@Override
	public String insert(List<String> list) {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		Connection con = null;
		ResultSet rs = null;
		String mercom_num = null;
		try {
			
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(NEXTVAL);//抓下一個流水號
			pstmt.executeQuery();
			
			pstmt2 = con.prepareStatement(INSERT);	
			
			for(int i =0;i<list.size();i++){
				String com_num = list.get(i);
				pstmt2.setString(1, com_num);
				pstmt2.addBatch();	
			}		
			 pstmt2.executeBatch();
			
			
			 pstmt3 = con.prepareStatement(CURR,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			 rs = pstmt3.executeQuery();
			 rs.absolute(-1);
			 mercom_num = rs.getString("MERCOM_NUM");			 
			 
			 con.commit();
		} catch (SQLException se) {
			try {
				con.rollback();
				throw new RuntimeException("A database error occured. " + se.getMessage());
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
					con.setAutoCommit(true);
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return mercom_num;
	}
}