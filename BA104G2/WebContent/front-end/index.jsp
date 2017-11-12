<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no">

 <title>Marker Clustering</title>
    <style>

      #map {
        height: 100%;
      }
 
      html, body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
    </style>
</head>
  
<body>
   
  <body>
    <div id="map"></div>
    <script>

      function initMap() {

    	  window.navigator.geolocation.getCurrentPosition(function(pos){
              current_pos = {lat: pos.coords.latitude, lng: pos.coords.longitude};//抓到現在位置
              map = new google.maps.Map(document.getElementById('map'), {
                zoom: 15,
                center: current_pos	// map center
              });
              var marker = new google.maps.Marker({
                position: current_pos,
                map: map			
              });
			});
    	  
    	  var marker = new google.maps.Marker({
    		 
    	  });
    	  
    	  
 
      
      } 
      
      function getJSON(url, callback) {
          var xhr = new XMLHttpRequest();
          xhr.open('GET', url, true);
          xhr.responseType = 'json';
          xhr.onload = function() {
            var status = xhr.status;
            if (status === 200) {
              callback(null, xhr.response);
            } else {
              callback(status, xhr.response);
            }
          };
          xhr.send();
        }

      
 
    </script>
    <script src="https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/markerclusterer.js">
    </script>
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBgGwpHCYQMEnC2S0l-ycO9Df87WvE2gLk&callback=initMap">
    </script>
  </body>
</html>
</body>
</html>
