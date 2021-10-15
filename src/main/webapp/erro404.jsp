<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><html>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
 <link rel="stylesheet" href="<%=request.getContextPath()%>resources/css/estilo.css"/>
  	<style>
    <%@include file="/resources/css/estilo.css"%>
		</style>


    </head> 
        <title>Tela de Erro</title>

<body id="erro">

    <div id="div-erro">
        <h1 class="text-erro">404 ERRO</h1>
        <h2 class="text-erro">ESSA AÇÃO NÃO ESTÁ DISPONÍVEL NO MOMENTO, SENTIMOS MUITO!</h2><br>
        <h2 class="text-erro">POR GENTILEZA CLIQUE ABAIXO PARA VOLTAR A TELA INICIAL</h2><br>
        
        <a id="voltar-erro"  href="<%=request.getContextPath()%>/inicio">Voltar</a>
    </div >

</body>
</html>