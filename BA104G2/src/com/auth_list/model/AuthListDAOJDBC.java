package com.auth_list.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class AuthListDAOJDBC implements AuthListDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA104G2";
	String passwd = "BA104G2";
	
	private static final String INSERT =
			"INSERT INTO AUTH_LIST (BM_NO,FUNC_NO) VALUES (?,?)";
	private static final String DELETE = 
			"DELETE FROM AUTH_LIST WHERE BM_NO = ?";
	private static final String GET_ONE = 
			"SELECT * FROM AUTH_LIST WHERE BM_NO =?";
	private static final String GET_ALL = 
			"SELECT * FROM AUTH_LIST ORDER BY BM_NO";
	
	@Override
	public void insert(String bm_no, List<String> list) {
		PreparedStatement pstmt = null;
		Connection con = null;
	
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			pstmt = con.prepareStatement(INSERT);
			
			for(int i = 0; i<list.size();i++){
				String func_no = list.get(i);
				pstmt.setString(1, bm_no);
				pstmt.setString(2, func_no);
				pstmt.addBatch();				
			}
			
			pstmt.executeBatch();
				
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
					con.setAutoCommit(true);
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(String bm_no) {
		PreparedStatement pstmt = null;
		Connection con = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, bm_no);			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
					con.setAutoCommit(true);
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public List<String> findByBm_no(String bm_no) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			pstmt = con.prepareStatement(GET_ONE);
			pstmt.setString(1, bm_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				String func_no = rs.getString("func_no");		
				list.add(func_no);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public Map<String, List<String>> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String,List<String>> auMap = new HashMap<String , List<String>>();
		List<String> funcSet  ;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			pstmt = con.prepareStatement(GET_ALL);			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String bm_no = rs.getString(1);
				String func_no = rs.getString(2);
				if(auMap.containsKey(bm_no)){
					auMap.get(bm_no).add(func_no);
				}else{
					funcSet = new ArrayList<String>();
					funcSet.add(func_no);
					auMap.put(bm_no, funcSet);
				}
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		return auMap;
	}
	
	public static void main(String[] args){
		AuthListDAOJDBC dao = new AuthListDAOJDBC();
		
		//新增
//		String bm_no = "BM0000000100";
//		List<String> list = new ArrayList<>();
//		list.add("FC0000000001");
//		list.add("FC0000000002");
//		list.add("FC0000000003");
//		list.add("FC0000000004");		
//		dao.insert(bm_no, list);
		
		//刪除
//		String bm_no = "BM0000000100";
//		dao.delete(bm_no);
		
		//查全部
//		Map<String , List<String>> aumap = dao.getAll();
//		for(String key : aumap.keySet()){
//			List<String> set = new ArrayList<String>();
//			set = aumap.get(key);
//			for(String value : set){
//				System.out.println(key+","+value);
//			}
//			Iterator<String> it = set.iterator();
//			while(it.hasNext()){
//				System.out.println(key+","+it.next());
//			}
//		}
	}
}
