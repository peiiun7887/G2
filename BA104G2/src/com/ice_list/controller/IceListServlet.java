package com.ice_list.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ice_list.model.*;
import com.sweetness.model.SweetnessService;

public class IceListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("insert".equals(action)){
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs",errorMsgs);
			String requestURL = req.getParameter("requestURL");
			req.setAttribute("requestURL", requestURL); 
			try{
				/************ 1.接收請求參數 -輸入格式處理  ******************/	
				String sto_num = req.getParameter("sto_num");
				String ice_type = req.getParameter("ice_type");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,3}$";
				
				if (ice_type == null || ice_type.trim().length() == 0){
					errorMsgs.put("sweet_type","冰塊名稱：請勿空白");
				} else if (!ice_type.trim().matches(nameReg)) {
					errorMsgs.put("sweet_type","冰塊名稱：只能是中、英文字母、數字和_ , 且長度必需在1到3之間");
				}				
				
				IceListVO iceListVO = new IceListVO();
				iceListVO.setSto_num(sto_num);
				iceListVO.setIce_type(ice_type);
				iceListVO.setStatus("上架");
				//send back if errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("iceListVO", iceListVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/store-end/pdc_mng/addIce.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/************ 2.開始加入資料   ****************************/
				IceListService swtSvc = new IceListService();
				iceListVO = swtSvc.insertIceList(iceListVO);				
				String ice_num = iceListVO.getIce_num();
				
				/************ 3.加入完成,準備轉交(Send the Success view)**/	
				String url = "/store-end/pdc_mng/stolistAllIce.jsp?ice_num="+ice_num;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交stolistAllProduct.jsp
				successView.forward(req, res);				
				
				/************ 其他錯誤處理  ******************************/
			} catch (Exception e) {
				errorMsgs.put("Exception",e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/store-end/pdc_mng/addIce.jsp");
				failureView.forward(req, res);
			}	
		}
		
	}

}
