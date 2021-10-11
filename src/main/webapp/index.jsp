<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="pt-br">
<head>
    <title>Tela de SafeWay</title>
  
   <link rel="stylesheet" href="<%=request.getContextPath()%>resources/css/estilo.css"/>
  	<style>
    <%@include file="/resources/css/estilo.css"%>
		</style>
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