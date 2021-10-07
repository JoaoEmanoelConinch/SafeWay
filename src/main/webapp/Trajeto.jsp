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
                    <th>Pontos</th>
                    <th>Ações</th>
                </tr>
				
				<tbody>
					<c:forEach var="ponto" items="${pontos}">
						<tr>
                            
							<td><c:out value="${ponto.pontos}" /></td>
                            
                           
                    <td> 
                        
                        <a href="<%=request.getContextPath()%>/formolario-denuncia">Adicionar Avaliação</a>
                    
                    
                    </td>
                            
						</tr>
					</c:forEach>
				</tbody>
			</table>
         <br>
           
		</div>
	</div>
</body>
</html>