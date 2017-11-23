<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<jsp:useBean id="pdcTSvc" scope="request" class="com.product_type.model.ProductTypeService" />	
<jsp:useBean id="pdSvc" scope="request" class="com.product.model.ProductService" />
<jsp:useBean id="mcSvc" scope="request" class="com.merged_commodity.model.MergedCommodityService" />

<%
	String sto_num = (String) session.getAttribute("sto_num");
	ProductService pdcSvc = new ProductService();
    List<ProductVO> list = pdcSvc.stoFindAllProduct(sto_num);
    pageContext.setAttribute("list",list);
    ProductVO productVO = (ProductVO) request.getAttribute("productVO");  	
	session.setAttribute("addform","permit");	//從add頁面來得給個通行證

%>


<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>合併商品資料新增 - addProduct.jsp</title>

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
	<tr>
		<td><h3>商品資料合併</h3></td>		
	</tr>
</table>

<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/pdc_mng/StoPdcMng.do"  enctype="multipart/form-data">

	<div id="pdclist" class="table-responsive">
		<table class="table">
			<tr>
				<th><input type="checkbox" name="selectall"></th>
				<th>商品編號</th>		
				<th>商品名稱</th>
				<th>小杯價錢</th>
				<th>大杯價錢</th>
				<th>描述</th>
				<th>圖片</th>
				<th>商品類別</th>
				<th>狀態</th>
				<th>合併狀態</th>		
			</tr>		
			
		<c:forEach var="PdcVO" items="${list}" >
			<tr ${(PdcVO.com_num==param.com_num)?'bgcolor=#CCCCFF':''}>					
				<td>					
					<input type="checkbox" name="checkbox" value="${PdcVO.com_num}" 
						<c:forEach var="ckList" items="${ckList}">
							${( ckList==PdcVO.com_num ) ? 'checked':'' } 
						</c:forEach>
							${( PdcVO.mercom_num==null) ? '':'disabled'}
					>
				</td>
				<td>${PdcVO.com_num}</td>	
				<td class="com_name">${PdcVO.com_name}</td>
				<td class="m_price">${PdcVO.m_price}</td>
				<td class="l_price">${PdcVO.l_price}</td>
				<td>${PdcVO.discribe}</td>
				<td><img height=50 src="<%=request.getContextPath()%>/PdcGifReader?com_num=${PdcVO.com_num}"></td> 
				<td>${pdcTSvc.getOnePdcT(PdcVO.pt_num).pt_name}</td>
				<td>${PdcVO.status}</td>
				<td width=200>				
				<c:forEach var="mcVO" items="${mcSvc.getMerList(PdcVO.mercom_num)}" varStatus="p">
					<span>
					${p.count} -
					${pdSvc.getOneProduct(mcVO.com_num).com_name} 
					小杯 ${pdSvc.getOneProduct(mcVO.com_num).m_price}
					大杯 ${pdSvc.getOneProduct(mcVO.com_num).l_price}
					</span><br>
				</c:forEach>
				</td>						
			</tr>
		</c:forEach>	
		</table>
	</div>	<!--id=pdclist-->
	
<button id="getcheckbox">選擇商品</button>	
<button id="reset">清除</button>	

<hr>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message.value}</li>
		</c:forEach>
	</ul>
</c:if>

	<div id="insert">
		<table>
				<tr><td>合併商品清單</td><td id="mercom"></td></tr>
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
	<input type="hidden" name="sto_num" value="${sto_num}">
	<input type="hidden" name="action" value="insert_merge">
	</div><!--id=insert-->	
		
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

	var add="";
	var mprice,lprice;

	$(document).ready(function () {
		$('#insert').hide();
		//已是合併商品標示灰色
		$("input[type=checkbox]:disabled").closest('tr').css("background","#CCCCCC");
		
		//錯誤處理直接到顯示FORM表單
		if(location.pathname=="/BA104G2/pdc_mng/StoPdcMng.do"){
			$("#pdclist").hide("slow");
			$('#insert').show("slow");
			$("#mercom").text("${productVO.com_name}");
		}
		
		//全選+全不選
		$("input[name=selectall]").change('click',function(){
			var checkboxes = $('input[name="checkbox"]:enabled');
			$(this).is(':checked') ? checkboxes.prop('checked', 'checked') : checkboxes.removeAttr('checked');			
		});
		
		//合併按鈕
		$('#getcheckbox').on('click',function(){
			var pname="";
			var m_price=0;
			var l_price=0;
			$("input[name=selectall]").removeAttr('checked');
			$("input[type=checkbox]:checked").each(function(i){
				if($(this).is(':enabled')){	//非合併商品才會計算
					 pname = pname+$(this).parent().siblings("td.com_name").text()+"_";
					 m_price = m_price+parseInt($(this).parent().siblings("td.m_price").text());
					 l_price = l_price+parseInt($(this).parent().siblings("td.l_price").text());
				}
			})
		
			//塞值進去下面的FORM
			$("#mercom").text(pname);
			$("input[name=com_name]").val(pname.trim());
			$("input[name=m_price]").val(m_price);
			$("input[name=l_price]").val(l_price);
			
			//商品清單隱藏
			$("#pdclist").toggle("slow");
			$('#insert').toggle("slow");
			return false;
		});		
		
		//清除按鈕
		$('#reset').on('click',function(){	
			$("input[type=checkbox]").each(function(){
				$(this).prop("checked",false);
			});
			var all_Inputs = $("input[type=text]");
			all_Inputs.val("");
			$("#pdclist").show("slow");
			$('#insert').hide("slow");
			return false;		
		});
		
		
		
	});

</script>
</body>
</html>