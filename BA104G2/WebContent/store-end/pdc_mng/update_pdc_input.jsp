<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.merged_commodity.model.*"%>
<jsp:useBean id="pdSvc" scope="request" class="com.product.model.ProductService" />
<jsp:useBean id="mcSvc" scope="request" class="com.merged_commodity.model.MergedCommodityService" />
 <jsp:useBean id="pdcTSvc" scope="request" class="com.product_type.model.ProductTypeService" />	
<% 
	ProductVO productVO = (ProductVO) request.getAttribute("productVO"); 
%>

<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>�ӫ~��ƭק� - update_pdc_input.jsp</title>
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

<table>
	<tr>
		<td><h3>�ӫ~��ƭק�</h3></td>
		<td><h4><a href="<%= request.getContextPath() %>/store-end/pdc_mng/store_select_page.jsp">�^�ӫ~�޲z����</a></h4></td>
	</tr>
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

	<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/pdc_mng/StoPdcMng.do" enctype="multipart/form-data">
	<table>
		<tr>
			<td>���a�s��</td>
			<td><input type="TEXT" name="sto_num" size="45" 
				 value="${productVO.sto_num}" disabled/>
				<input type="hidden" name="sto_num" value="${productVO.sto_num}">
			</td>		
		</tr>
		<tr>
			<td>�ӫ~�s��</td>
			<td><input type="TEXT" name="com_num" size="45" 
				 value="${productVO.com_num}" /></td>
		</tr>
		<tr>
			<td>�ӫ~�W��</td>
			<td><input type="TEXT" name="com_name" size="45" 
				 value="${productVO.com_name}" /></td>
		</tr>
		<tr>
			<td>�p�M�ӫ~����:</td>
			<td><input type="TEXT" name="m_price" size="45"
				 value="${productVO.m_price}"  /></td>
		</tr>
		<tr>
			<td>�j�M�ӫ~����:</td>
			<td><input type="TEXT" name="l_price" size="45"
				 value="${productVO.l_price}"/></td>
		</tr>
		<tr>
			<td>�ӫ~�ԭz:</td>
			<td><input type="text" name="discribe" size="45" 
			value="${productVO.discribe}"></td>
		</tr>
		<tr>
			<td>�ӫ~�Ϥ�:</td>
			<td><img height=200 src="<%=request.getContextPath()%>/DBGifReader4?com_num=${productVO.com_num}"><br><input type="File" name="img" size="45"/></td>
		</tr>	
		<tr>
			<td>�ӫ~���O:${productVO.pt_num}<font color=red><b>*</b></font></td>
			<td>
				<select size="1" name="pt_num">
		         <c:forEach var="pdcTVO" items="${pdcTSvc.all}" > 
		         	<option value="${pdcTVO.pt_num}" ${(pdcTVO.pt_num==productVO.pt_num)? 'selected':'' } >${pdcTVO.pt_name}
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
		<tr>
			<td>�X�֪��A</td>
			<td>
				<c:forEach var="mcVO" items="${mcSvc.getMerList(productVO.mercom_num)}" varStatus="p">
					<span>
					${p.count} -
					${pdSvc.getOneProduct(mcVO.com_num).com_name} 
					�p�M ${pdSvc.getOneProduct(mcVO.com_num).m_price}
					�j�M ${pdSvc.getOneProduct(mcVO.com_num).l_price}</span><br>
				</c:forEach>
			</td>
		</tr>
		
	</table>

	<input type="hidden" name="mercom_num" value="${productVO.mercom_num}">
	<input type="hidden" name="action" value="update">
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