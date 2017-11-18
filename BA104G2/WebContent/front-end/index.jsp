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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">

 <title></title>
    <style>
    div{
    	border: 0px solid #AAAAAA;
    }
    
   .coupon{
   		 background-image: url(<%= request.getContextPath() %>/img/coupon_bg.png);
   		 background-repeat: repeat;
   		 height:50px;   		    		 
   }
   .coupon-title{
   		color:#EFBC56; 
   		font-size:16pt;
   		margin:0 5px;
   }
	
   .coupon-text{
   		color:#3C9682; 
   		font-size:16pt;
   		display:inline-block;
   		height:50px;
        line-height:50px;
        margin:0 5px;
   }
   .imgrvs{
  	 	-webkit-transform: scaleX(-1);
    	transform: scaleX(-1);
   }
   
	h1{text-align:center}
	h2{margin-bottom:0;}
	
	.skill{
	  width:320px;
	  margin:0 auto;
	}
	.rating-bar{
	  background:black;
	  padding:3px;
	}
	.rating-bar span{
	  display:block;
	  height:15px;
	}
	.rating-bar, .rating-bar span {
	  border-radius:4px; 
	}
	
	/* Used to color the bars */
	.blue{ background:blue; }
	.red{ background:red; }
	
	/* .rate-n, n being the rating from 1 to 10 */
	.rate-1{ width:20%; }
	.rate-2{ width:40%; }
	.rate-3{ width:60%; }
	.rate-4{ width:80%; }
	.rate-5{ width:100%; }

	
	/* CSS3 Animation */
	.animate{
	  animation: progress 2s linear;
	  -moz-animation: progress 2s linear;
	  -webkit-animation: progress 2s linear;
	  -ms-animation: progress 2s linear;
	  -o-animation: progress 2s linear;
	}
	/* Span fills 100% of parent div (.rate-n) which may be 10-100% of the width of black bar (.rating) */
	@-webkit-keyframes progress {
	  from { width:0% }
	  to { width:100%; }
	}
	@-moz-keyframes progress {
	  from { width:0% }
	  to { width:100%; }
	}
	@-ms-keyframes progress {
	  from { width:0% }
	  to { width:100%; }
	}
	@-o-keyframes progress {
	  from { width:0% }
	  to { width:100%; }
	}
	@keyframes progress {
	  from { width:0% }
	  to { width:100%; }
	}

</style>

</head>
 
<body>
<% 
	ServletContext context = getServletContext();
	Map<String, Integer> rankList  = ( Map<String, Integer> )context.getAttribute("rankList");
%>

<jsp:include page="/front-end/member_top.jsp" />
<jsp:include page="/front-end/coupon_notify.jsp" />

<div class="container-fluid area20">
			<div class="row">



		<!-- 好評排行-->

		<div class="col-xs-12 col-sm-2 col-sm-offset-1">
			
			<% for (String key : rankList.keySet()){ %>
				<div class="good-to-drink">
					<div class="gd-left">
						<img class="imgsize" src="<%= request.getContextPath()%>/stoGifReader4?sto_num=<%= key %>">
					</div>
					<div class="gd-right rating-bar">
						<span class="store-name">${spSvc.getOneStoName(key)}</span>						
						<span class="rank-style"> <%=rankList.get(key)%></span>
					</div>
				</div>
			<% } %>
				

		<!-- 店家廣告 -->
		
		</div>
			
			<div class="col-xs-12 col-sm-8 col-sm-offset-1">
				<div id="carousel-id2" class="carousel slide" data-ride="carousel">
				    <!-- 幻燈片小圓點區 -->
				    <ol class="carousel-indicators">
				        <li data-target="#carousel-id2" data-slide-to="0" class=""></li>
				        <li data-target="#carousel-id2" data-slide-to="1" class=""></li>
				        <li data-target="#carousel-id2" data-slide-to="2" class="active"></li>
				    </ol>
				    <!-- 幻燈片主圖區 -->
				    <div class="carousel-inner">
				        <div class="item">
				        <img src="image/ad/ad3.jpeg" alt="">
					        </div>
				        <div class="item">
				            <img src="image/ad/ad1.jpeg" alt="">			            
				        </div>
				        <div class="item active">
				            <img src="image/ad/ad2.jpeg" alt="">			            
				        </div>
				    </div>
				    <!-- 上下頁控制區 -->
					    <a class="left carousel-control" href="#carousel-id2" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
					    <a class="right carousel-control" href="#carousel-id2" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
				</div>
			</div>
		

		</div> 

</div>   <!--功能區塊container-->



		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script>
		$(document).ready(function () {
			
			$('.rank-style').each(function(){
				var index = $(this).text();
				console.log(index);
				$(this).addClass(function( index ) {
					return "rate-" + index;
				});
				  
			});
			
		});	
		</script>
</body>
</html>
