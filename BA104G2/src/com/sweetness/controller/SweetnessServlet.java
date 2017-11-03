package com.sweetness.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sweetness.model.*;

public class SweetnessServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action =req.getParameter("action");
		
		if ("insert".equals(action)){
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs",errorMsgs);
			
			try {
				/************ 1.接收請求參數 -輸入格式處理  ******************/					
				String sto_num = req.getParameter("sto_num");
				System.out.println();
				String sweet_type = req.getParameter("sweet_type");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,3}$";
				if (sweet_type == null || sweet_type.trim().length() == 0){
					errorMsgs.put("sweet_type","甜度名稱：請勿空白");
				} else if (!sweet_type.trim().matches(nameReg)) {
					errorMsgs.put("sweet_type","甜度名稱：只能是中、英文字母、數字和_ , 且長度必需在1到3之間");
				}				
				System.out.println("sweet insert :" +sto_num+","+sweet_type);
				
				SweetnessVO sweetnessVO = new SweetnessVO();
				sweetnessVO.setSto_num(sto_num);
				sweetnessVO.setSto_num(sto_num);
				sweetnessVO.setSweet_type(sweet_type);
				System.out.println(errorMsgs.toString());
				//send back if errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("sweetnessVO", sweetnessVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/store-end/pdc_mng/addSweetness.jsp");
					failureView.forward(req, res);
					return;
				}
				/************ 2.開始加入資料   ****************************/
				SweetnessService swtSvc = new SweetnessService();
				swtSvc.insertSweetness(sto_num, sweet_type);				
				
				/************ 3.加入完成,準備轉交(Send the Success view)**/	
				String url = "";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交stolistAllProduct.jsp
				successView.forward(req, res);				
				
				/************ 其他錯誤處理  ******************************/
			} catch (Exception e) {
				errorMsgs.put("Exception",e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/store-end/pdc_mng/addSweetness.jsp");
				failureView.forward(req, res);
			}		
		
		}
		
		
		
	
	}
}
