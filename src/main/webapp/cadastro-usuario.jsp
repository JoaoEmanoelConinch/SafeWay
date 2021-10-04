<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Tela de Cadastro</title>

<%-- <style><%@include file="/resources/css/estilo.css"%></style> --%>
</head>
<body>
	<div id="main-container">
		<h1>Cadastre-se para acessar no</h1>
		<h1>SafeWay</h1>

		<form id="register-form" action="inserir-Usuario" method="POST">
			<div class="full-box">
				<label for="name">Nome</label> <input type="text" name="nome"
					id="nome" placeholder="Digite seu nome" data-required
					data-min-length="3" data-max-length="40">
			</div>
			<div class="full-box">
				<label for="email">Email</label> <input type="email" name="email"
					id="email" placeholder="Digite seu email" data-required
					data-min-length="8" value="">
			</div>
			<div class="full-box">
				<label for="lastname">Senha</label> <input type="password"
					name="senha" id="senha" minlength="8" maxlength="45"
					placeholder="Digite sua senha" data-password-validate data-required>
			</div>
			<div class="full-box">
				<label for="passconfirmation">Confirmação de senha</label> <input
					type="password" name="passwordconfirmation"
					id="passwordconfirmation" placeholder="Digite novamente sua senha"
					data-equal="password">
			</div>
			<div class="full-box">
				<button type="submit">Salvar</button>

				<a  href="<%=request.getContextPath()%>/inicio" id="Voltar">Voltar</a>
			</div>
		</form>
	</div>
	<p class="error-validation template"></p>
	<script src="js/scripts.js"></script>
</body>
</html>