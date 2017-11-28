<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>店家首頁</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/store_base.css">

	</head>
<body>
		<!-- top nav bar -->
		<div class="container-fluid">
			<div class="row ">	
				<nav class="navbar-fixed-top navbars">
					<div class="col-xs-12 col-sm-2 col-sm-offset-1 ">
						<div class="nav">
							<span class="display-4"><a class="item-color navbar-brand text-center " href="<%=request.getContextPath()%>/store-end/pdc_mng/store_select_page.jsp">
								揪茶趣 ‧ 店家管理頁面</a></span>
							
						</div>	
					</div>
					
				<div class="col-xs-12 col-sm-7">
					<ul class="nav navbar-nav navbar-right ">	
						<li>
							<a class="item-color " href="<%= request.getContextPath() %>/fakeLogin?action=logoutout">
<%-- 	整合log				<a class="item-color " href="<%= request.getContextPath() %>/store-end/storeprofile/sto.do?action=logout"> --%>
							<span class="glyphicon glyphicon-log-out icons"></span>
								登出							
							</a>
						</li>
					</ul>
				</div>

				<!-- 登出按鈕 -->
				
	</nav>		
			</div>
		</div>


</body>
</html>