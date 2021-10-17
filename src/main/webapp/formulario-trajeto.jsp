<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="pt-br">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Criar seu Trajeto</title>
   
<link rel="stylesheet" href="<%=request.getContextPath()%>resources/css/estilo.css"/>
  	<style>
    <%@include file="/resources/css/estilo.css"%>
		</style>
  
  </head>

  <body>
<jsp:include page="header.jsp" />
 

  <div class="relative">
     <div class="absolute"><div id="main-container">

      <h1>Digite as coordenadas</h1>
    
      <form id="register-form" action="criar-trajeto">
     
      <div class="half-box">
        <label for="inicio">Inicio</label>
        <div class="half-box-coordenadas" name="inicio" id="inicio">
           <input class="coordenadas rua" type="text" name="inicio" id="resp-form" placeholder="Informe a rua:" required minlength="1" >
           
        </div>
      </div>
      <div class="half-box">
        <label for="inicio">Chegada</label>
        <div class="half-box-coordenadas" name="chegada" id="chegada">
          <input class="coordenadas rua" type="text" name="rua-chegada" id="resp-form" placeholder="Informe a rua:" required minlength="1">
         
        </div>
      </div>
      <div class="half-box">
        <select id="MeioDeTransporte" name="meio-transporte" placeholder="Informe a rua:">
            <option value=0>Carro/Moto</option>
			<option value=1>Veiculo pesado</option>
			<option value=2>Ciclismo regular</option>
			<option value=3>Ciclovia</option>
			<option value=4>Mountainbike</option>
			<option value=5>Bicicleta Eletrica</option>
			<option value=6>Andando a pé</option>
			<option value=7>Trilha</option>
			<option value=8>Cadeira de rodas</option>
          
        </select>
      </div>
      <br>
      <div class="full-box" id = "boxf">
        <input id="ir" type="submit" value="Iniciar trajeto">
      </div>
    </form>

     </div>
  </div>
  
  </div>
 </div>

 
  
  <p class="error-validation template"></p>
  <script src="js/scripts.js"></script>
</div>
<jsp:include page="rodape.jsp" />
</boby>
