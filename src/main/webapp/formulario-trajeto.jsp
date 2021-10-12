<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="en">
  <head>
    <link rel="stylesheet" href="css/jstyle.css">
  </head>


<div class="absolute"><div id="main-container">

    <h1>Digite as coordenadas</h1>
    
    <form id="register-form" action="criar-trajeto">
     
      <div class="half-box">
        <label for="inicio">Inicio</label>
        <div class="half-box-coordenadas" name="inicio" id="inicio">
           <input class="coordenadas rua" type="text" name="inicio" id="rua-inicio" placeholder="Informe a rua:">
           
        </div>
      </div>
      <div class="half-box">
        <label for="inicio">Chegada</label>
        <div class="half-box-coordenadas" name="chegada" id="chegada">
          <input class="coordenadas rua" type="text" name="chegada" id="rua-chegada" placeholder="Informe a rua:">
        </div>
      </div>
      <div class="half-box">
        <select id="MeioDeTransporte" name="meio-transporte">
            <option value=0>Carro/Moto</option>
			<option value=1>Veiculo pesado</option>
			<option value=2>Ciclismo regular</option>
			<option value=3>Ciclovia</option>
			<option value=4>Mountainbike</option>
			<option value=5>Bicicleta Eletrica</option>
			<option value=6>Andando a pé</option>
			<option value=7>trilha</option>
			
          
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
    