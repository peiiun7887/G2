<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ice_list.model.*"%>

<%	
	String sto_num = (String) session.getAttribute("sto_num");
	IceListService iceSvc = new IceListService(); 	
	List<IceListVO> list = iceSvc.getSweetness(sto_num);
    pageContext.setAttribute("list",list);
%>


<html>
<head>
	<title>�Ҧ��B���ӫ~</title>
</head>
<body >

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

	<table>
		<tr>
			<th>�B���s��</th>		
			<th>���a�s��</th>
			<th>�B���W��</th>
			<th>���A</th>
			<th>�ק�</th>
			<th>�R��</th>
		</tr>
		
		<c:forEach var="iceVO" items="${list}">
			
			<tr ${(iceVO.ice_num==param.ice_num)?'bgcolor=#CCCCFF':''}>
				<td>${iceVO.ice_num}</td>	
				<td>${iceVO.sto_num}</td>
				<td>${iceVO.ice_type}</td>
				<td>${iceVO.status}</td>
				<td>
				  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pdc_mng/StoIceMng.do" style="margin-bottom: 0px;">
				     <input type="submit" value="�ק�">
				     <input type="hidden" name="ice_num" value="${iceVO.ice_num}">
				     <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
				     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
				</td>
				<td>
				  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pdc_mng/StoIceMng.do" style="margin-bottom: 0px;">
				     <input type="submit" value="�R��">
				     <input type="hidden" name="ice_num"  value="${iceVO.ice_num}">
				     <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
				     <input type="hidden" name="action" value="delete"></FORM>
				</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>