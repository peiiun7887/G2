<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.extra.model.*"%>

<%	
	String sto_num = (String) session.getAttribute("sto_num");
	ExtraService extSvc = new ExtraService();
	List<ExtraVO> list = extSvc.getExtras(sto_num);
    pageContext.setAttribute("list",list);
%>


<html>
<head>
	<title>所有加料商品</title>
	
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
<body>

	<table id="table-1">
		<tr>
			<td><h3>所有加料商品</h3></td>
			<td><h4><a href="<%= request.getContextPath() %>/store-end/pdc_mng/store_select_page.jsp">回商品管理首頁</a></h4></td>
		</tr>
	</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

	<table>
		<tr>
			<th>加料編號</th>		
			<th>店家編號</th>
			<th>加料名稱</th>
			<th>加料金額</th>
			<th>狀態</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
		
	<c:forEach var="extVO" items="${list}">
			
		<tr ${(extVO.ext_num==param.ext_num)?'bgcolor=#CCCCFF':''}>
			<td>${extVO.ext_num}</td>	
			<td>${extVO.sto_num}</td>
			<td>${extVO.ext_name}</td>
			<td>${extVO.ext_amount}</td>
			<td>${extVO.status}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pdc_mng/StoExtMng.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="ext_num" value="${extVO.ext_num}">
			     <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pdc_mng/StoExtMng.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="ext_num"  value="${extVO.ext_num}">
			     <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
		
	</c:forEach>
	
	</table>

</body>
</html>