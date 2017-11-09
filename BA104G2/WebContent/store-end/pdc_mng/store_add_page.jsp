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
					
						<div class=" btn-group" >	
					
						  <a href='addProduct.jsp' class="btn btn-green-group">新增商品</a>
						  <a href='addSweetness.jsp' class="btn btn-green-group">新增甜度</a>
						  <a href='addIce.jsp' class="btn btn-green-group">新增冰度</a>
						  <a href='addExtra.jsp' class="btn btn-green-group">新增加料</a>
						  <a href='addMerge.jsp' class="btn btn-green-group">合併商品</a>
						  
							
						</div>
		
		
		
	<!--========================== 功能放這邊 =============================================-->			
				</div><!-- class="block-center panelheight" -->			
			</div><!-- class="col-xs-12 col-sm-8 col-sm-offset-3" -->
		</div><!-- div class="row" -->
	</div><!-- div class="container-fluid" -->
	
	<jsp:include page="/store-end/store_foot.jsp" />

</body>
</html>