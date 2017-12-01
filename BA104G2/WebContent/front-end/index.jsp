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
<link rel="icon" href="<%= request.getContextPath() %>/img/favicon.ico" type="image/x-icon" /> 
<link rel="shortcut icon" href="<%= request.getContextPath() %>/img/favicon.ico" type="image/x-icon" />  
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/member_base.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"><!-- 星星 -->

<title>揪茶趣:訂飲料好方便</title>
 
<style>

   
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
		background-color:#FFFFFF;
		opacity:0.8;
		box-shadow:2px 2px 12px 2px #22615345;
	}
	
	.wrap:hover{
		opacity:1;
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
    	color:  #ffd280;
    	font-size:16pt;
	}	
	
	.star-gray{
		color:#CCCCCC;
		font-size:16pt;
	}
	.shadow{
		box-shadow: 0px 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
	}
	#comment-rank{
		background-color: #3C9682;
		color:#FFFFFF;
		font-size: 16pt;
    	letter-spacing: 3pt;		
	}
	#carousel-ctrl {
		height:530px;
	}
	#carousel-ctrl .item img{
		height:100%;
	}
   
   
   	

			.bg-fix{
				position: relative;	
				height:650px;
			}
		
			.s1{
				height: 200px;
				color:#FFFFFF;
				background-image: url(<%= request.getContextPath() %>/img/bg02.jpg);
				border: 1px solid #cccccc;
				
			}
			.s2{
				color: #FFFFFF;
				width:100%;
				height: 800px;
				background-image: url(<%= request.getContextPath() %>/img/bg02.jpg);
				border: 1px solid #cccccc;
				background-color: #FFFFFF;
				
			}
			.s3{
				color: #CCCCCC;
				background-image: url(<%= request.getContextPath() %>/img/bg03.jpg);
				border: 1px solid #cccccc;
			}
			.arty{
				margin: auto;
				height: 400px;
				padding:40px;

				background-repeat: no-repeat;
				background-position: center center;
				background-size: cover;
				background-attachment: fixed; 
			}


	</style>	


</head>
 
<body>
<% 
	ServletContext context = getServletContext();
	List<Map.Entry<String, Integer>> list_RankData  = (List<Map.Entry<String, Integer>>)context.getAttribute("list_RankData");
	StoreProfileService stSvc = new StoreProfileService();
%>


<jsp:include page="/front-end/member_top.jsp" />
<jsp:include page="/front-end/coupon_notify.jsp" />



<div class="bg-fix">

<div class="container-fluid area50">
	<div class="row">

		<!-- 好評排行-->

		<div class="col-xs-12 col-sm-2 col-sm-offset-1 shadow radius5 panel">
		
			<div class="panel-heading text-center" id="comment-rank">好評排行榜</div>
				<%	int count = 0; %>
				<% for ( Map.Entry<String, Integer> Key  : list_RankData){ %>
				<%	if(count<5){ %>	
			<div class="panel-body table good-to-drink ">
				<div class="gd-left">
					<img class="imgsize"  src="<%= request.getContextPath()%>/StoGifReader?sto_num=<%= Key.getKey() %>">
				</div>
				<div class="gd-right">
					<p class="store-name">
					<a href = "<%= request.getContextPath()%>/xxx.do?sto_num=<%= Key.getKey() %>">
					<%= stSvc.getOneStoName(Key.getKey()).getSto_name() %>
					</a>
					</p>						
					<span class="rank-style"></span>
					<span class="rank-point">  <%= Key.getValue() %> </span>
				</div>
			</div>
				<%	count ++; %>		
				<% }} %>
		</div>

		<!-- 店家廣告 -->
			
		<div class="col-xs-12 col-sm-8 col-sm-offset-0">
		
			<div id="carousel-id2" class="carousel slide" data-ride="carousel">
			    <!-- 幻燈片小圓點區 -->
			    <ol class="carousel-indicators">
			        <li data-target="#carousel-id2" data-slide-to="0" class=""></li>
			        <li data-target="#carousel-id2" data-slide-to="1" class=""></li>
			        <li data-target="#carousel-id2" data-slide-to="2" class="active"></li>
			        <li data-target="#carousel-id2" data-slide-to="3" ></li>
			        <li data-target="#carousel-id2" data-slide-to="4" ></li>
			    </ol>
			    <!-- 幻燈片主圖區 -->
			    <div class="carousel-inner " id="carousel-ctrl">
			        <div class="item">
			        	<img class="adimg" src="<%= request.getContextPath()%>/img/carousel_01.jpg" alt="">
				    </div>
			        <div class="item">
			            <img class="adimg" src="<%= request.getContextPath()%>/img/carousel_02.jpg" alt="">			            
			        </div>
			        <div class="item active">
			            <img class="adimg" src="<%= request.getContextPath()%>/img/carousel_03.jpg" alt="">			            
			        </div>
			        <div class="item">
			            <img class="adimg" src="<%= request.getContextPath()%>/img/carousel_04.jpg" alt="">			            
			        </div>
			        <div class="item">
			            <img class="adimg" src="<%= request.getContextPath()%>/img/carousel_05.jpg" alt="">			            
			        </div>
			    </div>
			    <!-- 上下頁控制區 -->
<!-- 				    <a class="left carousel-control" href="#carousel-id2" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a> -->
<!-- 				    <a class="right carousel-control" href="#carousel-id2" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a> -->
			</div>
		</div>
		

	</div>  <!--功能區塊row end-->
</div>   <!--功能區塊container end-->


</div>






		<div class="s1 arty col-xs-12 col-sm-12">
			<h1>123</h1>
		</div>

		
		<div class="s2 ">
			<!-- 附近店家 -->
			<div class="container-fluid">
			
				<div class="row">		
					<div class="col-xs-12 col-sm-10 col-sm-offset-1 ">
						<jsp:include page="storeListAll.jsp" />
					</div>
				</div> 
			</div>
			<!--附近店家-->
		</div>
		<div class="s3 arty">
			CCC<br>
			CCC<br>
			CCC<br>

			<div class="panel panel-warning">
			  <div class="panel-heading">
			    <h3 class="panel-title">標題</h3>
			  </div>
			  <div class="panel-body table">
			    AAAAAAA
			  </div>
			  <div class="panel-body table">
			    AAAAAAA
			  </div>
			  <div class="panel-body table">
			    AAAAAAA
			  </div>
			</div>

		</div>











<!--footer-->
<jsp:include page="/front-end/member_foot.jsp" />

		<script>
		$(document).ready(function () {
			
			
			//評分顯示星星
			$(".rank-style").each(function(){
				var rating = parseInt($(this).next().text());
				console.log(rating);
				for(var i =1 ; i<6 ;i++){
					if(i<=rating){						
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
