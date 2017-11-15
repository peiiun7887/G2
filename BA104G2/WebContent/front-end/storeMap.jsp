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
	  height: 300px;
	  width: 300px;
	  float: left;
	}
</style>

</head>
<script src="https://code.jquery.com/jquery.js"></script>
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
	    				<td>Name</td>
		    			<td>address</td>
		    			<td>distance</td>
	    			</tr>
	    		</thead>
	    		<tbody id="place">
	       		</tbody>
	    	</table>
</div>


<script>
	
		var map;
		var markers = [];
		var searchResult;

		function initMap() {			
			///////////
	        var myLatlng =  new google.maps.LatLng(24.969, 121.192); 
	        
	        map = new google.maps.Map(document.getElementById('map'), {
	          center: myLatlng,
	          zoom: 10
	        });
	        var marker = new google.maps.Marker({
	          position: myLatlng,
	          map: map,
	
	        });
	        
// 	        var current_pos;
	        
// 	        window.navigator.geolocation.getCurrentPosition(function(pos){
// 	            current_pos = {lat: pos.coords.latitude, lng: pos.coords.longitude};//抓到現在位置
// 	            map = new google.maps.Map(document.getElementById('map'), {
// 	              zoom: 10,
// 	              center: current_pos

// 	            });
// 	            var marker = new google.maps.Marker({
// 	              position: current_pos,
// 	              map: map
// 	            });
// 	          });
	        
			$.get('/BA104G2/index/IndexServlet.do','lat=24.969&lng=121.192',callback);
	        
	        map.addListener('center_changed',function(){
	        	deleteMarkers();
	        	$.get('/BA104G2/index/IndexServlet.do','lat=24.969&lng=121.192',callback);

	        });
	       
		}
		
		
		function callback(data){
			
				searchResult = data;
		        $("#place").empty();
		        var index = 1;
		        for(var i = 0; i < data.length; i++){
		            var sto_num = data[i].sto_num;
		            var sto_name = data[i].sto_name;
		            var address = data[i].address;
		            var distance = data[i].distance;
		            var latLng = new google.maps.LatLng(data[i].lat, data[i].lng);
		            if(map.getBounds().contains(latLng)){
		          		addMarker(latLng, data[i]);
		          		$("#place").append("<tr><td><a href='xxx.do/sto_num="+sto_num+"'>"+sto_name+"</a></td><td>"+address+"</td><td>"+distance.toFixed(1)+" km</td></tr>");
		          		index++;
		          	}
		        }
			}

		function addMarker(latLng,data){
			var marker = new google.maps.Marker({
	            position: latLng,
	            title: data.sto_name,
	            map: map,
	            icon:'/BA104G2/img/LOGO_50x50.png'
	        });
			 
			markers.push(marker);
			var infowindow = new google.maps.InfoWindow({
				content: "<h3><a href='xxx.do/sto_num="+data.sto_num+"'>"+data.sto_name+"</a></h3><h5>"+data.address+"</h5><h5>"+data.distance+"</h5>"
			});
	          marker.addListener('click', function() {
	              infowindow.open(map, marker);
	        });
		}
	
		function deleteMarkers(){
			setMapOnAll(null);
			markers=[];
		}	
		
		function setMapOnAll(map){
			for(var i = 0; i< markers.length ; i++){
				markers[i].setMap(map);
			}
		}
	
</script>
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBgGwpHCYQMEnC2S0l-ycO9Df87WvE2gLk&callback=initMap&libraries=geometry,places"></script>



</body>
</html>