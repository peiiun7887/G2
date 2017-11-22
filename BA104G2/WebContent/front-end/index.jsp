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
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/member_base.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"><!-- 星星 -->

<title>揪茶趣:訂飲料好方便</title>
 
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
   
   	img{
		display: inline-block;
		float:left;
		margin: 0 10px 0 0;
	}
	#stolist{
		width:100%;
	}

	#stolist td{
		padding: 10px;
		width:30%;
		align:center;
	}
	.wrap{		
		border:1px solid #DCE6D2;
		padding: 10px;
		border-radius:10px;
		height:100px;
	}
	
	.wrap:hover{
		border-color:#EFBC56;
		border-width: unset;
	}
	.color-org{
		color:#FA5532;
	}
	.title{
		color:#3C9682;
		font-weight:border;
		
	}
	a:hover {
    	text-decoration: none;
	}
	.adimg{
		width:100%;
		height:500px;
	}
	
	.checked {
    	color: orange;
	}	
	
	.star-gray{
		color:#CCCCCC;
		size:120%;
	}

   
</style>

</head>
 
<body>
<% 
	ServletContext context = getServletContext();
	List<Map.Entry<String, Integer>> list_RankData  = (List<Map.Entry<String, Integer>>)context.getAttribute("list_RankData");
	StoreProfileService stSvc = new StoreProfileService();
%>

<%

%>


<jsp:include page="/front-end/member_top.jsp" />
<jsp:include page="/front-end/coupon_notify.jsp" />




<div class="container-fluid area20">
	<div class="row">



		<!-- 好評排行-->

		<div class="col-xs-12 col-sm-2 col-sm-offset-1 ">
			
			<% for ( Map.Entry<String, Integer> Key  : list_RankData){ %>
				<div class="good-to-drink">
					<div class="gd-left">
						<img class="imgsize" src="<%= request.getContextPath()%>/StoGifReader?sto_num=<%= Key.getKey() %>">
					</div>
					<div class="gd-right rating-bar">
						<p class="store-name">
						<a href = "<%= request.getContextPath()%>/xxx.do?sto_num=<%= Key.getKey() %>">
						<%= stSvc.getOneStoName(Key.getKey()).getSto_name() %>
						</a>
						</p>						
						<span class="rank-style"></span>
						<span class="rank-point">  <%= Key.getValue() %> </span>
					</div>
				</div>
			<% } %>
				

		<!-- 店家廣告 -->
		
		</div>
			
			<div class="col-xs-12 col-sm-8 col-sm-offset-0">
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
				        	<img class="adimg" src="<%= request.getContextPath()%>/img/ad3.jpeg" alt="">
					    </div>
				        <div class="item">
				            <img class="adimg" src="<%= request.getContextPath()%>/img/ad1.jpeg" alt="">			            
				        </div>
				        <div class="item active">
				            <img class="adimg" src="<%= request.getContextPath()%>/img/ad2.jpeg" alt="">			            
				        </div>
				    </div>
				    <!-- 上下頁控制區 -->
					    <a class="left carousel-control" href="#carousel-id2" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
					    <a class="right carousel-control" href="#carousel-id2" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
				</div>
			</div>
		

		</div>  <!--功能區塊row end-->
</div>   <!--功能區塊container end-->


<!-- 附近店家 -->
<div class="container-fluid area20">
	<div class="row">
		<div class="col-xs-12 col-sm-10 col-sm-offset-1 ">
		<% if (request.getAttribute("stoList")==null){ %>
			<jsp:include page="storeListAll.jsp" />
		<% } %>	
		
		<jsp:include page="/front-end/storeList.jsp" />
		</div>
	</div> 
</div>   <!--附近店家-->



<!--footer-->
<jsp:include page="/front-end/member_foot.jsp" />

		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script>
		$(document).ready(function () {
			
			
			//評分顯示星星
			$(".rank-style").each(function(){
				var rating = parseInt($(this).next().text());
				console.log(rating);
				for(var i =1 ; i< 6 ;i++){
					if(i<rating){
						console.log(i+","+rating)
						$(this).append('<span class="fa fa-star checked"></span>');
					}else{
						$(this).append('<span class="fa fa-star star-gray"></span>');
					}
				}
			});
			
			
		});	
		</script>
</body>
</html>
