<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ page import="java.util.*"%>
<%@ page import="com.store_profile.model.*"%>  

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ÂsÄý©±®a</title>

<style type="text/css">
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
		height:120px;
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
</style>
</head>
<script src="https://code.jquery.com/jquery.js"></script>
<body>
<%

	StoreProfileService spSvc = new StoreProfileService();
	List<StoreProfileVO> stoList = spSvc.getAllgeo();	
	pageContext.setAttribute("stoList",stoList);

%>



	<div class="container-fliud">
			<div class="row">
				
				
					<table id="stolist">
						<c:forEach var="stoVO" items="${stoList}" varStatus="status">
							<c:if test="${status.count==0}">
								<tr>
							</c:if>
							<td>
							<div class="wrap">
								<img height=100 src="<%= request.getContextPath()%>/StoGifReader?sto_num=${stoVO.sto_num}">	
								<a href="xxx.do/sto_num=${stoVO.sto_num}"><h3 class="title">${stoVO.sto_name}</h3></a>
								<span>${stoVO.area}${stoVO.address}</span><br>
<%-- 								<h5 class="glyphicon glyphicon-map-marker color-org"> ¶ZÂ÷ ${stoVO.distance} ¤½¨½</h5>	 --%>
							</div>														
							</td>
							<c:if test="${status.count%3==0}">
								</tr>${staurs.current}
							</c:if>
						</c:forEach>
					</table>
				
				
				

			</div>
	</div>


</body>
</html>