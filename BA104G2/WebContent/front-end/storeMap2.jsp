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
	  height: 500px;
	  width: 500px;
	  float: left;
	}
</style>

</head>
<script src="https://code.jquery.com/jquery.js"></script>
<body>

<%
	//店家列表
	ServletContext context = getServletContext();
	List<StoreProfileVO> newList = (List<StoreProfileVO>) request.getAttribute("newList");
	pageContext.setAttribute("newList",newList);
%>

<jsp:include page="/front-end/member_top.jsp" />
<jsp:include page="/front-end/coupon_notify.jsp" />

<!-- 放地圖 -->
<div id="map"></div>

<!-- 地圖上可以看到的店家 -->
<div id="tableOutput">
   	<table border=1>
   		<thead>
   			<tr>
   				<td>店名</td>
    			<td>地址</td>
    			<td>距離</td>
   			</tr>
   		</thead>
   		<tbody id="place">
      		</tbody>
   	</table>
</div>


<script>
	
var map;
var initialLocation;
var lat;
var lng;
var markers = [];
var searchResult;

		function initMap() {	
			var g = window.navigator.geolocation;
	        g.getCurrentPosition(succ, fail);
		}
		
		function fail(event) {	//抓點失敗
	        console.log("Get location fail");
	        var position = {
	        lat : 24.9694,
	        lng : 121.1925
	        };
	        map = new google.maps.Map(document.getElementById("map"), {
	        minZoom : 10,
	        zoom : 8,
	        center : position
	        });
	        console.log(map);
	        var marker = new google.maps.Marker({
	        position : position,
	        title : '現在位置',
	        label : '現在位置',
	        map : map
	        });
	        map.addListener('center_changed',centerChanged);
	    }
		
		function succ(event) {	//抓點成功
	        console.log("Get location succ");	        
	        initialLocation = {
	        lat : event.coords.latitude,
	        lng : event.coords.longitude
	        };
	        
	        map = new google.maps.Map(document.getElementById("map"), {
	        minZoom : 10,
	        zoom : 8,
	        center : initialLocation
	        });
	        var marker = new google.maps.Marker({
	        position : initialLocation,
	        title : '現在位置',
	        label : '現在位置',
	        map : map
	        });
	        
			$.ajax({
			    url: '/BA104G2/index/IndexServlet.do',
			    type: 'GET',
			    data: {
			    	lat : event.coords.latitude,
			        lng : event.coords.longitude
			    },
			    error: function(xhr) {
			      alert('Ajax request 發生錯誤');
			    },
			    success: callback,
			});	        
	        map.addListener('center_changed',centerChanged);
	    }
		
		function centerChanged(event) {
        	deleteMarkers();
//         	var	lat;
//         	var lng;
//         	navigator.geolocation.getCurrentPosition(function(pos){
// 	            lat = pos.coords.latitude;
// 	            lng = pos.coords.longitude};})

        	$.get('/BA104G2/index/IndexServlet.do','lat=24.969&lng=121.192',callback);
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
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
// 	        map = new google.maps.Map(document.getElementById('map'), {
// 	          center: myLatlng,
// 	          zoom: 10
// 	        });
// 	        var marker = new google.maps.Marker({
// 	          position: myLatlng,
// 	          map: map,
	
// 	        });

// 	        window.navigator.geolocation.getCurrentPosition(success,error,option);
	        
// 	        var current_pos;
// 	        window.navigator.geolocation.getCurrentPosition(function(pos){
// 	            current_pos = {lat:pos.coords.latitude, lng:pos.coords.longitude};//抓到現在位置
// 	            map = new google.maps.Map(document.getElementById('map'), {
// 	              zoom: 10,
// 	              center: current_pos
// 	            });
// 	            var marker = new google.maps.Marker({
// 	              position: current_pos,
// 	              map: map
// 	            });
// 	          });
	        
	        
	        
	        
	        
	        

	        
	        
	        
// 			$.get('/BA104G2/index/IndexServlet.do','lat=24.969&lng=121.192',callback);
	        
// 	        map.addListener('center_changed',function(){
// 	        	deleteMarkers();
// 	        	$.get('/BA104G2/index/IndexServlet.do','lat=24.969&lng=121.192',callback);

// 	        });
	       
		
		
		
	
</script>
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBgGwpHCYQMEnC2S0l-ycO9Df87WvE2gLk&callback=initMap&libraries=geometry,places"></script>



</body>
</html>