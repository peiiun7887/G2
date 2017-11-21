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
	<title>���u��ƭק�</title>
</head>

<body>
	<jsp:include page="/back-end/back_top.jsp" /> <!-- navbar -->
	<!-- 1�h�j�خ� -->
	<div class="container-fluid">
		<div class="row">
	<!-- 2�h�إ� -->	
			<jsp:include page="/back-end/back_left.jsp" /> <!-- leftSidebar -->
	<!-- 2�h�إk -->		
			<div class="col-xs-12 col-sm-8 col-sm-offset-3" >				
				<div class="block-center panelheight">
	<!--========================== �\���o�� =============================================-->

<%-- �d��+ListAll���s --%>
<jsp:include page="/back-end/bks_mng/btn_select.jsp" />

<table>
	<tr>
		<td><h3>${sessionScope.bm_no}, ${bmVO.bm_no}���u��ƭק�</h3></td>
	</tr>
</table>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message.value}</li>
		</c:forEach>
	</ul>
</c:if>

	<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/bks_mng/BksMng.do" enctype="multipart/form-data">
	<table>
		<tr>
			<td>���u�s��:</td>
			<td>${bmVO.bm_no}<input type="hidden" name="bm_no" value="${bmVO.bm_no}"></td>
			
		</tr>
		<tr>
			<td>���u�W��:</td>
			<td><input type="TEXT" name="bm_name" size="45" 
					 value="${bmVO.bm_name}" /></td>
		</tr>
		<tr>
			<td>���u�b��:</td>
			<td>${bmVO.bm_num}<input type="hidden" name="bm_num" value="${bmVO.bm_num}"></td>
		</tr>
		<c:if test="${sessionScope.bm_no == bmVO.bm_no }">
		<tr>
			<td>���u�K�X:</td>
			<td><input type="text" name="bm_pwd" value="${bmVO.bm_pwd}"></td>
		</tr>
		</c:if>
		<tr>
			<td>���u���:</td>
			<td><input type="TEXT" name="bm_number" size="45"
				 value="${bmVO.bm_number}"/></td>
		</tr>
		<tr>
			<td>���u�H�c:</td>
			<td><input type="TEXT" name="bm_mail" size="45"
				 value="${bmVO.bm_mail}" /></td>
		</tr>
		<tr>
			<td>���u�Ȧ�b��:</td>
			<td><input type="text" name="bm_banknum" size="45" 
			value="${bmVO.bm_banknum}"></td>
		</tr>
		<tr>
			<td>���u�Ӥ�:</td>
			<td><img height=50 src="<%=request.getContextPath()%>/bmGifReader4?bm_no=${bmVO.bm_no}"><br>
			<input type="File" name="bm_img" size="45"/></td>
		</tr>	
		<tr>
			<td>���u���A:</td>
			<td>
				<select size="1" name="bm_jstatus">
					<option value="�b¾" ${(bmVO.bm_jstatus=='�b¾')? 'selected':'' } >�b¾
					<option value="��¾" ${(bmVO.bm_jstatus=='��¾')? 'selected':'' } >��¾
				</select>
			</td>
		</tr>
		<tr>
			<td>���u�v���G</td>
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
	<input type="submit" value="�e�X�ק�">
	</FORM>
	
<!--========================== �\���o�� =============================================-->			
				</div><!-- class="block-center panelheight" -->			
			</div><!-- class="col-xs-12 col-sm-8 col-sm-offset-3" -->
		</div><!-- div class="row" -->
	</div><!-- div class="container-fluid" -->
	
	<jsp:include page="/back-end/back_foot.jsp" />

	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
</body>
</html>