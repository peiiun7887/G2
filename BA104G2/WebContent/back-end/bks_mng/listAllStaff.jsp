<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.backstage_management.model.*"%>


<%
	String bm_no = (String) session.getAttribute("bm_no");
	BackstageManagementService bmSvc = new BackstageManagementService();	
	List<BackstageManagementVO> list = bmSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
	<title>�Ҧ��ӫ~���</title>
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
			<th>�H���s��</th>		
			<th>�W��</th>
			<th>���</th>
			<th>�H�c</th>
			<th>�Ȧ�b��</th>
			<th>�j�Y�K</th>
			<th>�H���b��</th>
			<th>���A</th>
			<th>�ק�</th>
		</tr>
		
	
		<c:forEach var="bmVO" items="${list}" >
		<tr ${(bmVO.bm_no==param.bm_no)?'bgcolor=#CCCCFF':''}>
			<td>${bmVO.bm_no}</td>	
			<td>${bmVO.bm_name}</td>
			<td>${bmVO.bm_number}</td>
			<td>${bmVO.bm_mail}</td>
			<td>${bmVO.bm_banknum}</td>
			<td><img height=50 src="<%=request.getContextPath()%>/BmGifReader?bm_no=${bmVO.bm_no}"></td> 
			<td>${bmVO.bm_num}</td>
			<td>${bmVO.bm_jstatus}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%= request.getContextPath() %>/bks_mng/BksMng.do" style="margin-bottom: 0px">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="bm_no" value="${bmVO.bm_no}">
			      <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
		</tr>
		</c:forEach>		
		
	</table>
</div>
</div>
</body>
</html>