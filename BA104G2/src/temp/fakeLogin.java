package temp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/fakeLogin")
public class fakeLogin extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req, res);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action =req.getParameter("action");
		
		if("loginm".equals(action)){

			String mem_num = req.getParameter("mem_num");
			System.out.println(mem_num);
			HttpSession session = req.getSession();
	
			session.setAttribute("mem_num", mem_num);
			res.sendRedirect(req.getContextPath()+"/front-end/member_top.jsp");	
			return;
		}
		
		if ("logoutm".equals(action)) {

			String mem_num = req.getParameter("mem_num");
			HttpSession session = req.getSession();
			session.invalidate();
			//整個連線拔掉
			res.sendRedirect(req.getContextPath()+"/front-end/member_top.jsp");
		    return;
		}

		
		if("loginin".equals(action)){
			String sto_num = req.getParameter("sto_num");
			String mem_num = req.getParameter("mem_num");
			System.out.println(sto_num+","+mem_num);
			HttpSession session = req.getSession();
			session.setAttribute("sto_num", sto_num);
			session.setAttribute("mem_num", mem_num);
			res.sendRedirect(req.getContextPath()+"/store-end/pdc_mng/store_select_page.jsp");	
			return;
		}
		
		if ("logoutout".equals(action)) {
			String sto_num = req.getParameter("sto_num");
			String mem_num = req.getParameter("mem_num");
			HttpSession session = req.getSession();
			session.invalidate();
			System.out.println(sto_num+"logout");
			//整個連線拔掉
			res.sendRedirect(req.getContextPath()+"/store-end/form.jsp");
		    return;
		}
	
	}

}
