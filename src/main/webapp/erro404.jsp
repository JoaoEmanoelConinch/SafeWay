<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><html>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<%--     <link rel="stylesheet" href="<%request.getContextPath()%>resources/css/styles.css"/> --%>
<!--     <style>  -->
<%--       <%@include file="/resources/css/styles.css"%>  --%>
<!--     </style> -->
        <title>Tela de Erro</title>
</head>
<body>
    <div id="conteudo">
        <h1>404 ERRO</h1>
        <h2>ESSA AÇÃO NÃO ESTÁ DISPONÍVEL NO MOMENTO, SENTIMOS MUITO!</h2><br>
        <h2>POR GENTILEZA CLIQUE ABAIXO PARA VOLTAR A TELA INICIAL</h2><br>
        <a  href="<%=request.getContextPath()%>/inicio">Voltar</a>
    </div >

</body>
</html>