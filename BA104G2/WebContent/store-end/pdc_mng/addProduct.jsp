<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>

<%
  ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>���u��Ʒs�W - addProduct.jsp</title>

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
		 <h3>�ӫ~��Ʒs�W - addProduct.jsp</h3></td><td>
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

<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/pdc_mng/StoPdcMng.do" enctype="multipart/form-data">
<table>
	<tr>
		<td>���a�s��</td>
		<td><input type="TEXT" name="sto_num" size="45" 
			 value="${store.sto_num}" disabled/>
			<input type="hidden" name="sto_num" value="${store.sto_num}">
		</td>
		
	</tr>
	<tr>
		<td>�ӫ~�W��</td>
		<td><input type="TEXT" name="com_name" size="45" 
			 value="${param.com_name}" /></td>
	</tr>
	<tr>
		<td>�p�M�ӫ~����:</td>
		<td><input type="TEXT" name="m_price" size="45"
			 value="${param.m_price}" /></td>
	</tr>
	<tr>
		<td>�j�M�ӫ~����:</td>
		<td><input type="TEXT" name="l_price" size="45"
			 value="${param.l_price}" /></td>
	</tr>
	<tr>
		<td>�ӫ~�ԭz:</td>
		<td><input type="text" name="discribe" size="45" 
		value="${param.discribe}"></td>
	</tr>
	<tr>
		<td>�ӫ~�Ϥ�:</td>
		<td><input type="File" name="img" size="45"/></td>
	</tr>
	
 <jsp:useBean id="pdcTSvc" scope="request" class="com.product_type.model.ProductTypeService" />	
	<tr>
		<td>�ӫ~���O�s��:<font color=red><b>*</b></font></td>
		<td>
			<select size="1" name="pt_num">
	         <c:forEach var="pdcTVO" items="${pdcTSvc.all}" > 
	         	<option value="${pdcTVO.pt_num}" ${(param.pt_num==pdcTVO.pt_num)? 'selected':'' } >${pdcTVO.pt_name}
	         </c:forEach>   
       		</select>
       </td>
	</tr>
	<tr>
		<td>�ӫ~���A:</td>
		<td>
			<select size="1" name="status">
			<option value="���W�[" ${(productVO.status=='���W�[')? 'selected':'' } >���W�[
			<option value="�w�W�[" ${(productVO.status=='�w�W�[')? 'selected':'' } >�w�W�[
			</select>
		</td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>
</body>

</html>