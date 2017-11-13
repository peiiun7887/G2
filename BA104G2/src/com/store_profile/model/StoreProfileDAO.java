package com.store_profile.model;

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

import com.google.gson.Gson;


public class StoreProfileDAO implements StoreProfileDAO_interface{
	
	private static DataSource ds = null;
	static{
		
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA104G2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
	private static final String GET_ADDR = 
			"SELECT STO_NUM, STO_NAME, AREA, ADDRESS FROM STORE_PROFILE WHERE STO_STATUS = '已上架'";

	@Override
	public List<StoreProfileVO> getAllgeo() {
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<StoreProfileVO> list = new ArrayList<StoreProfileVO>();
		StoreProfileVO sto_info = null;
//		String addrlist=null;
		try{
			con = ds.getConnection();	
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
