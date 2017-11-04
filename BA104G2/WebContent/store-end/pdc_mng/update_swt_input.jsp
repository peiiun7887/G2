<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sweetness.model.*"%>

<% 
	SweetnessVO sweetnessVO = (SweetnessVO) request.getAttribute("sweetnessVO"); 
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>商品資料修改 - update_pdc_input.jsp</title>
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
		 <h3>商品資料修改 - update_pdc_input.jsp</h3>
		 <h4><a href="<%= request.getContextPath() %>/store-end/pdc_mng/store_select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message.value}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/pdc_mng/StoSwtMng.do">
<table>
	<tr>
		<td>甜度編號</td>
		<td><input type="TEXT" name="sweet_num" size="45" 
			 value="${sweetnessVO.sweet_num}" disabled/>			
		</td>	
	</tr>
	<tr>
		<td>店家編號</td>
		<td><input type="TEXT" name="sto_num" size="45" 
			 value="${sweetnessVO.sto_num}" disabled/>			
		</td>	
	</tr>
	<tr>
		<td>甜度名稱</td>
		<td><input type="TEXT" name="sweet_type" size="45" 
			 value="${sweetnessVO.sweet_type}" /></td>
	</tr>	
	<tr>
		<td>甜度狀態:</td>
		<td>
			<select size="1" name="status">
			<option value="上架" ${(sweetnessVO.status=='上架')? 'selected':'' } >上架
			<option value="下架" ${(sweetnessVO.status=='下架')? 'selected':'' } >下架
			</select>
		</td>
	</tr>
</table>

<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="sweet_num" value="${sweetnessVO.sweet_num}">
<input type="hidden" name="sto_num" value="${sweetnessVO.sto_num}">
<input type="hidden" name="requestURL" value="<%=request.getAttribute("requestURL")%>">
<input type="submit" value="送出修改">
</FORM>
<font color=blue>request.getAttribute("requestURL"):</font> <%=request.getAttribute("requestURL")%><br>
<font color=blue>request.getAttribute("sweetnessVO"):</font> <%=request.getAttribute("sweetnessVO")%><br>
</body>
</html>