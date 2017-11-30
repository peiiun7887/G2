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
<body>








		<!--=================================================   ↓↓  footer 從這邊開始   ↓↓  ========================================================== -->

<div class="container-fluid navbars-menu">
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-3 ">
				<img src="<%= request.getContextPath()%>/img/LOGO_150x150_green_edge.png">
				
			</div>
			<div class="col-xs-12 col-sm-3 ">			
				
					<ul>				
						<li ><a href="#" class="icons item-color">教學專區</a></li>
						<li ><a href="#" class="icons item-color">揪茶趣手機版</a></li>				 
						<li ><a href="#" class="icons item-color">關於揪茶趣</a></li>
				    </ul> 				
			</div>
			
			<div class="col-xs-12 col-sm-3 ">
				<ul class="nav nav-pills nav-justified radius5">								      
				     <li ><a href="<%= request.getContextPath()%>/store-end/storeprofile/addStoreProfile.jsp" class="icons item-color">成為店家</a></li>					 
				     <li ><a href="<%= request.getContextPath()%>/store-end/storeprofile/login.jsp" class="icons item-color">店家登入</a></li>				 
				</ul>	
			</div>
			
			<div class="col-xs-12 col-sm-3 ">
				<ul class="nav nav-pills nav-justified radius5">								      
					 <li ><a href="#" class="icons item-color">揪茶趣手機版</a></li>				 
				     <li ><a href="#" class="icons item-color">關於揪茶趣</a></li>				
				     <li ><a href="<%= request.getContextPath()%>/store-end/storeprofile/addStoreProfile.jsp" class="icons item-color">成為店家</a></li>					 
				     <li ><a href="<%= request.getContextPath()%>/store-end/storeprofile/login.jsp" class="icons item-color">店家登入</a></li>				 
				     <li ><a href="#" class="icons item-color">教學專區</a></li>
				</ul>	
			</div>
			
		</div><!-- class="row" -->	
	</div><!-- class="container" -->	
</div><!-- class="container-fluid navbars-menu" -->



<div class="container-fluid navbars">
	<h5 class="text-center item-color">版權所有　<span class="glyphicon glyphicon-copyright-mark">　</span>揪茶趣 All rights reserved.</h5>
</div>	



		<!--=================================================   ↑↑  footer 從這邊開始   ↑↑  ========================================================== -->

</body>
</html>