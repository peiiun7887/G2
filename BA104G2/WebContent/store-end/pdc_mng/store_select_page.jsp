<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="pdcTSvc" scope="request" class="com.product_type.model.ProductTypeService"/>

<!-- session attibute(sto_num,sto_num) -->

<html>
<head>
<title>店家商品管理</title>
<style>
	.btn-green-group{
		background:#3C9682; /*green*/
		color:#FFFFFF;
	}
	.hide{
		display:none;
	}
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
			<div class="col-xs-12 col-sm-8 col-sm-offset-3" bgcolor="#AAAAAA">				
				<div class="block-center panelheight">
	<!--========================== 功能放這邊 =============================================-->
		
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
						  <a href='stolistAllProduct.jsp' class="btn btn-green-group">商品列表</a>
						  <a href='stolistAllSweet.jsp' class="btn btn-green-group">甜度列表</a>
						  <a href='stolistAllIce.jsp' class="btn btn-green-group">冰塊列表</a>
						  <a href='stolistAllExtra.jsp' class="btn btn-green-group">加料列表</a>							
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
					
				

<!-- 有空再寫 -->
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

</body>
</html>