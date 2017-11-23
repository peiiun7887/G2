<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ page import="java.util.*"%>
<%@ page import="com.coupon.model.*"%>
<%@ page import="com.store_profile.model.*"%>
<jsp:useBean id="cpSvc" scope="request" class="com.coupon.model.CouponService" />
<jsp:useBean id="spSvc" scope="request" class="com.store_profile.model.StoreProfileService" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no">

    <style>
    div{
    	border: 0px solid #AAAAAA;
    }
    
   .coupon{
   		 background-image: url(<%= request.getContextPath() %>/img/coupon_bg.png);
   		 background-repeat: repeat;
   		 height:70px;   		    		 
   }
   .coupon-title{
   		color:orange; 
   		font-size:16pt;
   		margin:0 5px;
   }
	
   .coupon-text{
   		color:#3C9682; 
   		font-size:16pt;
   		display:inline-block;
   		height:70px;
        line-height:70px;
        margin:0 5px;
   }
   .imgrvs{
  	 	-webkit-transform: scaleX(-1);
    	transform: scaleX(-1);
   }

    </style>
</head>
  
<body>
<%
	
 	List<CouponVO> cpList = cpSvc.getCoupon();
	pageContext.setAttribute("cpList",cpList);

%>

		<!-- 折價券預告============================================================= -->

		<div class="container-fluid area50">
			<div class="row">
				<div id="carousel-id" class="carousel slide" data-ride="carousel">
				    <!-- 折價券圖區 -->
				    <div class="carousel-inner radius5">
				    <c:forEach var="cpMsg" items="${cpList}" varStatus="i">
				        <div class="item coupon text-center ${(i.count==1)?'active':''}" >
				        	<span class="coupon-title">折價券預告 <span class=" glyphicon glyphicon-bullhorn"></span></span>
				        	<span class="coupon-text"> ${cpMsg.up_date} 起  </span>
				        	<span class="coupon-text"> ${spSvc.getOneStoName(cpMsg.sto_num).sto_name } ${cpMsg.coupon_desc}元 共${cpMsg.total}張</span>
				        	<span class="coupon-title"> <span class=" glyphicon glyphicon-bullhorn imgrvs"></span> 折價券預告</span>
						</div>
					</c:forEach>
				    </div>
				</div>
			</div>
		</div>


		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
