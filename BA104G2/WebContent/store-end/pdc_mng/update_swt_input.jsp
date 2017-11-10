<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sweetness.model.*"%>

<% 
	SweetnessVO sweetnessVO = (SweetnessVO) request.getAttribute("sweetnessVO"); 
%>

<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>修改甜度資料</title>

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

	<table id="table-1">
		<tr><td>
			 <h3>商品資料修改 - update_swt_input.jsp</h3>
			 <h4><a href="<%= request.getContextPath() %>/store-end/pdc_mng/store_select_page.jsp">回首頁</a></h4>
		</td></tr>
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

	<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/pdc_mng/StoSwtMng.do">
		<table>
			<tr>
				<td>甜度編號</td>
				<td><input type="TEXT" name="sweet_num" size="45" 
					 value="${sweetnessVO.sweet_num}" disabled/>			
				</td>	
			</tr>
			<tr>
				<td>店家編號</td>
				<td><input type="TEXT" name="sto_num" size="45" 
					 value="${sweetnessVO.sto_num}" disabled/>			
				</td>	
			</tr>
			<tr>
				<td>甜度名稱</td>
				<td><input type="TEXT" name="sweet_type" size="45" 
					 value="${sweetnessVO.sweet_type}" /></td>
			</tr>	
			<tr>
				<td>甜度狀態:</td>
				<td>
					<select size="1" name="status">
					<option value="上架" ${(sweetnessVO.status=='上架')? 'selected':'' } >上架
					<option value="下架" ${(sweetnessVO.status=='下架')? 'selected':'' } >下架
					</select>
				</td>
			</tr>
		</table>
		
		<input type="hidden" name="action" value="update">
		<input type="hidden" name="sweet_num" value="${sweetnessVO.sweet_num}">
		<input type="hidden" name="sto_num" value="${sweetnessVO.sto_num}">
		<input type="hidden" name="requestURL" value="<%=request.getAttribute("requestURL")%>">
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