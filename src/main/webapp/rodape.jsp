<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><html>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Rodapé</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>resources/css/estilo.css"/>
  	<style>
    <%@include file="/resources/css/estilo.css"%>
		</style>
		
    </head>
<body>
    <footer>
        <div class="links">
           <h4>Links do Projeto:</h4>
           <ul>
           <%-- Os link serão mudados pra ícones posteriomente --%>
            <li><a href="https://github.com/JoaoEmanoelConinch/SafeWay" target="_blank">GitHub</a></li>
            <li><a href=""> | </a></li>
            <li><a href="https://openrouteservice.org/" target="_blank"> API</a></li>
            
          </ul>


        </div>


    </footer>
</body>
</html>