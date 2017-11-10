<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
</head>

<body>

				
					<div class=" btn-group" >
						<form class="navbar-left input-group" METHOD="post" ACTION="<%= request.getContextPath() %>/store-end/pdc_mng/store_select_page.jsp">
							<input type="submit" value="商品列表" class="btn btn-green">
							<input type="hidden" name="action" value="getAllPdc">
						</form>														
						<form class="navbar-left input-group " METHOD="post" ACTION="<%= request.getContextPath() %>/pdc_mng/StoExtMng.do">
							<input type="submit" value="加料列表" class="btn btn-green">
							<input type="hidden" name="action" value="getAllExt">
						</form>
						<form class="navbar-left input-group" METHOD="post" ACTION="<%= request.getContextPath() %>/pdc_mng/StoIceMng.do">
							<input type="submit" value="冰塊列表" class="btn btn-green">
							<input type="hidden" name="action" value="getAllIce">
						</form>
						<form class="navbar-left input-group" METHOD="post" ACTION="<%= request.getContextPath() %>/pdc_mng/StoSwtMng.do">
							<input type="submit" value="甜度列表" class="btn btn-green">
							<input type="hidden" name="action" value="getAllSwt">
						</form>	
					</div>
					
					<form class="navbar-left input-group" METHOD="post" ACTION="<%= request.getContextPath() %>/pdc_mng/StoPdcMng.do">
						<div class="input-group"> 
					    <input type="text" name="com_name" value="" class="form-control" placeholder="搜尋商品">
						<input type="hidden" name="action" value="getName_For_Display">
						<input type="hidden" name="sto_num" value="${sto_num}">
						<span class="input-group-btn">
							<input type="submit" value="送出" class="btn btn-green">
					    </span>    
						</div>					
					</form>		
	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script>
		$(document).ready(function () {

			});	
		});	
	</script>
</body>
</html>