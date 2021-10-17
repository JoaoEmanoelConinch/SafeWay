<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>

  <title>Tela de Login</title>
  
  <link rel="stylesheet" href="<%=request.getContextPath()%>resources/css/estilo.css"/>

  	<style>
    <%@include file="/resources/css/estilo.css"%>
		</style>
	

  
<body>
<jsp:include page="header.jsp" />



  <div class="container"id="main-container">
  
    <h1>Logar em </h1>
    <h1>SafeWay</h1>

    <form id="register-form" action="logar-usuario" method="POST">
      <div class="full-box">
        <label for="email">Email</label>
        <input type ="text" name="email" id="resp-form" placeholder="Digite seu email" minlength="8" maxlength="45" required>
      </div>
      <div class="half-box">
        <label for="senha">Senha</label>

        <input type="password" name="senha" id="resp-form" placeholder="Digite sua senha" minlength="8" maxlength="45" required>
        
        <a id="login-voltar" class="btn btn-primary btn-lg" href="<%=request.getContextPath()%>/inicio">Voltar</a>
        <button id="login-avancar" type="submit" class="btn btn-primary btn-lg" type="submit">Logar</button>
      </div>
      
    </form>

  </div>

 <div id= "rodape2" >
 <jsp:include page="rodape.jsp" />
 </div>
</body>
 
</html>
