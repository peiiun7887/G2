<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<html>

<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<title>揪茶趣</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/member_base.css">

</head>
<% 

%>


<body>
		<!-- top nav bar =============================================================== -->
	<div class="navbars navbar-fixed-top">
		<div class="container">
			<div class="row ">
				<ul class="nav navbar-nav navbar-right ">
				<c:if test="${mem_num==null}">
					<li>
						<a class="item-color" href="<%= request.getContextPath()%>/store-end/form.jsp">
						<span class="glyphicon glyphicon-log-in icons"></span>
						登入</a>
					</li>
					<li>
						<a class="item-color" href="#">
						<span class="glyphicon glyphicon-pencil icons"></span>
						註冊</a>
					</li>
				</c:if>	
				<c:if test="${mem_num!=null}">
					<li>
						<a class="item-color" href="<%= request.getContextPath()%>/store-end/form.jsp">
						
						${mem_num}，你好</a>
					</li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle item-color" data-toggle="dropdown">
							<span class="glyphicon glyphicon-user icons"></span>
							會員專區
							
						</a>
						<ul class="dropdown-menu">	
							<li><a href="#">訂單管理</a></li>
							<li><a href="#">寄杯</a></li>
							<li><a href="#">點數</a></li>
							<li><a href="#">集點卡</a></li>	
							<li><a href="#">最愛店家</a></li>
							<li><a href="#">評論</a></li>
							<li><a href="#">好友</a></li>
							<li><a href="#">個人資料</a></li>				
						</ul>
					</li>
					<li>
						<a class="item-color" href="<%= request.getContextPath() %>/fakeLogin?action=logoutm">
						<span class="glyphicon glyphicon-log-out"></span>
						登出</a>
					</li>
				
				</c:if>

					
				</ul>
			</div>
		</div>
	</div>

		<!-- LOGO + Serch Bar + hotserch + 訂單管理 + 附近店家 + 折價券 ============ -->
		<div class="container">
		    <div class="row">
				
				<!-- LOGO -->
				<div class="col-xs-12 col-sm-3 ">
		        	<img src="<%= request.getContextPath() %>/img/LOGO_w_285x150.png">
		        </div>

		        <!-- serch bar + hot key-->
				<div class="col-xs-12 col-sm-5 ">
				    

				    <!-- search bar -->
				   <form method="post" action="">
						<div class="input-group area70">
						  <input type="text" name="keyword"class="form-control input-lg" placeholder="請輸入關鍵字">
						  <span class="input-group-btn">
						    <button class="btn input-lg btn-green" type="button"><span class=" glyphicon glyphicon-search"></span></button>
						  </span>
						</div>
					</form>
					


				 <!-- hot keys 熱門字 24個中文字滿-->        
				
			     <div class="hotkey">
			     	<ul class="list-inline"> 
			     	    <li>熱門字</li>		
			     	    <li>熱門字</li>
			     	    <li>熱門字</li>
			     	    <li>熱門字</li>
			     	    <li>熱門字</li>
			     	    <li>熱門字</li>
			     	    <li>熱門字</li>
			     	    
			     	 </ul>
			     </div>
				</div>



		        <!-- 訂單管理 btn+ 附近店家 btn + 折價券btn -->
		        <div class="col-xs-12 col-sm-4 area70">			        	
	        		<button type="button" class="btn btn-green  btn-lg ">訂單管理</button>			        	
	        		<button type="button" class="btn btn-green  btn-lg">附近店家</button>			        	
	        		<button type="button" class="btn btn-org  btn-lg">瘋折價券</button>         	
		    	</div>
			</div>
		</div>	


<!-- ====================================   ↑   HEADER到這邊結束    ↑   ======================================================== -->








		<!--=================================================   ↓↓  footer 從這邊開始   ↓↓  ========================================================== -->
<div class="navbars">
<div class="container area70">
	<div class="row">
		<div class="col-xs-12 col-sm-12">			
				<nav class="navbar">
					<ul class="nav nav-pills nav-justified radius5">								      
							 <li ><a href="#" class="icons item-color">揪茶趣手機版</a></li>				 
						     <li ><a href="#" class="icons item-color">關於揪茶趣</a></li>				
						     <li ><a href="#" class="icons item-color">成為店家</a></li>					 
						     <li ><a href="#" class="icons item-color">店家登入</a></li>				 
						     <li ><a href="#" class="icons item-color">教學專區</a></li>
					</ul>	
					<p class="text-center ">版權所有 © 揪茶趣 All rights reserved.</p>			
				</nav>
				
			</div>
		</div>	
	</div>
</div>

		<!--=================================================   ↑↑  footer 從這邊開始   ↑↑  ========================================================== -->
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>