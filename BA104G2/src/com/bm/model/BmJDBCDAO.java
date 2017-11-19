package com.bm.model;

import java.sql.*;
import java.util.*;


public class BmJDBCDAO implements BmDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "G2";
	String passwd = "G2";
	
	
	private static final String INSERT_STMT = 
			"INSERT INTO BACKSTAGE_MANAGEMENT (bm_no,bm_name,bm_number,bm_mail,bm_banknum,bm_num,bm_pwd,bm_jstatus)"
			+ "VALUES (?,?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = 
			"UPDATE BACKSTAGE_MANAGEMENT SET "
			+ "bm_name=?,bm_number=?,bm_mail=?,bm_banknum=?,bm_num=?,bm_pwd=?,bm_jstatus=? WHERE bm_no=?";
	private static final String DELETE_STMT = 
			"DELETE FROM BACKSTAGE_MANAGEMENT WHERE bm_no=?";
	private static final String GET_ONE_STMT = 
			"SELECT bm_no,bm_name,bm_number,bm_mail,bm_banknum,bm_num,bm_pwd,bm_jstatus "
			+ " FROM BACKSTAGE_MANAGEMENT WHERE bm_no=?";
	private static final String GET_ALL_STMT = 
			"SELECT bm_no,bm_name,bm_number,bm_mail,bm_banknum,bm_num,bm_pwd,bm_jstatus "
			+ "FROM BACKSTAGE_MANAGEMENT ORDER BY bm_no";
	
	@Override
	public void insert(BmVO bmVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, bmVO.getBm_no());
			pstmt.setString(2, bmVO.getBm_name());
			pstmt.setString(3, bmVO.getBm_number());
			pstmt.setString(4, bmVO.getBm_mail());
			pstmt.setString(5, bmVO.getBm_banknum());
			pstmt.setString(6, bmVO.getBm_num());
			pstmt.setString(7, bmVO.getBm_pwd());
			pstmt.setString(8, bmVO.getBm_jstatus());
			//圖片待測試
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
	public void update(BmVO bmVO) {
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
			//圖片待測試
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
	public void delete(String bm_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setString(1,bm_no);
			
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
	public BmVO findbyPrimaryKey(String bm_no) {
		
		BmVO bmVO = null;
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
					bmVO = new BmVO();
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
	public List<BmVO> getAll() {
		List<BmVO> list = new ArrayList<BmVO>();
		BmVO bmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {				
				bmVO = new BmVO();
				bmVO.setBm_no(rs.getString("bm_no"));
				bmVO.setBm_name(rs.getString("bm_name"));
				bmVO.setBm_number(rs.getString("bm_number"));
				bmVO.setBm_mail(rs.getString("bm_mail"));
				bmVO.setBm_banknum(rs.getString("bm_banknum"));
				bmVO.setBm_num(rs.getString("bm_num"));
				bmVO.setBm_pwd(rs.getString("bm_pwd"));
				bmVO.setBm_jstatus(rs.getString("bm_jstatus"));
				list.add(bmVO); // Store the row in the list
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

	public static void main(String[] args) {
		BmJDBCDAO dao = new BmJDBCDAO();
		//INSERT
		BmVO bmVO1 = new BmVO();
		bmVO1.setBm_no("BMtset");
		bmVO1.setBm_name("pyw");
		bmVO1.setBm_number("0912-345-678");
		bmVO1.setBm_mail("123@gg.com");
		bmVO1.setBm_banknum("1234567890");
		bmVO1.setBm_num("bm004");
		bmVO1.setBm_pwd("bm004");
		bmVO1.setBm_jstatus("在職");
		dao.insert(bmVO1);
		
		//UPDATE
		BmVO bmVO2 = new BmVO();
		bmVO2.setBm_no("BM0000000001");
		bmVO2.setBm_name("pyw2");
		bmVO2.setBm_number("0912-345-678");
		bmVO2.setBm_mail("123@gg.com");
		bmVO2.setBm_banknum("1234567890");
		bmVO2.setBm_num("bm004");
		bmVO2.setBm_pwd("bm004");
		bmVO2.setBm_jstatus("在職");
		dao.update(bmVO2);

		//DELEDT
		dao.delete("BMtset");
		
		//SELECT ONE
		BmVO bmVO3 = dao.findbyPrimaryKey("BM0000000001");
		System.out.print(bmVO3.getBm_no() + ",");
		System.out.print(bmVO3.getBm_name() + ",");
		System.out.print(bmVO3.getBm_number() + ",");
		System.out.print(bmVO3.getBm_mail()+ ",");
		System.out.print(bmVO3.getBm_banknum() + ",");
		System.out.print(bmVO3.getBm_num()+ ",");
		System.out.print(bmVO3.getBm_pwd() + ",");
		System.out.println(bmVO3.getBm_jstatus() + ",");
		System.out.println("---------------------");
		
		//SELECT ALL
		List<BmVO> list = dao.getAll();
		for(BmVO aBm :list){
			System.out.print(aBm.getBm_no() + ",");
			System.out.print(aBm.getBm_name() + ",");
			System.out.print(aBm.getBm_number() + ",");
			System.out.print(aBm.getBm_mail()+ ",");
			System.out.print(aBm.getBm_banknum() + ",");
			System.out.print(aBm.getBm_num()+ ",");
			System.out.print(aBm.getBm_pwd() + ",");
			System.out.println(aBm.getBm_jstatus() + ",");
			
		}
		System.out.println("---------ALL---------");
	}
}



