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
	<title>商品資料修改 - update_pdc_input.jsp</title>
</head>

<body>
	<jsp:include page="/store-end/store_top.jsp" /> <!-- navbar -->
	<!-- 1層大框框 -->
	<div class="container-fluid">
		<div class="row">
	<!-- 2層框左 -->	
			<jsp:include page="/store-end/store_left.jsp" /> <!-- leftSidebar -->
	<!-- 2層框右 -->		
			<div class="col-xs-12 col-sm-8 col-sm-offset-3" >				
				<div class="block-center panelheight">
	<!--========================== 功能放這邊 =============================================-->

<%-- 查詢+ListAll按鈕 --%>
<jsp:include page="/store-end/pdc_mng/btn_select.jsp" />

<table>
	<tr>
		<td><h3>商品資料修改</h3></td>
		<td><h4><a href="<%= request.getContextPath() %>/store-end/pdc_mng/store_select_page.jsp">回商品管理首頁</a></h4></td>
	</tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message.value}</li>
		</c:forEach>
	</ul>
</c:if>

	<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/pdc_mng/StoPdcMng.do" enctype="multipart/form-data">
	<table>
		<tr>
			<td>店家編號</td>
			<td><input type="TEXT" name="sto_num" size="45" 
				 value="${productVO.sto_num}" disabled/>
				<input type="hidden" name="sto_num" value="${productVO.sto_num}">
			</td>		
		</tr>
		<tr>
			<td>商品編號</td>
			<td><input type="TEXT" name="com_num" size="45" 
				 value="${productVO.com_num}" /></td>
		</tr>
		<tr>
			<td>商品名稱</td>
			<td><input type="TEXT" name="com_name" size="45" 
				 value="${productVO.com_name}" /></td>
		</tr>
		<tr>
			<td>小杯商品價位:</td>
			<td><input type="TEXT" name="m_price" size="45"
				 value="${productVO.m_price}"  /></td>
		</tr>
		<tr>
			<td>大杯商品價位:</td>
			<td><input type="TEXT" name="l_price" size="45"
				 value="${productVO.l_price}"/></td>
		</tr>
		<tr>
			<td>商品敘述:</td>
			<td><input type="text" name="discribe" size="45" 
			value="${productVO.discribe}"></td>
		</tr>
		<tr>
			<td>商品圖片:</td>
			<td><img height=200 src="<%=request.getContextPath()%>/DBGifReader4?com_num=${productVO.com_num}"><br><input type="File" name="img" size="45"/></td>
		</tr>	
		<tr>
			<td>商品類別:${productVO.pt_num}<font color=red><b>*</b></font></td>
			<td>
				<select size="1" name="pt_num">
		         <c:forEach var="pdcTVO" items="${pdcTSvc.all}" > 
		         	<option value="${pdcTVO.pt_num}" ${(pdcTVO.pt_num==productVO.pt_num)? 'selected':'' } >${pdcTVO.pt_name}
		         </c:forEach>   
	       		</select>
	       </td>
		</tr>
	
		<tr>
			<td>商品狀態:</td>
			<td>
				<select size="1" name="status">
				<option value="未上架" ${(productVO.status=='未上架')? 'selected':'' } >未上架
				<option value="已上架" ${(productVO.status=='已上架')? 'selected':'' } >已上架
				</select>
			</td>
		</tr>
		<tr>
			<td>合併狀態</td>
			<td>
				<c:forEach var="mcVO" items="${mcSvc.getMerList(productVO.mercom_num)}" varStatus="p">
					<span>
					${p.count} -
					${pdSvc.getOneProduct(mcVO.com_num).com_name} 
					小杯 ${pdSvc.getOneProduct(mcVO.com_num).m_price}
					大杯 ${pdSvc.getOneProduct(mcVO.com_num).l_price}</span><br>
				</c:forEach>
			</td>
		</tr>
		
	</table>

	<input type="hidden" name="mercom_num" value="${productVO.mercom_num}">
	<input type="hidden" name="action" value="update">
	<input type="submit" value="送出修改">
	</FORM>
	
<!--========================== 功能放這邊 =============================================-->			
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