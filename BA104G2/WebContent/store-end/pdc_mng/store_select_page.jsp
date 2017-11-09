<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="pdcTSvc" scope="request" class="com.product_type.model.ProductTypeService"/>

<!-- session attibute(sto_num,sto_num) -->

<html>
<head>
<title>店家商品管理</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
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
		
					<table id="table-1">
					   <tr><td><h3>${sto_num}-店家商品管理</h3><h4>( MVC )</h4></td></tr>
					</table>
					
						<form method="get" action="<%= request.getContextPath() %>/pdc_mng/StoPdcMng.do">
						<input type="hidden" name="sto_num"value="${sto_num}">
						<input type="hidden" name="action"value="logoutout">
						<input type="submit" value="登出">
						</form>
					
					
					<h3>資料查詢:</h3>
						
					<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color:red">請修正以下錯誤:</font>
						<ul>
						    <c:forEach var="message" items="${errorMsgs}">
								<li style="color:red">${message.value}</li>
							</c:forEach>
						</ul>
					</c:if>
					
					<ul>
					  <li><a href='stolistAllProduct.jsp'>全部商品列表</a></li>
					  <li><a href='stolistAllSweet.jsp'>全部甜度列表</a></li>
					  <li><a href='stolistAllIce.jsp'>全部冰塊列表</a></li>
					  <li><a href='stolistAllExtra.jsp'>全部加料列表</a></li>
					  
					  <li>
					    <FORM METHOD="post" ACTION="<%= request.getContextPath() %>/pdc_mng/StoPdcMng.do" >
					        <b>輸入商品名稱 (如 奶茶):</b>
					        <input type="text" name="com_name" value="奶茶">
					        <input type="hidden" name="action" value="getName_For_Display">
					        <input type="hidden" name="sto_num" value="${store.sto_num}">
					        <input type="submit" value="送出">
					    </FORM>
					  </li>
					
					
					 
					  <li>
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
					  </li>
					</ul>
					
					
					<h3>新增商品</h3>
					
					<ul>
					  <li><a href='addProduct.jsp'>新增商品</a></li>
					  <li><a href='addSweetness.jsp'>新增甜度</a></li>
					  <li><a href='addIce.jsp'>新增冰度</a></li>
					  <li><a href='addExtra.jsp'>新增加料</a></li>
					  <li><a href='addMerge.jsp'>合併商品</a></li>
					  
					</ul>		
		
		
		
		
	<!--========================== 功能放這邊 =============================================-->			
				</div><!-- class="block-center panelheight" -->			
			</div><!-- class="col-xs-12 col-sm-8 col-sm-offset-3" -->
		</div><!-- div class="row" -->
	</div><!-- div class="container-fluid" -->
	
	<jsp:include page="/store-end/store_foot.jsp" />

</body>
</html>