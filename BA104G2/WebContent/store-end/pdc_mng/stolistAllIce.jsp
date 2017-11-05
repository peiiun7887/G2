<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ice_list.model.*"%>
<jsp:useBean id="store" scope="session" class="com.product.model.ProductVO" />
<jsp:setProperty name="store" property="sto_num" value="ST0000000001"/>
<%	
 //	Object storeProfileVO=(String) session.getAttribute("storeProfileVO"); //�qsession���X���a�s��
	IceListService iceSvc = new IceListService();
 	String str = store.getSto_num();
	List<IceListVO> list = iceSvc.getSweetness(str);
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>���a�Ҧ��B���ӫ~ - stolistAllSweet.jsp</title>

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

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		${store.sto_num}<h3>�Ҧ��ӫ~��� - stoListAllIce.jsp</h3> 
		 <h4><a href="<%= request.getContextPath() %>/store-end/pdc_mng/store_select_page.jsp">�^����</a></h4>
	</td></tr>
</table>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

list size: <%= list.size() %>

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
<br>�����������|:<br><b>
   <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br>
   <font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%> </b>
</body>
</html>