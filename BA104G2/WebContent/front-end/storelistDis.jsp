<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ page import="java.util.*"%>
<%@ page import="com.store_profile.model.*"%>  

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#map {
	  height: 100%;
	  width: 60%;
	  float: left;
	}
</style>
</head>
<body>
<%
	ServletContext context = getServletContext();
	List<StoreProfileVO> newList = (List<StoreProfileVO>) request.getAttribute("newList");
	pageContext.setAttribute("newList",newList);
%>

<div id="map"></div>
<div id="tableOutput">
	    	<table border=1>
	    		<thead>
	    			<tr>
	    				<td>num</td>
		    			<td>Name</td>
		    			<td>PlateNumb</td>
		    			<td>area</td>
		    			<td>lat</td>
		    			<td>lng</td>
	    			</tr>
	    		</thead>
	    		<tbody id="place">
	    		<c:forEach var="addrList" items="${newList}">
	    			<tr>
	    			<td>${addrList.sto_num}</td>
	    			<td>${addrList.sto_name}</td>
	    			<td>${addrList.area}</td>
	    			<td>${addrList.address}</td>
	    			<td>${addrList.lat}</td>
	    			<td>${addrList.lng}</td>
	    		</c:forEach>
	    		</tbody>
	    	</table>
</div>
<script>
	var map;
	var markers = [];
	var searchResult;
	function initMap() {
		
        var map;
        var myLatlng = {lat: 24.969, lng: 121.192};

        map = new google.maps.Map(document.getElementById('map'), {
          center: myLatlng,
          zoom: 10
        });
        var marker = new google.maps.Marker({
          position: myLatlng,
          map: map,
          title: 'Click to zoom'
        });
     
        $.getJSON('/BA104G2/index/IndexServlet.do', function(data) {
            $.each(data, function(key, data) {
              var latLng = new google.maps.LatLng(data.lat, data.lng);
              // Creating a marker and putting it on the map
              var marker = new google.maps.Marker({
                position: latLng,
                title: data.sto_name
              });
              marker.setMap(map);
            });
          });
    
       }
	
</script>


<script src="https://code.jquery.com/jquery.js"></script>
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBgGwpHCYQMEnC2S0l-ycO9Df87WvE2gLk&callback=initMap"></script>
</body>
</html>