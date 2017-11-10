<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<jsp:useBean id="pdcTSvc" scope="request" class="com.product_type.model.ProductTypeService"/>

<!-- session attibute(sto_num,sto_num) -->

<html>
<head>
<title>店家商品管理</title>
<style>
	.btn-green-group{
		background:#3C9682; /*main green*/
		color:#FFFFFF;
	}
	.hide{
		display:none;
	}

	table {
		width: 1000px;
		background-color: white;
		margin-top: 5px;
		margin-bottom: 5px;
	}
	table, th, td {
	  border: 1px solid #3C9682;
	}
	th, td {
	  padding: 5px;
	  text-align: center;
	}
	body{
	height:1000px;
	}
	</style>
	
</style>

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
<% 
	String attrName = null;
	Enumeration<String> em = request.getAttributeNames();
	
	while(em.hasMoreElements()){
		attrName = (String) em.nextElement();
		out.println(attrName+" : "+request.getAttribute(attrName).toString());
	}
	if(request.getAttribute("attrName")==null ){
		String getAllPdc = "getAllPdc";
 		request.setAttribute("getAllPdc",getAllPdc);
	}
	

%>
				<div class="page-header">
				   <h3>${sto_num}-店家商品管理</h3>
				
					<form method="get" action="<%= request.getContextPath() %>/pdc_mng/StoPdcMng.do">
					<input type="hidden" name="sto_num"value="${sto_num}">
					<input type="hidden" name="action"value="logoutout">
					<input type="submit" value="測試用登出">
					</form>
				</div>		
						
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color:red">請修正以下錯誤:</font>
					<ul>
					    <c:forEach var="message" items="${errorMsgs}">
							<li style="color:red">${message.value}</li>
						</c:forEach>
					</ul>
				</c:if>
				
				
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
				
			<div id="listAll">
				<% if (request.getAttribute("getAllExt")!=null){
					request.removeAttribute("getAllPdc");
				%>
       				<jsp:include page="stolistAllExtra.jsp" />
				<% } %>	
				
				<% if (request.getAttribute("getAllIce")!=null){ 
					request.removeAttribute("getAllPdc");
				%>
       				<jsp:include page="stolistAllIce.jsp" />
				<% } %>
				
				<% if (request.getAttribute("getAllSwt")!=null ){ 
					request.removeAttribute("getAllPdc");
				%>
       				<jsp:include page="stolistAllSweet.jsp" />
				<% } %>

				<% if (request.getAttribute("getAllPdc")!=null){ %>
       				<jsp:include page="stolistAllProduct.jsp" />
				<% } %>	
				
				<% if (request.getAttribute("stolistAllProduct2")!=null){ 
					request.removeAttribute("getAllPdc");
				%>
       				<jsp:include page="stolistAllProduct2.jsp" />
				<% } %>							
			</div>

<!-- 再說030-->
<div class="hide">
   <FORM METHOD="post" ACTION="StoPdcMng.do">
       <b>選擇商品種類:</b>
       <select size="1" name="pt_name">
         <c:forEach var="pdcTVO" items="${pdcTSvc.all}" > 
          <option value="${pdcTVO.pt_num}">${pdcTVO.pt_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="">
       <input type="submit" value="送出" disabled>
    </FORM>
</div>	
		
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

			$("input[value*='修改']").on('click',function(){
				$("#listAll").addClass("hide");
			});	
		});	
	</script>
</body>
</html>