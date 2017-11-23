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
	<title>�Ҧ��[�ưӫ~</title>
</head>
<body>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<div class="table-responsive">
	<table class="table">
		<tr>
			<th>�[�ƽs��</th>		
			<th>���a�s��</th>
			<th>�[�ƦW��</th>
			<th>�[�ƪ��B</th>
			<th>���A</th>
			<th>�ק�</th>
			<th>�R��</th>
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
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="ext_num" value="${extVO.ext_num}">
			     <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pdc_mng/StoExtMng.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="ext_num"  value="${extVO.ext_num}">
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