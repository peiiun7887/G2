<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>店家首頁</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/back_base.css">
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

						<!--  1.看板管理 -->
						<div class="panel panel-default">
							<div class="panel-heading" role="tab" id="panel1">				      
						        <a href="#func1" data-parent="#accordion1" data-toggle="collapse" role="button" aria-expanded="true" aria-controls="func1">
						          <h4 class="panel-title funcbtn-normal ">看板管理</h4>
						        </a>				      
						    </div>
						    <div id="func1" class="panel-collapse collapse " role="tabpanel" aria-labelledby="panel1">
						      	<div class="list-group">
						        	<a href="#" class="list-group-item active">店家廣告上下架</a>
						        	<a href="#" class="list-group-item">折價券預告上下架</a>
						        	<a href="#" class="list-group-item">折價券上下架</a>
						        	
						        </div>	     
						    </div>
						</div>

						<!-- 2.會員點數管理 -->
						<div class="panel panel-default">
						    <div class="panel-heading" role="tab" id="panel2">				      
						        <a href="#func2" data-parent="#accordion1" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="func2">
						          <h4 class="panel-title funcbtn-normal">會員點數管理</h4>
						        </a>				      
						    </div>
						    <div id="func2" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel2">
						        <div class="list-group">
						        	<a href="#" class="list-group-item active">查詢儲值紀錄</a>
						        	<a href="#" class="list-group-item">查詢匯出紀錄</a>
						        </div>
						    </div>
						</div>

						<!-- 3.店家驗證管理 -->
						<div class="panel panel-default">
						    <div class="panel-heading" role="tab" id="panel3">
						        <a href="#func3" data-parent="#accordion1" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="func3">
						          <h4 class="panel-title funcbtn-normal">店家驗證管理</h4>
						        </a>
						    </div>
						    <div id="func3" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel3">
						      	<div class="list-group">
						        	<a href="#" class="list-group-item active">折價券驗證</a>
						        	<a href="#" class="list-group-item">廣告驗證</a>
						        	<a href="#" class="list-group-item">身分審核驗證</a>
						        	<a href="#" class="list-group-item">點數匯出驗證</a>
						        </div>	
						    </div>
						</div>

						<!-- 4.檢舉管理 -->
						<div class="panel panel-default">
						    <div class="panel-heading" role="tab" id="panel4">
						        <a href="#func4" data-parent="#accordion1" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="func4">
						          <h4 class="panel-title funcbtn-normal">檢舉管理</h4>
						        </a>
						    </div>
						    <div id="func4" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel4">
						      	<div class="list-group">
						        	<a href="#" class="list-group-item active">瀏覽檢舉紀錄</a>
						        	<a href="#" class="list-group-item">店家檢舉處理</a>
						        	<a href="#" class="list-group-item">會員檢舉處理</a>
						        	<a href="#" class="list-group-item">評論檢舉處理</a>
						        	<a href="#" class="list-group-item">檢舉處理狀況</a>
						        </div>	
						    </div>
						</div>

						<!-- 5.店家管理 -->
						<div class="panel panel-default">
						    <div class="panel-heading" role="tab" id="panel5">
						        <a href="#func5" data-parent="#accordion1" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="func5">
						          <h4 class="panel-title funcbtn-normal">店家管理</h4>
						        </a>
						    </div>
						    <div id="func5" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel5">
						      	<div class="list-group">
						        	<a href="#" class="list-group-item active">瀏覽店家</a>
						        </div>	
						    </div>
						</div>

						<!-- 6.會員管理 -->
						<div class="panel panel-default">
						    <div class="panel-heading" role="tab" id="panel6">
						        <a href="#func6" data-parent="#accordion1" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="func6">
						          <h4 class="panel-title funcbtn-normal">會員管理</h4>
						        </a>
						    </div>
						    <div id="func6" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel6">
						      	<div class="list-group">
						        	<a href="#" class="list-group-item active">搜尋會員</a>
						        </div>
						    </div>
						</div>

						<!-- 7.會員點數管理 -->
						<div class="panel panel-default">
						    <div class="panel-heading" role="tab" id="panel7">
						        <a href="#func7" data-parent="#accordion1" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="func7">
						          <h4 class="panel-title funcbtn-normal">會員點數管理</h4>
						        </a>
						    </div>
						    <div id="func7" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel7">
						      	<div class="list-group">
						        	<a href="#" class="list-group-item active">查詢儲值紀錄</a>
						        	<a href="#" class="list-group-item">查詢匯出紀錄</a>
						        </div>
						    </div>
						</div>

						<!-- 8.後台人員資料管理 -->
						<div class="panel panel-default">
						    <div class="panel-heading" role="tab" id="panel8">
						        <a href="#func8" data-parent="#accordion1" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="func8">
						          <h4 class="panel-title funcbtn-normal">後台人員資料管理</h4>
						        </a>
						    </div>
						    <div id="func8" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel8">
						      	<div class="list-group">
						        	<a href="#" class="list-group-item active">瀏覽後台人員</a>
						        	<a href="#" class="list-group-item">新增後台人員</a>
						        	<a href="#" class="list-group-item">修改後台人員</a>
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
// 			$(".list-group > [class|='list-group-item']").hover(function(){$(this).css({"background-color":"#DCE6D2","color":"#595942"})},function(){$(this).css({"background-color":"#FFFFFF","color":"#595942"})});
			
			//目前active頁面
			var loc = window.location.pathname;
			console.log(loc);
			$('.panel-collapse').find('a').each(function() {
			     $(this).toggleClass('active', $(this).attr('href') == loc);
			  });
			$('.active').css("background","#3C9682");

		</script>	
</body>
</html>