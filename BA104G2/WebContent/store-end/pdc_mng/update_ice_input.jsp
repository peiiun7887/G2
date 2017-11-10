<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ice_list.model.*"%>

<% 
	IceListVO iceListVO = (IceListVO) request.getAttribute("iceListVO"); 
%>

<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>�ק�B�����</title>

</head>

<body>
	<jsp:include page="/store-end/store_top.jsp" /> <!-- navbar -->
	<!-- 1�h�j�خ� -->
	<div class="container-fluid">
		<div class="row">
	<!-- 2�h�إ� -->	
			<jsp:include page="/store-end/store_left.jsp" /> <!-- leftSidebar -->
	<!-- 2�h�إk -->		
			<div class="col-xs-12 col-sm-8 col-sm-offset-3" >				
				<div class="block-center panelheight">
	<!--========================== �\���o�� =============================================-->


	<table>
		<tr>
			<td><h3>�ק�B�����</h3></td>
			<td><h4><a href="<%= request.getContextPath() %>/store-end/pdc_mng/store_select_page.jsp">�^�ӫ~�޲z����</a></h4></td>
		</tr>
	</table>
	
					<div class=" btn-group" >
						<form class="navbar-left input-group" METHOD="post" ACTION="<%= request.getContextPath() %>/store-end/pdc_mng/store_select_page.jsp">
							<input type="submit" value="�ӫ~�C��" class="btn btn-green">
							<input type="hidden" name="action" value="getAllPdc">
						</form>														
						<form class="navbar-left input-group " METHOD="post" ACTION="<%= request.getContextPath() %>/pdc_mng/StoExtMng.do">
							<input type="submit" value="�[�ƦC��" class="btn btn-green">
							<input type="hidden" name="action" value="getAllExt">
						</form>
						<form class="navbar-left input-group" METHOD="post" ACTION="<%= request.getContextPath() %>/pdc_mng/StoIceMng.do">
							<input type="submit" value="�B���C��" class="btn btn-green">
							<input type="hidden" name="action" value="getAllIce">
						</form>
						<form class="navbar-left input-group" METHOD="post" ACTION="<%= request.getContextPath() %>/pdc_mng/StoSwtMng.do">
							<input type="submit" value="���צC��" class="btn btn-green">
							<input type="hidden" name="action" value="getAllSwt">
						</form>	
					</div>
					
					<form class="navbar-left input-group" METHOD="post" ACTION="<%= request.getContextPath() %>/pdc_mng/StoPdcMng.do">
						<div class="input-group"> 
					    <input type="text" name="com_name" value="����" class="form-control" placeholder="�j�M�ӫ~">
						<input type="hidden" name="action" value="getName_For_Display">
						<input type="hidden" name="sto_num" value="${sto_num}">
						<span class="input-group-btn">
							<input type="submit" value="�e�X" class="btn btn-green">
					    </span>    
						</div>					
					</form>		
				<hr color=blue>	



<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message.value}</li>
		</c:forEach>
	</ul>
</c:if>

	<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/pdc_mng/StoIceMng.do">
		<table>
			<tr>
				<td>�B���s��</td>
				<td><input type="TEXT" name="ice_num" size="45" 
					 value="${iceListVO.ice_num}" disabled/>			
				</td>	
			</tr>
			<tr>
				<td>���a�s��</td>
				<td><input type="TEXT" name="sto_num" size="45" 
					 value="${iceListVO.sto_num}" disabled/>			
				</td>	
			</tr>
			<tr>
				<td>�B���W��</td>
				<td><input type="TEXT" name="ice_type" size="45" 
					 value="${iceListVO.ice_type}" /></td>
			</tr>	
			<tr>
				<td>�B�����A:</td>
				<td>
					<select size="1" name="status">
					<option value="�W�[" ${(iceListVO.status=='�W�[')? 'selected':'' } >�W�[
					<option value="�U�[" ${(iceListVO.status=='�U�[')? 'selected':'' } >�U�[
					</select>
				</td>
			</tr>
		</table>

	
		<input type="hidden" name="action" value="update">
		<input type="hidden" name="ice_num" value="${iceListVO.ice_num}">
		<input type="hidden" name="sto_num" value="${iceListVO.sto_num}">
		<input type="hidden" name="requestURL" value="<%=request.getAttribute("requestURL")%>">
		<input type="submit" value="�e�X�ק�">
	</FORM>

<!--========================== �\���o�� =============================================-->			
				</div><!-- class="block-center panelheight" -->			
			</div><!-- class="col-xs-12 col-sm-8 col-sm-offset-3" -->
		</div><!-- div class="row" -->
	</div><!-- div class="container-fluid" -->
	
	<jsp:include page="/store-end/store_foot.jsp" />

	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script>
		$(document).ready(function () {

		});	
	</script>
</body>