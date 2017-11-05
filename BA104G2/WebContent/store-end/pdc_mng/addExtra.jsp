<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.extra.model.*"%>

<%
  ExtraVO extraVO = (ExtraVO) request.getAttribute("extraVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�B����Ʒs�W - addIIce.jsp</title>

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
		 <h3>�[�Ƹ�Ʒs�W - addExtra.jsp</h3></td><td>
		 <h4><a href="<%= request.getContextPath() %>/store-end/pdc_mng/store_select_page.jsp">�^����</a></h4>
	</td></tr>
</table>

<h3>��Ʒs�W:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message.value}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/pdc_mng/StoExtMng.do">
<table>
	<tr>
		<td>���a�s��</td>
		<td><input type="TEXT" name="sto_num" size="45" 
			 value="${store.sto_num}" disabled/>			
		</td>		
	</tr>
	<tr>
		<td>�[�ƦW��</td>
		<td><input type="TEXT" name="ext_name" size="45" 
			 value="${extraVO.ext_name}" /></td>
	</tr>
	<tr>
		<td>�[�ƪ��B</td>
		<td><input type="TEXT" name="ext_amount" size="45" 
			 value="${extraVO.ext_amount}" /></td>
	</tr>		
</table>
<br>
<input type="hidden" name="sto_num" value="${store.sto_num}">
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>
</body>

</html>