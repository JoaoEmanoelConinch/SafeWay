<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cabeçalho OSR</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>resources/css/estilo.css"/>
  	<style>
    <%@include file="/resources/css/estilo.css"%>
		</style>
</head>
<body>
    <header>
      <a  href="<%=request.getContextPath()%>/inicio">Voltar</a>
        <div class="container-logo">
            <div class="logo-imagem"></div>
            <div class="logo-texto">
              <h2>SafeWay</h2>
            </div>
          </div>
			<%-- Os links serão mudados para icones posteriomente --%>
          <div class="menu">
            <ul>
              <li><a href="">Perfil</a></li>
              <li><a href="">Favoritos</a></li>
            </ul>
            </div>

    </header>

</body>
</html>