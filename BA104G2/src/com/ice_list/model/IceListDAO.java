package com.ice_list.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class IceListDAO implements IceListDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA104G2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_ICELIST = "INSERT INTO ICE_LIST STO_NUM=?, ICE_TYPE=?, STATUS=?";
	private static final String UPDATE = "UPDATE ICE_LIST SET STATUS=? WHERE ICE_NUM=?";
	private static final String GET_ICELIST = "SELECT * FROM ICE_LIST WHERE STO_NUM=?";

	@Override
	public void insert(IceListVO iceListVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_ICELIST);

			con.setAutoCommit(false);

			pstmt.setString(1, iceListVO.getSto_num());
			pstmt.setString(2, iceListVO.getIce_type());
			pstmt.setString(3, iceListVO.getStatus());

			pstmt.executeUpdate();

			con.commit();

		} catch (SQLException se) {
			try {
				con.rollback();
				throw new RuntimeException("A database error occured. " + se.getMessage());
			} catch (SQLException see) {
				see.printStackTrace();
			}
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
	public void update(IceListVO iceListVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			con.setAutoCommit(false);

			pstmt.setString(1, iceListVO.getStatus());
			pstmt.setString(2, iceListVO.getIce_num());

			pstmt.executeUpdate();

			con.commit();

		} catch (SQLException se) {
			try {
				con.rollback();
				throw new RuntimeException("A database error occured. " + se.getMessage());
			} catch (SQLException see) {
				see.printStackTrace();
			}
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
	public List<IceListVO> getIceList(IceListVO iceListVO) {
		IceListVO iceVO = null;
		List<IceListVO> iceList = new ArrayList<IceListVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ICELIST);

			pstmt.setString(1, iceListVO.getSto_num());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				iceVO = new IceListVO();
				iceVO.setIce_num(rs.getString("ICE_NUM"));
				iceVO.setIce_type(rs.getString("ICE_TYPE"));
				iceVO.setStatus(rs.getString("STATUS"));
				iceList.add(iceVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return iceList;
	}
}