package com.product.controller;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

public class DBGifReader4 extends HttpServlet {

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			Statement stmt = con.createStatement();
			String com_num = req.getParameter("com_num");
			String com_num2 = new String(com_num.getBytes("ISO-8859-1"),"UTF-8");
			ResultSet rs = stmt.executeQuery(
				"SELECT IMG FROM PRODUCT WHERE COM_NUM ='"+ com_num2 +"'");

			if (rs.next()) {
				InputStream in;
				if(rs.wasNull()){
					in = getServletContext().getResourceAsStream("/img/fin-ayu.png");
					byte[] b = new byte[in.available()];
					in.read(b);
					out.write(b);
					in.close();
				}else{
					in = rs.getBinaryStream("IMG");
					byte[] buf = new  byte[8*1024]; //8k buffer
					int len;
					while((len=in.read(buf)) != -1){
						out.write(buf, 0, len);
					}
					in.close();
				}
			}else{
				InputStream in = getServletContext().getResourceAsStream("/images/nopic.gif");
				System.out.println("===========================");
				byte[] b = new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();				
			}	
		
			rs.close();
			stmt.close();
			
		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream("/img/fin-ayu.png");
			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		}
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA104G2");
			con = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	}

	public void destroy() {
		if(con != null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
