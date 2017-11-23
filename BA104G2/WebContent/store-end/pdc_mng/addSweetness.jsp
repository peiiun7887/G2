<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sweetness.model.*"%>

<%
	SweetnessVO sweetnessVO = (SweetnessVO) request.getAttribute("sweetnessVO");
	session.setAttribute("addform","permit");	//從add頁面來得給個通行證
%>

<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>新增甜度</title>

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

 <h3>店家商品管理</h3>

<%-- 查詢+ListAll按鈕 --%>
<jsp:include page="/store-end/pdc_mng/btn_select.jsp" />

	<table id="table-1">
		<tr>
			<td><h3>新增甜度</h3></td>
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

	<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/pdc_mng/StoSwtMng.do">
		<table>
			<tr>
				<td>店家編號</td>
				<td><input type="TEXT" name="sto_num" size="45" 
					 value="${sto_num}" disabled/>			
				</td>
				
			</tr>
			<tr>
				<td>甜度名稱</td>
				<td><input type="TEXT" name="sweet_type" size="45" 
					 value="${sweetnessVO.sweet_type}" /></td>
			</tr>	
		</table>

		<input type="hidden" name="sto_num" value="${sto_num}">
		<input type="hidden" name="action" value="insert">
		<input type="submit" value="送出新增">
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