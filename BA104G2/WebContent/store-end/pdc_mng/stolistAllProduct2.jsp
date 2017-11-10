<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<jsp:useBean id="pdcTSvc" scope="request" class="com.product_type.model.ProductTypeService" />	
<jsp:useBean id="pdSvc" scope="request" class="com.product.model.ProductService" />
<jsp:useBean id="mcSvc" scope="request" class="com.merged_commodity.model.MergedCommodityService" />
<%
	List<ProductVO> list = (List<ProductVO>) request.getAttribute("stolistAllProduct2");
	pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>店家查商品名稱</title>

</head>

<body >

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message.value}</li>
		</c:forEach>
	</ul>
</c:if>


	<table>
		<tr>
			<th>商品編號</th>		
			<th>商品名稱</th>
			<th>小杯價錢</th>
			<th>大杯價錢</th>
			<th>描述</th>
			<th>圖片</th>
			<th>商品類別</th>
			<th>狀態</th>
			<th>合併狀態</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>

	<c:forEach var="PdcVO" items="${list}" >
		
		<tr>
			<td>${PdcVO.com_num}</td>	
			<td>${PdcVO.com_name}</td>
			<td>${PdcVO.m_price}</td>
			<td>${PdcVO.l_price}</td>
			<td>${PdcVO.discribe}</td>
			<td><img height=50 src="<%=request.getContextPath()%>/DBGifReader4?com_num=${PdcVO.com_num}"></td> 
			 <c:forEach var="pdcTSvc" items="${pdcTSvc.all}" > 
	         	<c:if test="${pdcTSvc.pt_num==PdcVO.pt_num}" var="condition" scope="page">
	         		<td>${pdcTSvc.pt_name}</td>
	         	</c:if>
	         </c:forEach>
			<td>${PdcVO.status}</td>
			<td width=200>				
			<c:forEach var="mcVO" items="${mcSvc.getMerList(PdcVO.mercom_num)}" varStatus="p">
				<span>${p.count} -
				${pdSvc.getOneProduct(mcVO.com_num).com_name} 
				小杯 ${pdSvc.getOneProduct(mcVO.com_num).m_price}
				大杯 ${pdSvc.getOneProduct(mcVO.com_num).l_price}</span>
			</c:forEach>
			</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pdc_mng/StoPdcMng.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="com_num"  value="${PdcVO.com_num}">
			      <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pdc_mng/StoPdcMng.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="com_num"  value="${PdcVO.com_num}">
			      <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
	</table>

</body>
</html>