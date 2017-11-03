package com.product_type.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductTypeDAOJDBC implements ProductType_interface{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA104G2";
	String passwd = "BA104G2";
	
	private static final String INSERT = 
			"INSERT INTO PRODUCT_TYPE (PT_NUM,PT_NAME) "
			+ " VALUES ('PT'||LPAD(to_char(SEQ_RPT_SNUM.NEXTVAL),3,'0'),?)";
	private static final String GET_ALL_STMT=
			"SELECT * FROM PRODUCT_TYPE";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM PRODUCT_TYPE WHERE PT_NUM=?";
	@Override
	public void insert(ProductTypeVO productTypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setString(1, productTypeVO.getPt_name());
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());		
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
	public List<ProductTypeVO> getAll() {
		List<ProductTypeVO> list = new ArrayList<ProductTypeVO>();
		ProductTypeVO pdcTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				pdcTypeVO = new ProductTypeVO();
				pdcTypeVO.setPt_num(rs.getString("pt_num"));
				pdcTypeVO.setPt_name(rs.getString("pt_name"));
				list.add(pdcTypeVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
		return list;
	}
	
	@Override
	public ProductTypeVO getOnePdcT(String pt_num) {
		ProductTypeVO productTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, pt_num);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				productTypeVO = new ProductTypeVO();
				productTypeVO.setPt_num(rs.getString("pt_num"));
				productTypeVO.setPt_name(rs.getString("pt_name"));
			}
			
		}catch (ClassNotFoundException e) {
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
		return productTypeVO;
	}
	
	public static void main(String[] args){
		ProductTypeDAOJDBC dao = new ProductTypeDAOJDBC();
		ProductTypeVO pdtVO = new ProductTypeVO();
		//insert
		pdtVO.setPt_name("草本養生茶");
		dao.insert(pdtVO);
		
		//查全部
//		List<ProductTypeVO> list = dao.getAll();
//		for(ProductTypeVO apdcT : list){
//			System.out.print(apdcT.getPt_num());
//			System.out.print(apdcT.getPt_name());
//			System.out.println();
//		}
		
		//查一個
//		ProductTypeVO pdtVO = dao.getOnePdcT("PT001");
//		System.out.print(pdtVO.getPt_num());
//		System.out.println(pdtVO.getPt_name());
//		System.out.println("------------------");
	}
}
