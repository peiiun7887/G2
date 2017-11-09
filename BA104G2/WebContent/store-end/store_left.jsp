<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>店家首頁</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/store_base.css">
		<style>

		.active{
			background-color:#3C9682;
		}
		</style>
	
	</head>
<body>	
		
	<!-- left side function bar -->

			<div class="col-xs-12 col-sm-2 col-sm-offset-1 navbars navbar-fixed-top area50">

				<div class="panel panel-default ">
					<!-- 店家圖片 -->

					<div class="panel-body">
						<a href="#">
						    <img class="imgsize thumbnail center-block area20" src="https://api.fnkr.net/testimg/100x100/3C9682/FFF/?text=img+placeholder">
						    <span class="glyphicon glyphicon-pencil pull-right funcbtn-normal"></span>
					    </a>
					</div>
				

					<!--左邊功能伸縮 panel-->
					<div class="panel-group text-center mgrb" id="accordion1" role="tablist" aria-multiselectable="true">

						<!--  1.訂單管理 -->
						<div class="panel panel-default">
							<div class="panel-heading" role="tab" id="panel1">				      
						        <a href="#func1" data-parent="#accordion1" data-toggle="collapse" role="button" aria-expanded="true" aria-controls="func1">
						          <div class="panel-title funcbtn-normal ">訂單管理</div>
						        </a>				      
						    </div>
						    <div id="func1" class="panel-collapse collapse " role="tabpanel" aria-labelledby="panel1">
						      	<div class="list-group">
						        	<a href="#" class="list-group-item active">訂單驗證</a>
						        	<a href="#" class="list-group-item">瀏覽客戶寄杯</a>
						        	<a href="#" class="list-group-item">送出寄杯</a>
						        	<a href="#" class="list-group-item">瀏覽訂單</a>
						        </div>	     
						    </div>
						</div>

						<!-- 2.商品管理 -->
						<div class="panel panel-default">
						    <div class="panel-heading" role="tab" id="panel2">				      
						        <a href="#func2" data-parent="#accordion1" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="func2">
						          <div class="panel-title funcbtn-normal">商品管理</div>
						        </a>				      
						    </div>
						    <div id="func2" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="panel2">
						        <div class="list-group">
						        	<a href="<%= request.getContextPath() %>/store-end/pdc_mng/store_select_page.jsp" class="list-group-item">修改商品</a>
						        	<a href="<%= request.getContextPath() %>/store-end/pdc_mng/store_add_page.jsp" class="list-group-item ">新增商品</a>
						        </div>
						    </div>
						</div>

						<!-- 3.店家資訊 -->
						<div class="panel panel-default">
						    <div class="panel-heading" role="tab" id="panel3">
						        <a href="#func3" data-parent="#accordion1" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="func3">
						          <div class="panel-title funcbtn-normal">店家資訊</div>
						        </a>
						    </div>
						    <div id="func3" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel3">
						      	<div class="list-group">
						        	<a href="#" class="list-group-item active">修改店家資訊</a>
						        	<a href="#" class="list-group-item">瀏覽店家評論</a>
						        	<a href="#" class="list-group-item">店家上/下架</a>
						        	<a href="#" class="list-group-item">集點卡設定</a>
						        	<a href="#" class="list-group-item">檢舉評論</a>
						        </div>	
						    </div>
						</div>

						<!-- 4.點數管理 -->
						<div class="panel panel-default">
						    <div class="panel-heading" role="tab" id="panel4">
						        <a href="#func4" data-parent="#accordion1" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="func4">
						          <div class="panel-title funcbtn-normal">點數管理</div>
						        </a>
						    </div>
						    <div id="func4" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel4">
						      	<div class="list-group">
						        	<a href="#" class="list-group-item active">查詢點數餘額</a>
						        	<a href="#" class="list-group-item">點數提領</a>
						        </div>	
						    </div>
						</div>

						<!-- 5.廣告管理 -->
						<div class="panel panel-default">
						    <div class="panel-heading" role="tab" id="panel5">
						        <a href="#func5" data-parent="#accordion1" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="func5">
						          <div class="panel-title funcbtn-normal">廣告管理</div>
						        </a>
						    </div>
						    <div id="func5" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel5">
						      	<div class="list-group">
						        	<a href="#" class="list-group-item active">新增廣告</a>
						        	<a href="#" class="list-group-item">廣告紀錄</a>
						        	<a href="#" class="list-group-item">購買廣告</a>
						        </div>	
						    </div>
						</div>

						<!-- 6.折價券管理 -->
						<div class="panel panel-default">
						    <div class="panel-heading" role="tab" id="panel6">
						        <a href="#func6" data-parent="#accordion1" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="func6">
						          <div class="panel-title funcbtn-normal">折價券管理</div>
						        </a>
						    </div>
						    <div id="func6" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel6">
						      	<div class="list-group">
						        	<a href="#" class="list-group-item active">申請發佈折價券</a>
						        	<a href="#" class="list-group-item">新增折價券</a>
						        	<a href="#" class="list-group-item">折價券紀錄</a>
						        </div>
						    </div>
						</div>

						<!-- 7.檢舉專區 -->
						<div class="panel panel-default">
						    <div class="panel-heading" role="tab" id="panel7">
						        <a href="#func7" data-parent="#accordion1" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="func7">
						          <div class="panel-title funcbtn-normal">檢舉專區</div>
						        </a>
						    </div>
						    <div id="func7" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel7">
						      	<div class="list-group">
						        	<a href="#" class="list-group-item active">檢舉紀錄</a>
						        	<a href="#" class="list-group-item">聯繫後台人員</a>
						        </div>
						    </div>
						</div>

						<!-- 8.教學專區 -->
						<div class="panel panel-default">
						    <div class="panel-heading" role="tab" id="panel8">
						    	<a href="#func8" data-parent="#accordion1" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="func8" >
						           <div class="panel-title funcbtn-normal">教學專區</div>
						        </a>
						    </div>
						    <div id="func8" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel8">
						      	<div class="list-group">
						        	<a href="#" class="list-group-item active">店家資訊維護教學</a>
						        	<a href="#" class="list-group-item">訂單處理教學</a>
						        	<a href="#" class="list-group-item">廣告發佈教學</a>
						        	<a href="#" class="list-group-item">折價券申請教學</a>
						        </div>
						    </div>
						</div>
				</div>
			</div>		
		</div>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script >
			

			// 改 panel外面 hover 顏色
			$("[class~='panel-heading']").hover(function(){$(this).css("background-color","#DCE6D2")},function(){$(this).css("background-color"," #FFFFFF")});
			
			// 改 panel裡面 hover 顏色
			$(".list-group > [class|='list-group-item']").hover(function(){$(this).css({"background-color":"#DCE6D2","color":"#595942"})},function(){$(this).css({"background-color":"#FFFFFF","color":"#595942"})});
			
			//目前active業面
			var loc = window.location.pathname;
			console.log(loc);
			$('.panel-collapse').find('a').each(function() {
			     $(this).toggleClass('active', $(this).attr('href') == loc);
			  });
			$('.active').css("background","#3C9682");

		</script>	
</body>
</html>