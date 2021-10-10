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
			<form action="formolario-denuncia">
			<table>
                <tr>
                    <th>Endereço</th>
                    <th>Denunciar</th>
                </tr>
				
				<tbody>
					<c:forEach var="ponto" items="${pontos}">
						<tr>
							<td><c:out value="${ponto.endereco}"/></td>
<%--                        		<td><a href="<%=request.getContextPath()%>/formolario-denuncia">Denunciar Crime</a></td> --%>
                            <td>
                            	<label for="${pontos.ponto}">Denunciar</label>
                            	<input type="checkbox" id="${pontos.ponto}" name="${pontos.ponto}" value="${pontos.ponto}">
                            </td>
                            
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<br>
			<input type="submit" value="Denunciar pontos">
			</form>
         <br>
         <a  href="<%=request.getContextPath()%>/formulario-trageto">Voltar</a>
         <br>
           
		</div>
	</div>
</body>
</html>