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
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT = 
			"INSERT INTO MERGED_COMMODITY (meror_num, mem_num) "
			+ "VALUES ('CN'||LPAD(to_char(SEQ_MERCOM_NUM.NEXTVAL),10,'0'),?)";

	@Override
	public String insert(MergedCommodityVO mergedCommodityVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		String mercom_num = null;
		try {

			con = ds.getConnection();
			String[] col = {"MERCOM_NUM"};
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(INSERT,col);
			
			for(int i =0;i<list.size;i++){
			pstmt.setString(1, mergedCommodityVO.getCom_num());
			pstmt.addBatch();
			}
			
			
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			if (rs.next()) {
				do {
					for (int i = 1; i <= columnCount; i++) {
						mercom_num = rs.getString(i);						
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