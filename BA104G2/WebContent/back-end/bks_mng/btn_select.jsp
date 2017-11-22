<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<style>
	.btn-green-group{
		background:#595942; /*deep green*/
		color:#FFFFFF;
	}
	.bar{
		margin-right:20px;
	}

</style>
</head>

<body>

<ul class="nav nav-pills page-header">
				<li class="bar">
					<div class=" input-group" >
						<div class=" btn-group" >
						<form class="btn-group" METHOD="post" ACTION="<%= request.getContextPath() %>/back-end/bks_mng/bksmng_select_page.jsp">
							<span class="input-group-btn">
							<input type="submit" value="員工列表" class="btn btn-green ">
							</span>
							<input type="hidden" name="action" value="getAllStaff">
						</form>	
						<a class="btn btn-green " href="<%= request.getContextPath() %>/back-end/bks_mng/addStaff.jsp">新增員工</a>
							
						</div>
					</div>
				</li>
				
				
</ul>


	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script>
		$(document).ready(function () {
			
		});	
	</script>
</body>
</html>