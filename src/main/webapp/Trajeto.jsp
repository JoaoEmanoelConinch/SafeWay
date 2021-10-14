<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><html>

<!doctype html>
<head>
	
	 <link rel="stylesheet" href="<%=request.getContextPath()%>resources/css/estilo.css"/>
  	<style>
    <%@include file="/resources/css/estilo.css"%>
		</style>
	
	
	
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<title>Trajeto</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	
	<div>
		<div>
           
			
			
			<br>
			<table class="table">
				<thead class="thead-dark" >	
                <tr>
                    <th id="tabelaop">Endereço</th>
                    <th id="tabelaop">Lesoes corporais</th>
                    <th id="tabelaop">Furtos</th>	
                    <th id="tabelaop">Roubos</th>
                    <th id="tabelaop">Homicidios</th>
                    <th id="tabelaop">Latrocinios</th>
                    <th id="tabelaop">Denunciar</th>
                </tr>
			</thead>
				<tbody>
					<c:forEach var="ponto" items="${pontos}">
						<tr class="table-active" id="linhatabela">
							<td><c:out value="${ponto.endereco}"/></td>
							<td><c:out value="${ponto.quantidadeLesoesCorporais}"/></td>
							<td><c:out value="${ponto.quantidadeFurtos}"/></td>
							<td><c:out value="${ponto.quantidadeRoubos}"/></td>
							<td><c:out value="${ponto.quantidadeHomicidios}"/></td>
							<td><c:out value="${ponto.quantidadeLatrocinio}"/></td>
							<td><a id="botaoavaliar"  href=formolario-denuncia?id=<c:out value="${ponto.idPonto}"/>> Denunciar crime</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
         
         <a id="botaovoltar" href="<%=request.getContextPath()%>/formulario-trageto">Voltar</a>
           
		</div>
	</div>
</body>
</html>