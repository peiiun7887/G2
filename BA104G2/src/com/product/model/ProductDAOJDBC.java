package com.product.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOJDBC implements ProductDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA104G2";
	String passwd = "BA104G2";
	
	private static final String INSERT_STMT = 
			"INSERT INTO PRODUCT (sto_num,com_name,m_price,l_price,discribe,img,pt_num,status,mercom_num,com_num)"
			+ " VALUES (?,?,?,?,?,?,?,?,?,'CN'||LPAD(to_char(SEQ_PRODUCT_NUM.NEXTVAL),10,'0'))";
	private static final String UPDATE_STMT = 
			"UPDATE PRODUCT SET "
			+ "com_name=?,m_price=?,l_price=?,discribe=?,img=?,pt_num=?,status=?,mercom_num=? WHERE com_num=? ";
	private static final String GET_ALL_STMT=
			"SELECT * FROM PRODUCT";	
	private static final String GET_ONE_STMT=
			"SELECT com_num,sto_num,com_name,m_price,l_price,discribe,img,pt_num,status,mercom_num "
			+ " FROM PRODUCT WHERE com_num=? ";
	private static final String STO_GET_BY_COM_NAME = //店家依名稱查商品
			"SELECT com_num,com_name,m_price,l_price,discribe,img,pt_num,status,mercom_num"
			+ " FROM (SELECT * FROM PRODUCT WHERE status<>'刪除') WHERE com_name LIKE ? AND sto_num=?";
	private static final String STO_GET_ALL = 	//店家查所有商品
			"SELECT com_num,com_name,m_price,l_price,discribe,img,pt_num,status,mercom_num"
			+ " FROM (SELECT * FROM PRODUCT WHERE status<>'刪除') WHERE sto_num=? ORDER BY com_num  ";
	
	@Override
	public String insert(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String com_num = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			pstmt.setString(10, productVO.getCom_num());
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			if (rs.next()) {
				do {
					for (int i = 1; i <= columnCount; i++) {
						com_num = rs.getString(i);						
					}
				} while (rs.next());
			}			
			rs.close();
						
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
		return com_num;
	}

	@Override
	public void update(ProductVO productVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());		
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(STO_GET_BY_COM_NAME);

			pstmt.setString(1, "%"+com_name+"%");
			pstmt.setString(2, sto_num);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setCom_num(rs.getString("com_num"));
				productVO.setCom_name(rs.getString("com_name"));
				productVO.setM_price(rs.getDouble("m_price"));
				productVO.setL_price(rs.getDouble("l_price"));
				productVO.setDiscribe(rs.getString("discribe"));
				productVO.setImg(rs.getBytes("img"));
				productVO.setPt_num(rs.getString("pt_num"));
				productVO.setStatus(rs.getString("status"));
				productVO.setMercom_num(rs.getString("mercom_num"));
				list.add(productVO);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(STO_GET_ALL);
			
			pstmt.setString(1, sto_num);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setCom_num(rs.getString("com_num"));
				productVO.setCom_name(rs.getString("com_name"));
				productVO.setM_price(rs.getDouble("m_price"));
				productVO.setL_price(rs.getDouble("l_price"));
				productVO.setDiscribe(rs.getString("discribe"));
				productVO.setImg(rs.getBytes("img"));
				productVO.setPt_num(rs.getString("pt_num"));
				productVO.setStatus(rs.getString("status"));
				productVO.setMercom_num(rs.getString("mercom_num"));
				list.add(productVO);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				productVO = new ProductVO();
				productVO.setCom_num(rs.getString("com_num"));
				productVO.setSto_num(rs.getString("sto_num"));
				productVO.setCom_name(rs.getString("com_name"));
				productVO.setM_price(rs.getDouble("m_price"));
				productVO.setL_price(rs.getDouble("l_price"));
				productVO.setDiscribe(rs.getString("discribe"));
				productVO.setImg(rs.getBytes("img"));
				productVO.setPt_num(rs.getString("pt_num"));
				productVO.setStatus(rs.getString("status"));
				productVO.setMercom_num(rs.getString("mercom_num"));
				list.add(productVO);	
			}
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, com_num);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				productVO = new ProductVO();
				productVO.setCom_num(rs.getString("com_num"));
				productVO.setSto_num(rs.getString("sto_num"));
				productVO.setCom_name(rs.getString("com_name"));
				productVO.setM_price(rs.getDouble("m_price"));
				productVO.setL_price(rs.getDouble("l_price"));
				productVO.setDiscribe(rs.getString("discribe"));
				productVO.setImg(rs.getBytes("img"));
				productVO.setPt_num(rs.getString("pt_num"));
				productVO.setStatus(rs.getString("status"));
				productVO.setMercom_num(rs.getString("mercom_num"));	
			}
			
		} catch (ClassNotFoundException e) {
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
		return productVO;
	}
	
	
	public static void main(String[] args) throws IOException {	
		
		String picName = "WebContent/img/cute.png";
		File pic = new File(picName);
		byte[] buffer = Files.readAllBytes(pic.toPath());

		ProductDAOJDBC dao = new ProductDAOJDBC();
		//findbyprimaryKey
//		ProductVO pName = dao.findByPrimaryKey("CN0000000008");
//		System.out.print(pName.getCom_num()+" , ");
//		System.out.print(pName.getSto_num()+" , ");
//		System.out.print(pName.getCom_name()+" , ");
//		System.out.print(pName.getM_price()+" , ");
//		System.out.print(pName.getL_price()+" , ");
//		System.out.print(pName.getDiscribe()+" , ");
//		System.out.print(pName.getImg()+" , ");
//		System.out.print(pName.getPt_num()+" , ");
//		System.out.print(pName.getStatus()+" , ");
//		System.out.print(pName.getMercom_num()+" , ");
//		System.out.println();

		//insert
//		ProductVO productVO1 = new ProductVO();
//		productVO1.setSto_num("ST0000000003");
//		productVO1.setCom_name("");
//		productVO1.setM_price(new Double(50));
//		productVO1.setL_price(new Double(60));
//		productVO1.setDiscribe("");
//		productVO1.setImg(buffer);
//		productVO1.setPt_num("PT003");
//		productVO1.setStatus("123");
//		productVO1.setMercom_num("");
//		dao.insert(productVO1);
		
		//update
//		ProductVO productVO2 = new ProductVO();
//		productVO2.setCom_name("aaa");
//		productVO2.setM_price(new Double(50));
//		productVO2.setL_price(new Double(60));
//		productVO2.setDiscribe("aaa");
//		productVO2.setImg(buffer);
//		productVO2.setPt_num("PT003");
//		productVO2.setStatus("未上架");
//		productVO2.setMercom_num("");
//		productVO2.setCom_num("CN0000000001");
//		dao.update(productVO2);
		
		
		List<ProductVO> list1 = dao.stoFindbyProductName("奶", "ST0000000003");
		System.out.println(list1.size());
		for(ProductVO pName : list1){
			System.out.print(pName.getCom_num()+" , ");
			System.out.print(pName.getCom_name()+" , ");
			System.out.print(pName.getM_price()+" , ");
			System.out.print(pName.getL_price()+" , ");
			System.out.print(pName.getDiscribe()+" , ");
			System.out.println(pName.getImg()+" , ");
			System.out.print(pName.getPt_num()+" , ");
			System.out.print(pName.getStatus()+" , ");
			System.out.print(pName.getMercom_num()+" , ");
			System.out.println();			
		}
		
//		
//		List<ProductVO> list2 = dao.stoGetAll("ST0000000003");
//		System.out.println(list2.size());
//		for(ProductVO pName : list2){
//			System.out.print(pName.getCom_num()+" , ");
//			System.out.print(pName.getCom_name()+" , ");
//			System.out.print(pName.getM_price()+" , ");
//			System.out.print(pName.getL_price()+" , ");
//			System.out.print(pName.getDiscribe()+" , ");
//			System.out.print(pName.getImg()+" , ");
//			System.out.print(pName.getPt_num()+" , ");
//			System.out.print(pName.getStatus()+" , ");
//			System.out.print(pName.getMercom_num()+" , ");
//			System.out.println();			
//		}
		
//		List<ProductVO> list = dao.getAll();
//		System.out.println(list.size());
//		for(ProductVO pName : list){
//			System.out.print(pName.getCom_num()+" , ");
//			System.out.println(pName.getSto_num()+" , ");
//			System.out.print(pName.getCom_name()+" , ");
//			System.out.print(pName.getM_price()+" , ");
//			System.out.print(pName.getL_price()+" , ");
//			System.out.print(pName.getDiscribe()+" , ");
//			System.out.print(pName.getImg()+" , ");
//			System.out.print(pName.getPt_num()+" , ");
//			System.out.print(pName.getStatus()+" , ");
//			System.out.print(pName.getMercom_num()+" , ");
//			System.out.println();			
//		}
	}

	

	
}

