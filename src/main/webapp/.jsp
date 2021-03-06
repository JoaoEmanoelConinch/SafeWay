<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
     <title>Tela de Cadastro</title>
      <link rel="stylesheet" href="css/styles.css">
       <link rel="stylesheet" href="Stript_telas.js" >
</head>
<body>
  <div id="main-container">
    <h1>Cadastre-se para acessar</h1>
    <h1>Open Safe Routes</h1>

    <form id="register-form" action="inserir-Usuario" method="POST" >
      <div class="full-box">
          <label for="name">Nome</label>
          <input type="text" name="name" id="name" placeholder="Digite seu nome" data-required data-min-length="3" data-max-length="40">
      </div>
      <div class="full-box">
        <label for="email">Email</label>
        <input type = "email" name="email" id="email" placeholder="Digite seu email" data-required data-min-length="8" value="">
      </div>
      <div class="full-box">
        <label for="lastname">Senha</label>
        <input type="password" name="password" id="password" minlength="8" maxlength="45" placeholder="Digite sua senha" data-password-validate data-required>
      </div>
      <div class="full-box">
        <label for="passconfirmation">Confirmação de senha</label>
        <input type="password" name="passwordconfirmation" id="passwordconfirmation" placeholder="Digite novamente sua senha" data-equal="password">
      </div>
      <div class="full-box">
        <input id="btn-submit" type="submit" value="Registrar">

        <a href="Tela InicialOSR.html" id="Voltar">Voltar</a>  
      </div>
    </form>
  </div>
  <p class="error-validation template"></p>
  <script src="js/scripts.js"></script>
</body>
</html>