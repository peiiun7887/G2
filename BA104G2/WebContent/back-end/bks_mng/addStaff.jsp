<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.backstage_management.model.*"%>


<%
	
	BackstageManagementVO bmVO = (BackstageManagementVO) request.getAttribute("bmVO");
	session.setAttribute("addform","permit");	//�qadd�����ӱo���ӳq����

%>

<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>�s�W��O�H�� - addProduct.jsp</title>

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
<%-- <jsp:include page="/back-end/bks_mng/btn_select.jsp" /> --%>

	<table id="table-1">
		<tr>
			<td><h3>�s�W��O�H��</h3></td>
			<td><h4><a href="<%= request.getContextPath() %>/back-end/bks_mng/bksmng_select_page.jsp">�^��O�H���޲z����</a></h4></td>
		</tr>
	</table>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li id="err" style="color:red">${message.value}</li>
		</c:forEach>
	</ul>
</c:if>

	<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/bks_mng/BksMng.do" enctype="multipart/form-data">
		<table>
			<tr>
				<td>���u�W��:</td>
				<td><input type="TEXT" name="bm_name" size="45" 
					 value="${bmVO.bm_name}" /></td>
			</tr>
			<tr>
				<td>���u�b��:</td>
				<td><input type="text" name="bm_num" size="45" 
				value="${bmVO.bm_num}"><span class="glyphicon glyphicon-question-sign"></span></td>
			</tr>
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
				<td><input type="File" name="bm_img" size="45"/></td>
			</tr>

		</table>
		
		<input type="hidden" name="action" value="insert">
		<input type="submit" value="�e�X�s�W">
	</FORM>
	
<!--========================== �\���o�� =============================================-->			
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
			      alert('Ajax request �o�Ϳ��~');
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