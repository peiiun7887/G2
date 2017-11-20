<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>店家首頁</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/back_base.css">

	</head>
<body>
		<!-- top nav bar -->
	<div class="navbars navbar-fixed-top ">
		<div class="container-fluid">
			<div class="row ">	
				<div class="col-xs-12 col-sm-3 col-sm-offset-1">
					<ul class="nav navbar-nav ">
						<li>
							<a class="navbar-brand item-color" href="#">
							揪茶趣 ‧ 店家管理頁面</a>
						</li>
					</ul>	

				</div>
				<div class="col-xs-12 col-sm-8">
					<ul class="nav navbar-nav navbar-right ">	
						<li>
							
							<a class="item-color " href="<%= request.getContextPath() %>/fakeLogin?action=logoutout">
							<span class="glyphicon glyphicon-log-out icons"></span>
								登出							
							</a>
						</li>
					</ul>
				</div>

				<!-- 登出按鈕 -->
				
			</div>
		</div>
	</div>		


</body>
</html>