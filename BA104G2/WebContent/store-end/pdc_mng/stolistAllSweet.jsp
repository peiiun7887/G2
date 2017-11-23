<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sweetness.model.*"%>

<%	
	String sto_num = (String) session.getAttribute("sto_num");
	SweetnessService swtSvc = new SweetnessService();
	List<SweetnessVO> list = swtSvc.getSweetness(sto_num);
    pageContext.setAttribute("list",list);

%>


<html>
<head>
	<title>所有甜度商品</title>
</head>
<body >

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<div class="table-responsive">
	<table class="table">
		<tr>
			<th>甜度編號</th>		
			<th>店家編號</th>
			<th>甜度名稱</th>
			<th>狀態</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
		
		<c:forEach var="swtVO" items="${list}">
			
			<tr ${(swtVO.sweet_num==param.sweet_num)?'bgcolor=#CCCCFF':''}>
				<td>${swtVO.sweet_num}</td>	
				<td>${swtVO.sto_num}</td>
				<td>${swtVO.sweet_type}</td>
				<td>${swtVO.status}</td>
				<td>
				  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pdc_mng/StoSwtMng.do" style="margin-bottom: 0px;">
				     <input type="submit" value="修改">
				     <input type="hidden" name="sweet_num" value="${swtVO.sweet_num}">
				     <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
				     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
				</td>
				<td>
				  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pdc_mng/StoSwtMng.do" style="margin-bottom: 0px;">
				     <input type="submit" value="刪除">
				     <input type="hidden" name="sweet_num"  value="${swtVO.sweet_num}">
				     <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
				     <input type="hidden" name="action" value="delete"></FORM>
				</td>
			</tr>
			
		</c:forEach>
		
	</table>
</div>
</div>
</body>
</html>