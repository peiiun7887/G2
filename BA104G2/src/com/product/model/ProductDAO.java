package com.product.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ProductDAO implements ProductDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA104G2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}	
	
	private static final String INSERT_STMT = 
			"INSERT INTO PRODUCT (sto_num,com_name,m_price,l_price,discribe,img,pt_num,status,mercom_num,com_num)"
			+ " VALUES (?,?,?,?,?,?,?,?,?,'CN'||LPAD(to_char(SEQ_COM_NUM.NEXTVAL),10,'0'))";
	private static final String UPDATE_STMT =
			"UPDATE PRODUCT SET "
			+ " com_name=?,m_price=?,l_price=?,discribe=?,img=?,pt_num=?,status=?,mercom_num=? WHERE com_num=? ";
	private static final String GET_ALL_STMT=
			"SELECT com_num,sto_num,com_name,m_price,l_price,discribe,img,pt_num,status,mercom_num FROM PRODUCT";
	private static final String GET_ONE_STMT=
			"SELECT com_num,sto_num,com_name,m_price,l_price,discribe,img,pt_num,status,mercom_num "
			+ " FROM PRODUCT WHERE com_num=?";
	private static final String STO_GET_BY_COM_NAME = 
			"SELECT com_num,sto_num,com_name,m_price,l_price,discribe,img,pt_num,status,mercom_num"
			+ " FROM (SELECT * FROM PRODUCT WHERE status<>'刪除') WHERE com_name LIKE ? AND sto_num=?";
	private static final String STO_GET_ALL = 	
			"SELECT com_num,sto_num,com_name,m_price,l_price,discribe,img,pt_num,status,mercom_num"
			+ " FROM (SELECT * FROM PRODUCT WHERE status<>'刪除') WHERE sto_num=? ORDER BY com_num desc ";
	
	@Override
	public String insert(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String com_num = null;
		ResultSet rs = null;
		try {
			
			con =ds.getConnection();
			String[] col = {"com_num"};
			pstmt = con.prepareStatement(INSERT_STMT,col);
			pstmt.setString(1, productVO.getSto_num());
			pstmt.setString(2, productVO.getCom_name());
			pstmt.setDouble(3, productVO.getM_price());
			pstmt.setDouble(4, productVO.getL_price());
			pstmt.setString(5, productVO.getDiscribe());
			pstmt.setBytes(6, productVO.getImg());
			pstmt.setString(7, productVO.getPt_num());
			pstmt.setString(8, productVO.getStatus());
			pstmt.setString(9, productVO.getMercom_num());
			pstmt.executeUpdate();
	
			rs = pstmt.getGeneratedKeys();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			if (rs.next()) {
				do {
					for (int i = 1; i <= columnCount; i++) {
						com_num = rs.getString(i);						
					}
				} while (rs.next());
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
		return com_num;
	}

	@Override
	public void update(ProductVO productVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, productVO.getCom_name());
			pstmt.setDouble(2, productVO.getM_price());
			pstmt.setDouble(3, productVO.getL_price());
			pstmt.setString(4, productVO.getDiscribe());
			pstmt.setBytes(5, productVO.getImg());
			pstmt.setString(6, productVO.getPt_num());
			pstmt.setString(7, productVO.getStatus());
			pstmt.setString(8, productVO.getMercom_num());
			pstmt.setString(9, productVO.getCom_num());
			pstmt.executeUpdate();

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
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public List<ProductVO> stoFindbyProductName(String com_name , String sto_num) {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(STO_GET_BY_COM_NAME);

			pstmt.setString(1, "%"+com_name+"%");
			pstmt.setString(2, sto_num);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setCom_num(rs.getString("com_num"));
				productVO.setCom_name(rs.getString("com_name"));
				productVO.setM_price(rs.getInt("m_price"));
				productVO.setL_price(rs.getInt("l_price"));
				productVO.setDiscribe(rs.getString("discribe"));
				productVO.setImg(rs.getBytes("img"));
				productVO.setPt_num(rs.getString("pt_num"));
				productVO.setStatus(rs.getString("status"));
				productVO.setMercom_num(rs.getString("mercom_num"));
				list.add(productVO);
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
		return list;
	}

	@Override
	public List<ProductVO> stoGetAll(String sto_num) {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(STO_GET_ALL);
			
			pstmt.setString(1, sto_num);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setCom_num(rs.getString("com_num"));
				productVO.setSto_num(rs.getString("sto_num"));
				productVO.setCom_name(rs.getString("com_name"));
				productVO.setM_price(rs.getInt("m_price"));
				productVO.setL_price(rs.getInt("l_price"));
				productVO.setDiscribe(rs.getString("discribe"));
				productVO.setImg(rs.getBytes("img"));
				productVO.setPt_num(rs.getString("pt_num"));
				productVO.setStatus(rs.getString("status"));
				productVO.setMercom_num(rs.getString("mercom_num"));
				list.add(productVO);
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
		return list;
	}

	@Override
	public List<ProductVO> getAll() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				productVO = new ProductVO();
				productVO.setCom_num(rs.getString("com_num"));
				productVO.setSto_num(rs.getString("sto_num"));
				productVO.setCom_name(rs.getString("com_name"));
				productVO.setM_price(rs.getInt("m_price"));
				productVO.setL_price(rs.getInt("l_price"));
				productVO.setDiscribe(rs.getString("discribe"));
				productVO.setImg(rs.getBytes("img"));
				productVO.setPt_num(rs.getString("pt_num"));
				productVO.setStatus(rs.getString("status"));
				productVO.setMercom_num(rs.getString("mercom_num"));
				list.add(productVO);	
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
		return list;
	}
	
	@Override
	public ProductVO findByPrimaryKey(String com_num) {
		
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, com_num);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				productVO = new ProductVO();
				productVO.setCom_num(rs.getString("com_num"));
				productVO.setSto_num(rs.getString("sto_num"));
				productVO.setCom_name(rs.getString("com_name"));
				productVO.setM_price(rs.getInt("m_price"));
				productVO.setL_price(rs.getInt("l_price"));
				productVO.setDiscribe(rs.getString("discribe"));
				productVO.setImg(rs.getBytes("img"));
				productVO.setPt_num(rs.getString("pt_num"));
				productVO.setStatus(rs.getString("status"));
				productVO.setMercom_num(rs.getString("mercom_num"));	
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
		return productVO;
	}
}


	
