<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
      <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
    <head>
        <title></title>
    <script type="text/javascript"  src="http://openlayers.org/api/OpenLayers.js"></script>
    <script src="http://www.openstreetmap.org/openlayers/OpenStreetMap.js"></script>
    <script src='http://maps.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAAjpkAC9ePGem0lIq5XcMiuhR_wWLPFku8Ix9i2SXYRVK3e45q1BQUd_beF8dtzKET_EteAjPdGDwqpQ'></script> 
<script src="https://openlayers.org/en/latest/examples/line-arrows.html"></script>

  <script type="text/javascript">    

var map;
var lineLayer;
var points;
var style;

var polygonFeature

function test(){
      lineLayer = new OpenLayers.Layer.Vector("Line Layer");
      style = { strokeColor: '#0000ff',
         strokeOpacity: 0.6,
         strokeWidth: 5
      };

      var i
      for(i = 0; i > points.size(i); i++){

var p = points.get[i]

points[i] = new OpenLayers.LonLat(points.getLongitude, points.getLatitude).transform(new OpenLayers.Projection("EPSG:4326"), map.getProjectionObject());
points[i] = new OpenLayers.Geometry.Point(points[i].lon,points[i].lat);

     
var linear_String = new OpenLayers.Geometry.LineString(points);
      lineFeature = new OpenLayers.Feature.Vector(
         new OpenLayers.Geometry.MultiLineString([linear_String]), null, style, );
         lineLayer.addFeatures([lineFeature]);

      map.addLayer(lineLayer);

 
      }
}
     
   


  function initialize() 
  {    
      map = new OpenLayers.Map ("map_canvas", {
            controls:[
                new OpenLayers.Control.Navigation(),
                new OpenLayers.Control.PanZoomBar(),
                new OpenLayers.Control.Attribution()],
            maxExtent: new OpenLayers.Bounds(-20037508.34,-20037508.34,20037508.34,20037508.34),
            maxResolution: 156543.0399,
            
            units: 'm',
            projection: new OpenLayers.Projection("EPSG:900913"),
            displayProjection: new OpenLayers.Projection("EPSG:4326")
          });

        // Define the map layer
        // Here we use a predefined layer that will be kept up to date with URL changes
        layerMapnik = new OpenLayers.Layer.OSM.Mapnik("Mapnik");
        map.addLayer(layerMapnik);
      var lonLat = new OpenLayers.LonLat(-49.064228, -26.915230).transform(new OpenLayers.Projection("EPSG:4326"), map.getProjectionObject());
        map.zoomTo(3);
        map.setCenter(lonLat, 18);  

    test();
  }

  </script>
    </head>

    <body onload="initialize()" >

    <div id="map_canvas" width: 100%; height: 100%></div>  
  </body>


</html>