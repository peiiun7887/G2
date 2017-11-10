<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.extra.model.*"%>

<% 
	ExtraVO extraVO = (ExtraVO) request.getAttribute("extraVO"); 
%>

<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>修改加料商品</title>
	
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


<table>
	<tr>
		<td><h3>修改加料商品</h3></td>
		<td><h4><a href="<%= request.getContextPath() %>/store-end/pdc_mng/store_select_page.jsp">回商品管理首頁</a></h4></td>
	</tr>
</table>

					<div class=" btn-group" >
						<form class="navbar-left input-group" METHOD="post" ACTION="<%= request.getContextPath() %>/store-end/pdc_mng/store_select_page.jsp">
							<input type="submit" value="商品列表" class="btn btn-green">
							<input type="hidden" name="action" value="getAllPdc">
						</form>														
						<form class="navbar-left input-group " METHOD="post" ACTION="<%= request.getContextPath() %>/pdc_mng/StoExtMng.do">
							<input type="submit" value="加料列表" class="btn btn-green">
							<input type="hidden" name="action" value="getAllExt">
						</form>
						<form class="navbar-left input-group" METHOD="post" ACTION="<%= request.getContextPath() %>/pdc_mng/StoIceMng.do">
							<input type="submit" value="冰塊列表" class="btn btn-green">
							<input type="hidden" name="action" value="getAllIce">
						</form>
						<form class="navbar-left input-group" METHOD="post" ACTION="<%= request.getContextPath() %>/pdc_mng/StoSwtMng.do">
							<input type="submit" value="甜度列表" class="btn btn-green">
							<input type="hidden" name="action" value="getAllSwt">
						</form>	
					</div>
					
					<form class="navbar-left input-group" METHOD="post" ACTION="<%= request.getContextPath() %>/pdc_mng/StoPdcMng.do">
						<div class="input-group"> 
					    <input type="text" name="com_name" value="奶茶" class="form-control" placeholder="搜尋商品">
						<input type="hidden" name="action" value="getName_For_Display">
						<input type="hidden" name="sto_num" value="${sto_num}">
						<span class="input-group-btn">
							<input type="submit" value="送出" class="btn btn-green">
					    </span>    
						</div>					
					</form>		
				<hr color=blue>	

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message.value}</li>
		</c:forEach>
	</ul>
</c:if>

	<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/pdc_mng/StoExtMng.do">
		<table>
			<tr>
				<td>加料編號</td>
				<td><input type="TEXT" name="ext_num" size="45" 
					 value="${extraVO.ext_num}" disabled/>			
				</td>	
			</tr>
			<tr>
				<td>店家編號</td>
				<td><input type="TEXT" name="sto_num" size="45" 
					 value="${extraVO.sto_num}" disabled/>			
				</td>	
			</tr>
			<tr>
				<td>加料名稱</td>
				<td><input type="TEXT" name="ext_name" size="45" 
					 value="${extraVO.ext_name}" /></td>
			</tr>
			<tr>
				<td>加料金額</td>
				<td><input type="TEXT" name="ext_amount" size="45" 
					 value="${extraVO.ext_amount}" /></td>
			</tr>		
			<tr>
				<td>加料狀態:</td>
				<td>
					<select size="1" name="status">
					<option value="上架" ${(extraVO.status=='上架')? 'selected':'' } >上架
					<option value="下架" ${(extraVO.status=='下架')? 'selected':'' } >下架
					</select>
				</td>
			</tr>
		</table>

		<input type="hidden" name="action" value="update">
		<input type="hidden" name="ext_num" value="${extraVO.ext_num}">
		<input type="hidden" name="sto_num" value="${extraVO.sto_num}">
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