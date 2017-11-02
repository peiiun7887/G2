package com.store_chatlog.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoreChatlogDAOJDBC implements StoreChatlogDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA104G2";
	String passwd = "BA104G2";
	
	private static final String Get_ALL = 
			" SELECT sc_num, SC.rpt_snum , clog_name , content , to_char(clog_time,'yyyy-mm-dd') clog_time "
			+ " FROM STORE_CHATLOG SC LEFT JOIN (SELECT RPT_SNUM,STO_NUM FROM REPORT_STORE) RS "
			+ " ON RS.RPT_SNUM = SC.RPT_SNUM WHERE sto_num=?";
		
	@Override
	public List<StoreChatlogVO> getAll(String sto_num) {
		List<StoreChatlogVO> list = new ArrayList<StoreChatlogVO>();
		StoreChatlogVO storeChatlogVO = null;
		
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(Get_ALL);
				
				pstmt.setString(1, sto_num);
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					storeChatlogVO = new StoreChatlogVO();
					storeChatlogVO.setSc_num(rs.getString("sc_num"));
					storeChatlogVO.setRpt_snum(rs.getString("rpt_snum"));
					storeChatlogVO.setClog_name(rs.getString("clog_name"));
					storeChatlogVO.setContent(rs.getString("content"));
					storeChatlogVO.setClog_time(rs.getDate("clog_time"));
					list.add(storeChatlogVO);
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

	public static void main(String[] args){
		
		StoreChatlogDAOJDBC dao = new StoreChatlogDAOJDBC();
		
		List<StoreChatlogVO> list = dao.getAll("ST0000000001");
		System.out.println(list.size());
		for(StoreChatlogVO scVO : list){
			
			System.out.print(scVO.getSc_num() + ",");
			System.out.print(scVO.getRpt_snum() + ",");
			System.out.print(scVO.getClog_name() + ",");
			System.out.print(scVO.getContent() + ",");
			System.out.print(scVO.getClog_time() + ",");
			System.out.println();
		}
	}
}
