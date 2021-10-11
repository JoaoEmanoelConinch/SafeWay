<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><html>

<!doctype html>
<head>
<title>Trajeto</title>
</head>
<body>
	
	
	<div>
		<div>
            <h3>Trajeto</h3>
			<hr>
			
			<br>
			<table>
                <tr>
                    <th>Endereço</th>
                    <th>Lesoes corporais</th>
                    <th>Furtos</th>
                    <th>Roubos</th>
                    <th>Homicidios</th>
                    <th>Latrocinios</th>
                    <th>Denunciar</th>
                </tr>
				
				<tbody>
					<c:forEach var="ponto" items="${pontos}">
						<tr>
							<td><c:out value="${ponto.endereco}"/></td>
							<td><c:out value="${ponto.quantidadeLesoesCorporais}"/></td>
							<td><c:out value="${ponto.quantidadeFurtos}"/></td>
							<td><c:out value="${ponto.quantidadeRoubos}"/></td>
							<td><c:out value="${ponto.quantidadeHomicidios}"/></td>
							<td><c:out value="${ponto.quantidadeLatrocinio}"/></td>
							<td><a href=formolario-denuncia?id=<c:out value="${ponto.idPonto}"/>>Denunciar crime</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
         <br>
         <a  href="<%=request.getContextPath()%>/formulario-trageto">Voltar</a>
         <br>
           
		</div>
	</div>
</body>
</html>