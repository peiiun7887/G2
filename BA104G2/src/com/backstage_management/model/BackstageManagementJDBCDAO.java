package com.backstage_management.model;

import java.sql.*;
import java.util.*;


public class BackstageManagementJDBCDAO implements BackstageManagementDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA104G2";
	String passwd = "BA104G2";
	
	
	private static final String INSERT_STMT = 
			"INSERT INTO BACKSTAGE_MANAGEMENT (bm_no,bm_name,bm_number,bm_mail,bm_banknum,bm_num,bm_pwd,bm_jstatus)"
			+ "VALUES ('BM'||LPAD(to_char(SEQ_BM_NO.NEXTVAL),10,'0'),?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = 
			"UPDATE BACKSTAGE_MANAGEMENT SET "
			+ " bm_name=?,bm_number=?,bm_mail=?,bm_banknum=?,bm_num=?,bm_pwd=?,bm_jstatus=? WHERE bm_no=?";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM BACKSTAGE_MANAGEMENT WHERE bm_no=?";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM BACKSTAGE_MANAGEMENT ORDER BY bm_no";
	private static final String CHECK_BM_NUM = "SELECT BM_NUM FROM BACKSTAGE_MANAGEMENT WHERE BM_NUM = ?";
	
	
	@Override
	public String insert(BackstageManagementVO bmVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String bm_no = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String[] col = {"bm_no"};
			pstmt = con.prepareStatement(INSERT_STMT,col);
			
			
			pstmt.setString(1, bmVO.getBm_name());
			pstmt.setString(2, bmVO.getBm_number());
			pstmt.setString(3, bmVO.getBm_mail());
			pstmt.setString(4, bmVO.getBm_banknum());
			pstmt.setString(5, bmVO.getBm_num());
			pstmt.setString(6, bmVO.getBm_pwd());
			pstmt.setString(7, bmVO.getBm_jstatus());
			
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			if (rs.next()) {
				do {
					for (int i = 1; i <= columnCount; i++) {
						bm_no = rs.getString(i);						
					}
				} while (rs.next());
			}			
			rs.close();
			
			
		} catch (ClassNotFoundException e) {	//driver error
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());		
		} catch (SQLException se) {	//SQL error
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally{ 			
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
		return bm_no;
	}

	@Override
	public void update(BackstageManagementVO bmVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);
						
			pstmt.setString(1, bmVO.getBm_name());
			pstmt.setString(2, bmVO.getBm_number());
			pstmt.setString(3, bmVO.getBm_mail());
			pstmt.setString(4, bmVO.getBm_banknum());
			pstmt.setString(5, bmVO.getBm_num());
			pstmt.setString(6, bmVO.getBm_pwd());
			pstmt.setString(7, bmVO.getBm_jstatus());
			pstmt.setString(8, bmVO.getBm_no());
			
			pstmt.executeUpdate();
			
			
		} catch (ClassNotFoundException e) {	//driver error
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());		
		} catch (SQLException se) {	//SQL error
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally{ 			
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
	public BackstageManagementVO findbyPrimaryKey(String bm_no) {
		
		BackstageManagementVO bmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url,userid,passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);
				
				pstmt.setString(1, bm_no);
				
				rs = pstmt.executeQuery();
				while(rs.next()){
					bmVO = new BackstageManagementVO();
					bmVO.setBm_no(rs.getString("bm_no"));
					bmVO.setBm_name(rs.getString("bm_name"));
					bmVO.setBm_number(rs.getString("bm_number"));
					bmVO.setBm_mail(rs.getString("bm_mail"));
					bmVO.setBm_banknum(rs.getString("bm_banknum"));
					bmVO.setBm_num(rs.getString("bm_num"));
					bmVO.setBm_pwd(rs.getString("bm_pwd"));
					bmVO.setBm_jstatus(rs.getString("bm_jstatus"));	
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
		return bmVO;
	}

	@Override
	public List<BackstageManagementVO> getAll() {
		List<BackstageManagementVO> list = new ArrayList<BackstageManagementVO>();
		BackstageManagementVO bmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {				
				bmVO = new BackstageManagementVO();
				bmVO.setBm_no(rs.getString("bm_no"));
				bmVO.setBm_name(rs.getString("bm_name"));
				bmVO.setBm_number(rs.getString("bm_number"));
				bmVO.setBm_mail(rs.getString("bm_mail"));
				bmVO.setBm_banknum(rs.getString("bm_banknum"));
				bmVO.setBm_num(rs.getString("bm_num"));
				bmVO.setBm_pwd(rs.getString("bm_pwd"));
				bmVO.setBm_jstatus(rs.getString("bm_jstatus"));
				list.add(bmVO);
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
	public int checkBm_num(String bm_num){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int existBm_num=0;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(CHECK_BM_NUM);
			
			pstmt.setString(1, bm_num);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				existBm_num+=1;
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
		
		return existBm_num;		
	}

	public static void main(String[] args) {
		BackstageManagementJDBCDAO dao = new BackstageManagementJDBCDAO();
		//INSERT
//		BackstageManagementVO bmVO1 = new BackstageManagementVO();
		
//		bmVO1.setBm_name("pyw");
//		bmVO1.setBm_number("0912-345-678");
//		bmVO1.setBm_mail("123@gg.com");
//		bmVO1.setBm_banknum("1234567890");
//		bmVO1.setBm_num("bm004");
//		bmVO1.setBm_pwd("bm004");
//		bmVO1.setBm_jstatus("在職");
//		dao.insert(bmVO1);
//		
		//UPDATE
//		BackstageManagementVO bmVO2 = new BackstageManagementVO();
//		bmVO2.setBm_no("BM0000000100");
//		bmVO2.setBm_name("pyw2");
//		bmVO2.setBm_number("0912-345-678");
//		bmVO2.setBm_mail("123@gg.com");
//		bmVO2.setBm_banknum("1234567890");
//		bmVO2.setBm_num("bm004");
//		bmVO2.setBm_pwd("bm004");
//		bmVO2.setBm_jstatus("離職");
//		dao.update(bmVO2);

				
		//SELECT ONE
//		BackstageManagementVO bmVO3 = dao.findbyPrimaryKey("BM0000000001");
//		System.out.print(bmVO3.getBm_no() + ",");
//		System.out.print(bmVO3.getBm_name() + ",");
//		System.out.print(bmVO3.getBm_number() + ",");
//		System.out.print(bmVO3.getBm_mail()+ ",");
//		System.out.print(bmVO3.getBm_banknum() + ",");
//		System.out.print(bmVO3.getBm_num()+ ",");
//		System.out.print(bmVO3.getBm_pwd() + ",");
//		System.out.println(bmVO3.getBm_jstatus() + ",");
//		System.out.println("---------------------");
		
		//SELECT ALL
//		List<BackstageManagementVO> list = dao.getAll();
//		for(BackstageManagementVO aBm :list){
//			System.out.print(aBm.getBm_no() + ",");
//			System.out.print(aBm.getBm_name() + ",");
//			System.out.print(aBm.getBm_number() + ",");
//			System.out.print(aBm.getBm_mail()+ ",");
//			System.out.print(aBm.getBm_banknum() + ",");
//			System.out.print(aBm.getBm_num()+ ",");
//			System.out.print(aBm.getBm_pwd() + ",");
//			System.out.println(aBm.getBm_jstatus() + ",");
//			
//		}
		
		//CHECK BM_NUM
		int i = dao.checkBm_num("bm007");
		System.out.println(i+"筆");
		
	}
}



