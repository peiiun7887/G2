<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.backstage_management.model.*"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="flSvc" scope="request" class="com.func_list.model.FuncListService" />
	
<% 
	BackstageManagementVO bmVO = (BackstageManagementVO) request.getAttribute("bmVO"); 
	List<String> funcList = (List<String>)request.getAttribute("funcList");
%>

<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>員工資料修改</title>
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
<jsp:include page="/back-end/bks_mng/btn_select.jsp" />

<table>
	<tr>
		<td><h3>${sessionScope.bm_no}, ${bmVO.bm_no}員工資料修改</h3></td>
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

	<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/bks_mng/BksMng.do" enctype="multipart/form-data">
	<table>
		<tr>
			<td>員工編號:</td>
			<td>${bmVO.bm_no}<input type="hidden" name="bm_no" value="${bmVO.bm_no}"></td>
			
		</tr>
		<tr>
			<td>員工名稱:</td>
			<td><input type="TEXT" name="bm_name" size="45" 
					 value="${bmVO.bm_name}" /></td>
		</tr>
		<tr>
			<td>員工帳號:</td>
			<td>${bmVO.bm_num}<input type="hidden" name="bm_num" value="${bmVO.bm_num}"></td>
		</tr>
		<c:if test="${sessionScope.bm_no == bmVO.bm_no }">
		<tr>
			<td>員工密碼:</td>
			<td><input type="text" name="bm_pwd" value="${bmVO.bm_pwd}"></td>
		</tr>
		</c:if>
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
			<td><img height=50 src="<%=request.getContextPath()%>/bmGifReader4?bm_no=${bmVO.bm_no}"><br>
			<input type="File" name="bm_img" size="45"/></td>
		</tr>	
		<tr>
			<td>員工狀態:</td>
			<td>
				<select size="1" name="bm_jstatus">
					<option value="在職" ${(bmVO.bm_jstatus=='在職')? 'selected':'' } >在職
					<option value="離職" ${(bmVO.bm_jstatus=='離職')? 'selected':'' } >離職
				</select>
			</td>
		</tr>
		<tr>
			<td>員工權限：</td>
			<td>
			<c:forEach var="funcVO" items="${flSvc.all}">
				<input type="checkbox" name=func value="${funcVO.func_no}"
					
						<c:forEach var="funcList" items="${funcList}">
							${(funcList==funcVO.func_no)? 'checked':'' }
						</c:forEach>					
					
					> ${funcVO.func_name} <br>
			</c:forEach>
			</td>
		</tr>		
	</table>

	
	<input type="hidden" name="action" value="update">
	<input type="submit" value="送出修改">
	</FORM>
	
<!--========================== 功能放這邊 =============================================-->			
				</div><!-- class="block-center panelheight" -->			
			</div><!-- class="col-xs-12 col-sm-8 col-sm-offset-3" -->
		</div><!-- div class="row" -->
	</div><!-- div class="container-fluid" -->
	
	<jsp:include page="/back-end/back_foot.jsp" />

	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
</body>
</html>