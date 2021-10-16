<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="pt-br">
<head>

<title>Tela de Cadastro</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>resources/css/estilo.css"/>
  	<style>
    <%@include file="/resources/css/estilo.css"%>
		</style>
	
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

    </head>
<body>
<jsp:include page="header.jsp" />

	<div id="main-container" class="conteiner">
		<h1>Cadastre-se</h1>
		

		<form id="register-form" action="inserir-usuario" method="POST">
			<div class="full-box">
				<label for="name">Nome</label>
				 <input type="text" name="nome" id="nome" placeholder="Digite seu nome" minlength="3" maxlength="45" required>
			</div>
			
			<div class="full-box">
				<label for="email">Email</label>
				<input type="email" name="email" id="email" placeholder="Digite seu email" minlength="8" maxlength="45" required>
			</div>
			
			<div class="full-box">
				<label for="lastname">Senha</label>
				<input type="password" name="senha" id="senha" placeholder="Digite sua senha" minlength="8" maxlength="45" placeholder="Digite sua senha" required>
			</div>
			
			<div class="full-box">
				<label for="passconfirmation">Confirmação de senha</label>
				<input type="password" name="passwordconfirmation" id="passwordconfirmation" placeholder="Digite novamente sua senha" minlength="8" maxlength="45" required>
			</div>
			<div class="full-box">
			
			<a id="cadastro-voltar" class="btn btn-primary btn-lg" href="<%=request.getContextPath()%>/inicio" id="Voltar">Voltar</a>
			<button type="submit" id="cadastro-avancar" class="btn btn-primary btn-lg">Salvar</button>

				
			</div>
		</form>
		
	</div>
	 <jsp:include page="rodape.jsp" />
	</body>
</html>