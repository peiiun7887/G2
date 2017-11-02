<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>

<%
  ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>員工資料新增 - addProduct.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
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

<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/pdc_mng/StoPdcMng.do" enctype="multipart/form-data">
<table>
	<tr>
		<td>店家編號</td>
		<td><input type="TEXT" name="sto_num" size="45" 
			 value="${store.sto_num}" disabled/>
			<input type="hidden" name="sto_num" value="${store.sto_num}">
		</td>
		
	</tr>
	<tr>
		<td>商品名稱</td>
		<td><input type="TEXT" name="com_name" size="45" 
			 value="${param.com_name}" /></td>
	</tr>
	<tr>
		<td>小杯商品價位:</td>
		<td><input type="TEXT" name="m_price" size="45"
			 value="${param.m_price}" /></td>
	</tr>
	<tr>
		<td>大杯商品價位:</td>
		<td><input type="TEXT" name="l_price" size="45"
			 value="${param.l_price}" /></td>
	</tr>
	<tr>
		<td>商品敘述:</td>
		<td><input type="text" name="discribe" size="45" 
		value="${param.discribe}"></td>
	</tr>
	<tr>
		<td>商品圖片:</td>
		<td><input type="File" name="img" size="45"/></td>
	</tr>
	
 <jsp:useBean id="pdcTSvc" scope="request" class="com.product_type.model.ProductTypeService" />	
	<tr>
		<td>商品類別編號:<font color=red><b>*</b></font></td>
		<td>
			<select size="1" name="pt_num">
	         <c:forEach var="pdcTVO" items="${pdcTSvc.all}" > 
	         	<option value="${pdcTVO.pt_num}" ${(param.pt_num==pdcTVO.pt_num)? 'selected':'' } >${pdcTVO.pt_name}
	         </c:forEach>   
       		</select>
       </td>
	</tr>
	<tr>
		<td>商品狀態:</td>
		<td>
			<select size="1" name="status">
			<option value="未上架" ${(productVO.status=='未上架')? 'selected':'' } >未上架
			<option value="已上架" ${(productVO.status=='已上架')? 'selected':'' } >已上架
			</select>
		</td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

</html>