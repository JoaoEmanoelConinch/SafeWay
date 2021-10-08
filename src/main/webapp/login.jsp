<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Tela de Login</title>
<%--   <link rel="stylesheet" href="<%request.getContextPath()%>resources/css/styles.css"/> --%>
<!--   <style>  -->
<%--     <%@include file="/resources/css/styles.css"%>  --%>
<!--   </style> -->
  </head>
<body>
  <div id="main-container">
    <h1>Logar em </h1>
    <h1>SafeWay</h1>

    <form id="register-form" action="logar-usuario" method="POST">
      <div class="full-box">
        <label for="email">Email</label>
        <input type ="text" name="email" id="email" placeholder="Digite seu email" data-required data-min-length="8">
      </div>
      <div class="half-box">
        <label for="senha">Senha</label>
        <input type="password" name="senha" id="senha" placeholder="Digite sua senha" data-password-validate data-required>
        <a  href="<%=request.getContextPath()%>/inicio">Voltar</a>
        <button type="submit">Logar</button>
      </div>
    </form>

  </div>
</body>
</html>