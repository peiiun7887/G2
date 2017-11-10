<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sweetness.model.*"%>

<% 
	SweetnessVO sweetnessVO = (SweetnessVO) request.getAttribute("sweetnessVO"); 
%>

<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>�קﲢ�׸��</title>

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

<%-- �d��+ListAll���s --%>
<jsp:include page="/store-end/pdc_mng/btn_select.jsp" />

	<table id="table-1">
		<tr><td>
			 <h3>�ӫ~��ƭק� - update_swt_input.jsp</h3>
			 <h4><a href="<%= request.getContextPath() %>/store-end/pdc_mng/store_select_page.jsp">�^����</a></h4>
		</td></tr>
	</table>



<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message.value}</li>
		</c:forEach>
	</ul>
</c:if>

	<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/pdc_mng/StoSwtMng.do">
		<table>
			<tr>
				<td>���׽s��</td>
				<td><input type="TEXT" name="sweet_num" size="45" 
					 value="${sweetnessVO.sweet_num}" disabled/>			
				</td>	
			</tr>
			<tr>
				<td>���a�s��</td>
				<td><input type="TEXT" name="sto_num" size="45" 
					 value="${sweetnessVO.sto_num}" disabled/>			
				</td>	
			</tr>
			<tr>
				<td>���צW��</td>
				<td><input type="TEXT" name="sweet_type" size="45" 
					 value="${sweetnessVO.sweet_type}" /></td>
			</tr>	
			<tr>
				<td>���ת��A:</td>
				<td>
					<select size="1" name="status">
					<option value="�W�[" ${(sweetnessVO.status=='�W�[')? 'selected':'' } >�W�[
					<option value="�U�[" ${(sweetnessVO.status=='�U�[')? 'selected':'' } >�U�[
					</select>
				</td>
			</tr>
		</table>
		
		<input type="hidden" name="action" value="update">
		<input type="hidden" name="sweet_num" value="${sweetnessVO.sweet_num}">
		<input type="hidden" name="sto_num" value="${sweetnessVO.sto_num}">
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
</html>