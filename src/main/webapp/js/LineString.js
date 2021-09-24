   type="text/javascript"  
src="http://openlayers.org/api/OpenLayers.js"
  
  src="http://www.openstreetmap.org/openlayers/OpenStreetMap.js"
    
 src='http://maps.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAAjpkAC9ePGem0lIq5XcMiuhR_wWLPFku8Ix9i2SXYRVK3e45q1BQUd_beF8dtzKET_EteAjPdGDwqpQ'
     src='https://openlayers.org/en/latest/examples/line-arrows.html'
  type="text/javascript"  

var map;
var lineLayer;
var points;
var style;




var polygonFeature
function test(){
      lineLayer = new OpenLayers.Layer.Vector("Line Layer");
      style = { strokeColor: '#ff00ff',
         strokeOpacity: 0.8,
         strokeWidth: 7
      };

      points = new Array();
      
      points[0] =new OpenLayers.LonLat(-49.063751,-26.915298).transform(new OpenLayers.Projection("EPSG:4326"), map.getProjectionObject());;
      points[0] = new OpenLayers.Geometry.Point(points[0].lon,points[0].lat);

      points[1] = new OpenLayers.LonLat( -49.062885,-26.916216).transform(new OpenLayers.Projection("EPSG:4326"), map.getProjectionObject());;
      points[1] = new OpenLayers.Geometry.Point(points[1].lon,points[1].lat);

      

     
     
      var linear_ring = new OpenLayers.Geometry.LinearRing(points);
      polygonFeature = new OpenLayers.Feature.Vector(
         new OpenLayers.Geometry.Polygon([linear_ring]), null, style, );
         lineLayer.addFeatures([polygonFeature]);

      map.addLayer(lineLayer);

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

