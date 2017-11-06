<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<jsp:useBean id="store" scope="session" class="com.product.model.ProductVO" />
<jsp:setProperty name="store" property="sto_num" value="ST0000000001"/>
<jsp:useBean id="pdcTSvc" scope="request" class="com.product_type.model.ProductTypeService" />	

<%
	ProductService pdcSvc = new ProductService();
	String str = store.getSto_num();
    List<ProductVO> list = pdcSvc.stoFindAllProduct(str);
    pageContext.setAttribute("list",list);
%>
<%
  ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>資料新增 - addProduct.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>商品資料新增 - addProduct.jsp</h3></td><td>
		 <h4><a href="<%= request.getContextPath() %>/store-end/pdc_mng/store_select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message.value}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="get" ACTION="<%= request.getContextPath() %>/pdc_mng/StoPdcMng.do">
	
<table border=1>
	<tr>
		<th>商品編號</th>		
		<th>商品名稱</th>
		<th>小杯價錢</th>
		<th>大杯價錢</th>
		<th>描述</th>
		<th>圖片</th>
		<th>商品類別</th>
		<th>狀態</th>
		<th>合併</th>
	</tr>
	
	<c:forEach var="PdcVO" items="${list}" >
		
		<tr ${(PdcVO.com_num==param.com_num)?'bgcolor=#CCCCFF':''}>
			<td>${PdcVO.com_num}</td>	
			<td>${PdcVO.com_name}</td>
			<td>${PdcVO.m_price}</td>
			<td>${PdcVO.l_price}</td>
			<td>${PdcVO.discribe}</td>
			<td><img height=100 src="<%=request.getContextPath()%>/DBGifReader4?com_num=${PdcVO.com_num}"></td> 
			<td>${pdcTSvc.getOnePdcT(PdcVO.pt_num).pt_name}</td>
			<td>${PdcVO.status}</td>
			<td><input type="checkbox" name="merge" value="${PdcVO.com_num}"></td>			
		</tr>
	</c:forEach>	
</table>
	<input type="submit" value="MERGE!">
	<input type="hidden" value="${list}">
	<input type="hidden" name="action" value="get_For_merge">
</FORM>

<% if (request.getAttribute("MergeProduct")!=null){ %>
	<jsp:include page="addMerge.jsp" />
<% } %>
</body>

</html>