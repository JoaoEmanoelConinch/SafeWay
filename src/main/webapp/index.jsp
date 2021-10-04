<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="pt-br">
<head>
    <title>Tela de Inicio OSR</title>

  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
<%--   <link rel="stylesheet" href="<%request.getContextPath()%>resources/css/styles.css"/> --%>
<!--   <style>  -->
<%--     <%@include file="/resources/css/styles.css"%>  --%>
<!--   </style>-->
</head> 

<body>

  <div  id="main-container">
    <div class="cadastrar">
      <a href="<%=request.getContextPath()%>/cadastro"  id="cadastre-se">Cadastre-se<a><br>
    </div>
    
    <div class="logar" >
      <a  href="<%=request.getContextPath()%>/login" id="logar">Logar<br>
     </div>

 </div>    
</body>

</html>