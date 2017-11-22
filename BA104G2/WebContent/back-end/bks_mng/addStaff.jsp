<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.backstage_management.model.*"%>
<jsp:useBean id="flSvc" scope="request" class="com.func_list.model.FuncListService" />

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
<jsp:include page="/back-end/bks_mng/btn_select.jsp" />

	<table id="table-1">
		<tr>
			<td><h3>�s�W��O�H��</h3></td>
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
				<td><input type="text" id="bm_num" name="bm_num" size="45" 
				value="${bmVO.bm_num}"><span id = "checkicon"></span></td>
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
	$('#bm_num').blur(checkBm_num);
	
	function checkBm_num(){
		$('#checkicon').empty();
		var a = $('#bm_num').val();
		console.log(a);
		console.log($('#bm_num').val().length);
		if($('#bm_num').val().length<2){
			$('#checkicon').removeClass("glyphicon glyphicon-remove-sign");
			$('#checkicon').removeClass("glyphicon glyphicon-ok-sign");
			$('#checkicon').addClass("glyphicon glyphicon-question-sign");
			$('#checkicon').css("color","orange");
			$('#checkicon').append('�п�J2-10�r�b��');
		}else{
			 $.ajax({
				    url: '/BA104G2/bks_mng/BksMng.do',
				    type: 'GET',
				    data: {
				    	action: 'check',
				    	bm_num: $('#bm_num').val()
				    },
				    error: function(xhr) {
				      alert('Ajax request �o�Ϳ��~');
				    },
				    success: function(response) {
				    	console.log(response);
				    	if(response==0){
				    		$('#checkicon').removeClass("glyphicon glyphicon-remove-sign");
				    		$('#checkicon').addClass("glyphicon glyphicon-ok-sign");
				    		$('#checkicon').css("color","#3C9682");
				    		$('#checkicon').text("�b���i�H�ϥ�");
				    	}else{
				    		
				    		$('#checkicon').removeClass("glyphicon glyphicon-ok-sign");
				    		$('#checkicon').addClass("glyphicon glyphicon-remove-sign");
				    		$('#checkicon').css("color","#FA5532");
				    		$('#checkicon').text("�b���w�Q�ϥ�");
				    	}
				         
				    }
				});
		}
		
	}
	
	</script>
</body>
</html>