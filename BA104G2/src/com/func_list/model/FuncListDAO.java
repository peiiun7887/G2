package com.func_list.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.backstage_management.model.BackstageManagementVO;

public class FuncListDAO implements FuncListDAO_interface{
	
	private static DataSource ds =null;
	static {
		try{
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA104G2");
		}catch (NamingException e) {
			e.printStackTrace(System.err);
		}
	}
	
	private static String GET_ALL = 
			"SELECT * FROM FUNC_LIST";
	
	@Override
	public List<FuncListVO> getAll() {
		List<FuncListVO> list = new ArrayList<FuncListVO>();
		FuncListVO funcListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {				
				funcListVO = new FuncListVO();
				funcListVO.setFunc_no(rs.getString("func_no"));
				funcListVO.setFunc_name(rs.getString("func_name"));

				list.add(funcListVO);
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
