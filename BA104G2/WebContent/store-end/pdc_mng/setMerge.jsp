<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<jsp:useBean id="store" scope="session" class="com.product.model.ProductVO" />
<jsp:setProperty name="store" property="sto_num" value="ST0000000001"/>
<jsp:useBean id="pdcTSvc" scope="request" class="com.product_type.model.ProductTypeService" />	

<%
	ProductService pdcSvc = new ProductService();
	String str = store.getSto_num();
    List<ProductVO> list = pdcSvc.stoFindAllProduct(str);
    pageContext.setAttribute("list",list);
%>
<%
  ProductVO productVO = (ProductVO) request.getAttribute("productVO");
  List<String> ckList = (List<String>) request.getAttribute("ckList");	
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>資料新增 - addProduct.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
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

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }

</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>商品資料合併 - mergeProduct.jsp</h3></td><td>
		 <h4><a href="<%= request.getContextPath() %>/store-end/pdc_mng/store_select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>


<button id="getcheckbox"><a href="#Info">MERGE PRODUCE</a></button>
<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/pdc_mng/StoPdcMng.do"  enctype="multipart/form-data">
	
<table border=1>
	<tr>
		<th>合併</th>
		<th>商品編號</th>		
		<th>商品名稱</th>
		<th>小杯價錢</th>
		<th>大杯價錢</th>
		<th>描述</th>
		<th>圖片</th>
		<th>商品類別</th>
		<th>狀態</th>
		
	</tr>
	
	
	
	<c:forEach var="PdcVO" items="${list}" >
		<tr ${(PdcVO.com_num==param.com_num)?'bgcolor=#CCCCFF':''}>
			<td><input type="checkbox" name="checkbox" value="${PdcVO.com_num}" ></td>
			<td>${PdcVO.com_num}</td>	
			<td class="com_name">${PdcVO.com_name}</td>
			<td class="m_price">${PdcVO.m_price}</td>
			<td class="l_price">${PdcVO.l_price}</td>
			<td>${PdcVO.discribe}</td>
			<td><img height=100 src="<%=request.getContextPath()%>/DBGifReader4?com_num=${PdcVO.com_num}"></td> 
			<td>${pdcTSvc.getOnePdcT(PdcVO.pt_num).pt_name}</td>
			<td>${PdcVO.status}</td>						
		</tr>
	</c:forEach>	
</table>

<hr>
<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message.value}</li>
		</c:forEach>
	</ul>
</c:if>
			
<table>
		<tr><td>商品名稱</td><td><input type="text" name="com_name" value="${productVO.com_name}"></td></tr>
		<tr><td>小杯價錢</td><td><input type="text" name="m_price" value="${productVO.m_price}"></td></tr>
		<tr><td>大杯價錢</td><td><input type="text" name="l_price" value="${productVO.l_price}"></td></tr>
		<tr><td>描述</td><td><input type="text" name="discribe" value="${productVO.discribe}"></td></tr>
		<tr><td>圖片</td><td><input type="file" name="img" ></td></tr>
		
		<tr>
			<td>商品類別編號:<font color=red><b>*</b></font></td>
			<td>
				<select size="1" name="pt_num">
		         <c:forEach var="pdcTVO" items="${pdcTSvc.all}" > 
		         	<option value="${pdcTVO.pt_num}" ${(productVO.pt_num==pdcTVO.pt_num)? 'selected':'' } >${pdcTVO.pt_name}
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
</table>	


<input type="submit" value="送出合併商品">
<input type="hidden" name="sto_num" value="${store.sto_num}">
<input type="hidden" name="action" value="insert_merge">
<a id="Info">	</a>		
</FORM>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
var add="";
var mprice,lprice;
$(document).ready(function () {
	

	$('#getcheckbox').on('click',function(){
		var pname="";
		var m_price=0;
		var l_price=0;
		$("input[type=checkbox]:checked").each(function(i){
		 pname = pname+$(this).parent().siblings("td.com_name").text()+"_";
		 m_price = m_price+parseInt($(this).parent().siblings("td.m_price").text());
		 l_price = l_price+parseInt($(this).parent().siblings("td.l_price").text());
		})
	
	console.log(pname);console.log(m_price);console.log(l_price);
	
		if(pname.length>0){
	 		pname = pname.substr(0,pname.length-1);
		}
	console.log("final:"+pname);console.log("final:"+m_price);console.log("final:"+l_price);
	
		//塞值進去下面的FORM
		$("input[name=com_name]").val(pname);
		$("input[name=m_price]").val(m_price);
		$("input[name=l_price]").val(l_price);
	});
	
	

});
</script>
</body>

</html>