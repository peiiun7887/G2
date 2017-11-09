<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.extra.model.*"%>

<% 
	ExtraVO extraVO = (ExtraVO) request.getAttribute("extraVO"); 
%>

<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>修改加料商品</title>
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
	<tr>
		<td><h3>修改加料商品</h3></td>
		<td><h4><a href="<%= request.getContextPath() %>/store-end/pdc_mng/store_select_page.jsp">回商品管理首頁</a></h4></td>
	</tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message.value}</li>
		</c:forEach>
	</ul>
</c:if>

	<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/pdc_mng/StoExtMng.do">
		<table>
			<tr>
				<td>加料編號</td>
				<td><input type="TEXT" name="ext_num" size="45" 
					 value="${extraVO.ext_num}" disabled/>			
				</td>	
			</tr>
			<tr>
				<td>店家編號</td>
				<td><input type="TEXT" name="sto_num" size="45" 
					 value="${extraVO.sto_num}" disabled/>			
				</td>	
			</tr>
			<tr>
				<td>加料名稱</td>
				<td><input type="TEXT" name="ext_name" size="45" 
					 value="${extraVO.ext_name}" /></td>
			</tr>
			<tr>
				<td>加料金額</td>
				<td><input type="TEXT" name="ext_amount" size="45" 
					 value="${extraVO.ext_amount}" /></td>
			</tr>		
			<tr>
				<td>加料狀態:</td>
				<td>
					<select size="1" name="status">
					<option value="上架" ${(extraVO.status=='上架')? 'selected':'' } >上架
					<option value="下架" ${(extraVO.status=='下架')? 'selected':'' } >下架
					</select>
				</td>
			</tr>
		</table>

		<input type="hidden" name="action" value="update">
		<input type="hidden" name="ext_num" value="${extraVO.ext_num}">
		<input type="hidden" name="sto_num" value="${extraVO.sto_num}">
		<input type="hidden" name="requestURL" value="<%=request.getAttribute("requestURL")%>">
		<input type="submit" value="送出修改">
	</FORM>

</body>
</html>