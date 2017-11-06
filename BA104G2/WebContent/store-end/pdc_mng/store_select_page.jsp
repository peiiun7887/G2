<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="store" scope="session" class="com.product.model.ProductVO" />
<jsp:setProperty name="store" property="sto_num" value="ST0000000001"/>

<html>
<head>
<title>Stroe_Product_management: Home</title>

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
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>Stroe_Product_management: Home</h3><h4>( MVC )</h4></td></tr>
</table>



<p>This is the Home page for Stroe_Product_management: ${store.sto_num}</p>

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
  <li><a href='stolistAllProduct.jsp'>List</a> my product.  <br><br></li>
  <li><a href='stolistAllSweet.jsp'>List</a> my sweet.  <br><br></li>
  <li><a href='stolistAllIce.jsp'>List</a> my ice.  <br><br></li>
  <li><a href='stolistAllExtra.jsp'>List</a> my extra.  <br><br></li>
  
  <li>
    <FORM METHOD="post" ACTION="<%= request.getContextPath() %>/pdc_mng/StoPdcMng.do" >
        <b>輸入商品名稱 (如 奶茶):</b>
        <input type="text" name="com_name" value="奶茶">
        <input type="hidden" name="action" value="getName_For_Display">
        <input type="hidden" name="sto_num" value="${store.sto_num}">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="pdcTSvc" scope="request" class="com.product_type.model.ProductTypeService"/>
 
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
  <li><a href='chooseMerge.jsp'>合併商品</a></li>
  
</ul>

</body>
</html>