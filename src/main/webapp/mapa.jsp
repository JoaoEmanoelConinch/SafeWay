<!doctype html>
<html lang="en">
  <head>




<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>mapa</title>
  <link rel="stylesheet" href="css/jstyle.css">
 
  



    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/openlayers/openlayers.github.io@master/en/v6.5.0/css/ol.css" type="text/css">
    <style>
div.relative {
    position: relative;
   height: 850px;
        width: 100%;
    
} 

div.absolute {
    position: absolute;
    top: 200px;
    right: 10;
    width: 300px;
    height: 300px;
    box-sizing: border-box;
  font-family: Helvetica, sans-serif;
  color: #323232;
  border: none;
}


      .map {
        height: 850px;
        width: 100%;
      }
    </style>
    <script

    

 src="https://cdn.jsdelivr.net/gh/openlayers/openlayers.github.io@master/en/v6.5.0/build/ol.js"></script>
    <title>OpenLayers example</title>
  </head>
  <body>
    
<div class="relative">

    <div id="map" class="map"></div>
    <script type="text/javascript">
      var map = new ol.Map({
        target: 'map',
        layers: [
          new ol.layer.Tile({
 source: new ol.source.OSM()
           
          })
        ],
        view: new ol.View({
          center: ol.proj.fromLonLat([-49.064228, -26.915230]),
          zoom: 18
        })
      });


    

    </script>






  <div class="absolute"><div id="main-container">

    <h1>Digite as coordenadas</h1>
    
    <form id="register-form" action="criar-trajeto-Locais">
     
      <div class="half-box">
        <label for="inicio">Inicio</label>
        <div class="half-box-coordenadas" name="inicio" id="inicio">
           <input class="coordenadas rua" type="text" name="inicio" id="rua-inicio" placeholder="Informe a rua:">
           
        </div>
      </div>
      <div class="half-box">
        <label for="inicio">Chegada</label>
        <div class="half-box-coordenadas" name="chegada" id="chegada">
          <input class="coordenadas rua" type="text" name="rua-chegada" id="rua-chegada" placeholder="Informe a rua:">
         
        </div>
      </div>
      <div class="half-box">
        <select id="MeioDeTransporte" name="meio-transporte">
          <option value="driving-car">Carro/Moto</option>
							<option value="driving-hgv">Veiculo pesado</option>
							<option value="cycling-regular">Ciclismo regular</option>
							<option value="cycling-road">Ciclovia</option>
							<option value="cycling-mountain">Mountainbike</option>
							<option value="cycling-electric">Bicicleta Eletrica</option>
							<option value="foot-walking">Andando a pé</option>
							<option value="foot-hiking">trilha</option>
							<option value="wheelchair">Cadeira de rodas</option>
          
        </select>
      </div>
      <div class="full-box">
        <input id="btn-submit" type="submit" value="GO">
      </div>
    </form>

  </div>
  <p class="error-validation template"></p>
  <script src="js/scripts.js"></script>
</div>



 














</div>


</body>
