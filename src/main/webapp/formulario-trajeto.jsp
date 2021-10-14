<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="pt-br">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Criar sua TrajetÃ³ria</title>
   
<link rel="stylesheet" href="<%=request.getContextPath()%>resources/css/estilo.css"/>
  	<style>
    <%@include file="/resources/css/estilo.css"%>
		</style>
  
  </head>

  <body>
<jsp:include page="header.jsp" />
  <div id="logo">
      <img src="https://lh3.googleusercontent.com/62ENRr4PVgseYwi970BwZy4bML5uf4Y_lpV8tl1BSawXJ6pH6qsW2OeVfk5IdePFnYLgUxglCaf4ulVWk-lohaL0JeT2Uf0E_Kvc5sJTyBxT4GxNnDxprepJT7OLI3PU9bb92j4YhoIStqT_nBet94c_KT8zhUUHgt-qvbz_9KEtSIfsC1tL_67hEFVMPRGQ84iVuoSex2txcoi-0arqyu7ENYUR7OcFleSWyfuDPgK6DOzKFVZF7bVxAsMRHENtGEVOIpt206aJreB3uZ5Cl9uvEZCP38Aw5IsdwdRORBAq4wf-td6DVbapf91LUlQZxNwgHfy3LMVjzNVlyesberjeoF_8gVRbgO-V0WXrlGZXMEcrhF2v-W1v_kCJfuVu0AF45UTCNXVVsbPyaiwdkK6pVvYziFBk2uR4j4N9wLTLT0FH1yF_bbwpbMwXUkgj5AvRL6CxIf-sVsJUuxmLjTHaCEdjkF2kW__3JXAP-w4deLHt-RuNo-IZJFScYC18LMZWuaa4aI4EbMuHSOq1_wzNA9wMzXD5kGkUULU8JMm-xq1ckcj_41nhKiaYfONYtOuDrsGBZtrBJ7gd887fmeIvop2RPbxsLuC3w34Utgqcp_bVnhVLn__HNBpXRm_eDRUg3Z8ZaebxMRqDCdy6TfpqBdOQdl22RF5KlF57jWT5khQEOzhdZffQC4OttMnG6JLqG_vmXHS_GpPYUTpsPh0=w359-h282-no?authuser=0" id="logo"></img>

   </div>

  <div class="relative">
     <div class="absolute"><div id="main-container">

      <h1>Digite as coordenadas</h1>
    
      <form id="register-form" action="criar-trajeto">
     
      <div class="half-box">
        <label for="inicio">Inicio</label>
        <div class="half-box-coordenadas" name="inicio" id="inicio">
           <input class="coordenadas rua" type="text" name="inicio" id="inicio" placeholder="Informe a rua:" required minlength="1" >
           
        </div>
      </div>
      <div class="half-box">
        <label for="inicio">Chegada</label>
        <div class="half-box-coordenadas" name="chegada" id="chegada">
          <input class="coordenadas rua" type="text" name="rua-chegada" id="rua-chegada" placeholder="Informe a rua:" required minlength="1">
         
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
							<option value=6>Andando a pÃ©</option>
							<option value=7>Trilha</option>
							<option value=8>Cadeira de rodas</option>
          
        </select>
      </div>
      <br>
      <div class="full-box">
        <input id="go" type="submit" value="GO">
      </div>
    </form>

     </div>
  </div>
  
  </div>
 </div>

 
  
  <p class="error-validation template"></p>
  <script src="js/scripts.js"></script>
</div>
</boby>
