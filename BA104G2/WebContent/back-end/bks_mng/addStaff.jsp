<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.backstage_management.model.*"%>


<%
	
	BackstageManagementVO bmVO = (BackstageManagementVO) request.getAttribute("bmVO");
	session.setAttribute("addform","permit");	//從add頁面來得給個通行證

%>

<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>新增後臺人員 - addProduct.jsp</title>

</head>

<body>
	<jsp:include page="/back-end/back_top.jsp" /> <!-- navbar -->
	<!-- 1層大框框 -->
	<div class="container-fluid">
		<div class="row">
	<!-- 2層框左 -->	
			<jsp:include page="/back-end/back_left.jsp" /> <!-- leftSidebar -->
	<!-- 2層框右 -->		
			<div class="col-xs-12 col-sm-8 col-sm-offset-3" >				
				<div class="block-center panelheight">
	<!--========================== 功能放這邊 =============================================-->

<%-- 查詢+ListAll按鈕 --%>
<%-- <jsp:include page="/back-end/bks_mng/btn_select.jsp" /> --%>

	<table id="table-1">
		<tr>
			<td><h3>新增後臺人員</h3></td>
			<td><h4><a href="<%= request.getContextPath() %>/back-end/bks_mng/bksmng_select_page.jsp">回後臺人員管理首頁</a></h4></td>
		</tr>
	</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li id="err" style="color:red">${message.value}</li>
		</c:forEach>
	</ul>
</c:if>

	<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/bks_mng/BksMng.do" enctype="multipart/form-data">
		<table>
			<tr>
				<td>員工名稱:</td>
				<td><input type="TEXT" name="bm_name" size="45" 
					 value="${bmVO.bm_name}" /></td>
			</tr>
			<tr>
				<td>員工帳號:</td>
				<td><input type="text" name="bm_num" size="45" 
				value="${bmVO.bm_num}"><span class="glyphicon glyphicon-question-sign"></span></td>
			</tr>
			<tr>
				<td>員工手機:</td>
				<td><input type="TEXT" name="bm_number" size="45"
					 value="${bmVO.bm_number}"/></td>
			</tr>
			<tr>
				<td>員工信箱:</td>
				<td><input type="TEXT" name="bm_mail" size="45"
					 value="${bmVO.bm_mail}" /></td>
			</tr>
			<tr>
				<td>員工銀行帳號:</td>
				<td><input type="text" name="bm_banknum" size="45" 
				value="${bmVO.bm_banknum}"></td>
			</tr>
			<tr>
				<td>員工照片:</td>
				<td><input type="File" name="bm_img" size="45"/></td>
			</tr>

		</table>
		
		<input type="hidden" name="action" value="insert">
		<input type="submit" value="送出新增">
	</FORM>
	
<!--========================== 功能放這邊 =============================================-->			
				</div><!-- class="block-center panelheight" -->			
			</div><!-- class="col-xs-12 col-sm-8 col-sm-offset-3" -->
		</div><!-- div class="row" -->
	</div><!-- div class="container-fluid" -->
	
	<jsp:include page="/back-end/back_foot.jsp" />

	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script>
	$('input[name=bm_num]').blur(checkBm_num);
	
	function checkBm_num(){
		 $.ajax({
			    url: '/BA104G2/bks_mng/BksMng.do',
			    type: 'GET',
			    data: {
			    	action: 'check',
			    	bm_num: $('input[name=bm_num]').val()
			    },
			    error: function(xhr) {
			      alert('Ajax request 發生錯誤');
			    },
			    success: function(response) {
			    	if(response=0){
			    		$('input[name=bm_num]').find('span').empty();
			    		
			    	}else{
			    		$('#checkicon').empty();
			    		$('#checkicon').addClass("glyphicon glyphicon-remove-sign"></sapn>);
			    	}
			         
			    }
			  });
	}
	
	</script>
</body>
</html>