<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<head>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>resources/css/estilo.css" />




<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
	integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
	crossorigin="anonymous"></script>
<title>Trajeto</title>
</head>
<body>
	<jsp:include page="header.jsp" />

	<div>
		<div>

			<form id="register-form" action="criar-trajeto">


				<table class="table">
					<thead class="thead-dark">
						<tr>
							<th id="tabelaop">Inicio</th>
							<th id="tabelaop">Chegada</th>
							<th id="tabelaop">Meio de transporte</th>
							<th id="tabelaop">trajeto</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="Trajeto" items="${trajetos}">
							<tr class="table-active" id="linhatabela">
								<td><c:out value="${Trajeto.inicio.endereço}" /></td>
								<td><c:out value="${Trajeto.chegada.endereço}" /></td>
								<td><c:out value="${Trajeto.transporteUsado}" /></td>
								<td><c:out value="${Trajeto.idtrajeto}"/></td>
								<input id="botaoTrajeto" type="submit"> realizar trajeto</input>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
			<a id="botaovoltar"
				href="<%=request.getContextPath()%>/formulario-trageto">Voltar</a>

		</div>
	</div>
</body>
</html>