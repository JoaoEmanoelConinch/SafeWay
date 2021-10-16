<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Avaliacoes</title>
</head>
<body>

	<div>
		<div>
			<br>
			<table class="table">
				<thead class="table table-striped">
					<tr>
						<th id="tabelaop">Endereco</th>
						<th id="tabelaop">Lesoes Corporais</th>
						<th id="tabelaop">Furtos</th>
						<th id="tabelaop">Roubos</th>
						<th id="tabelaop">Homicidios</th>
						<th id="tabelaop">Latrocinios</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="avaliacao" items="${avaliacoes}">
						<tr class="table-active" id="linhatabela">
							<td><c:out value="${avaliacao.ponto.endereco}" /></td>
							<td><c:out value="${avaliacao.lesaoCorporal}" /></td>
							<td><c:out value="${avaliacao.furto}" /></td>
							<td><c:out value="${avaliacao.roubo}" /></td>
							<td><c:out value="${avaliacao.homicidio}" /></td>
							<td><c:out value="${avaliacao.latrocinio}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<a id="botaovoltar"
				href="<%=request.getContextPath()%>/formulario-trageto">Voltar</a>
		</div>
	</div>

</body>
</html>