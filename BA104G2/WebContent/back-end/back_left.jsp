<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="bmSvc" scope="request" class="com.backstage_management.model.BackstageManagementService" />
<html>
<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>後端首頁</title>
		<style>
			#staff-info {
				margin:auto;
				text-align:center;
				font-size:24px;
				font-weight:bolder;
				color:#595942
			}
			
		</style>
	
	</head>
<body>	

	
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12 col-sm-2 col-sm-offset-1 navbars navbar-fixed-top area50">

				
				<div class="panel panel-default ">
					<!-- 店家圖片 -->

					<div class="panel-body " >
							
						    <img class="imgsize thumbnail center-block area20" width="100" height="100" src="<%= request.getContextPath()%>/BmGifReader?bm_no=${sessionScope.bm_no}">
						  
						   	<a href="<%= request.getContextPath() %>/bks_mng/BksMng.do?action=getOne_For_Display&bm_no=${sessionScope.bm_no}" class="center-block">
						   		<div id="staff-info">${bmSvc.findbyPrimaryKey(sessionScope.bm_no).bm_name}<div class="glyphicon glyphicon-pencil"></div></div>
						   							   		
						   	</a>
						   	
					</div>
				

					<!--左邊功能伸縮 panel-->
					<div class="panel-group text-center mgrb" id="accordion1" role="tablist" aria-multiselectable="true">

						<!--  1.看板管理 -->
						<div class="panel panel-default" id="FC0000000001">
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
						<div class="panel panel-default" id="FC0000000002">
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
						<div class="panel panel-default" id="FC0000000003">
						    <div class="panel-heading" role="tab" id="panel3">
						        <a href="#func3" data-parent="#accordion1" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="func3">
						          <h4 class="panel-title funcbtn-normal">店家驗證管理</h4>
						        </a>
						    </div>
						    <div id="func3" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel3">
						      	<div class="list-group">
						        	<a href="<%= request.getContextPath() %>/store-end/pdc_mng/store_select_page.jsp" class="list-group-item active">折價券驗證</a>
						        	<a href="#" class="list-group-item">廣告驗證</a>
						        	<a href="#" class="list-group-item">身分審核驗證</a>
						        	<a href="#" class="list-group-item">點數匯出驗證</a>
						        </div>	
						    </div>
						</div>

						<!-- 4.檢舉管理 -->
						<div class="panel panel-default" id="FC0000000004">
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
						<div class="panel panel-default" id="FC0000000005">
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
						<div class="panel panel-default" id="FC0000000006">
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
						<div class="panel panel-default" id="FC0000000007">
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
						<div class="panel panel-default" id="FC0000000008">
						    <div class="panel-heading" role="tab" id="panel8">
						        <a href="#func8" data-parent="#accordion1" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="func8">
						          <h4 class="panel-title funcbtn-normal">
						          	<a href = "<%= request.getContextPath() %>/back-end/bks_mng/bksmng_select_page.jsp">後台人員資料管理</a>
						          </h4>
						        </a>
						    </div>						    
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

			
			//目前active頁面
			var loc = window.location.pathname;
			console.log(loc);
			$('.panel-collapse').find('a').each(function() {
			     $(this).toggleClass('active', $(this).attr('href') == loc);
			  });
			$('.active').css("background","#3C9682");
			
			//抓權限清單
			var authList = ${authList};
			var authList_ ="";
			for(var i =0 ; i<authList.length;i++){	//把陣列轉字串
				authList_=authList_+authList[i]+" ";
			}
			var func = 'FC000000000';			
			for(var i =1 ; i<9 ; i++){					
				var funcN = func + i;			
				if(!authList_.match(funcN)){
					$('#'+funcN).hide();	//沒在權限清單中的功能藏起來
				}			
			}
			

		</script>	
</body>
</html>