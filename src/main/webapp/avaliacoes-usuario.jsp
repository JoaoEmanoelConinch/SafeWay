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
	<br>
		<table class="table">
		<thead class="table table-striped" >	
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
					<c:forEach var="avaliacoes" items="${avaliacoes}">
						<tr class="table-active" id="linhatabela">
							<td><c:out value="${avaliacoes.ponto.endereco}"/></td>
							<td><c:out value="${avaliacoes.lesaoCorporal}"/></td>
							<td><c:out value="${avaliacoes.furto}"/></td>
							<td><c:out value="${avaliacoes.roubo}"/></td>
							<td><c:out value="${avaliacoes.homicidio}"/></td>
							<td><c:out value="${avaliacoes.latrocinio}"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
         <a id="botaovoltar" href="<%=request.getContextPath()%>/formulario-trageto">Voltar</a>
           
		</div>
	</div>

</body>
</html>