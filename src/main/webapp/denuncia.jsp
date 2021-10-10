<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Formulario de avaliação</title>
    <link rel="stylesheet" href="css/styles-1.css">
    
</head>


<body>
    <div class="bg-modal" id="modal">
        <div class="modal">
           
        <form action="inserir-denuncia" method="POST" >
        <fieldset>
            <legend>Avalie esse ponto</legend>
            <label>Lesão corporal:</label>
            <label for="sim">Sim</label>
            <input type="radio" id="sim" name="lesaoCorporal" value="true" >
            <label for="nao">Não</label>
            <input type="radio" id="nao" name="lesaoCorporal" value="false" checked><br><br>

            <label>Furto:</label>
            <label for="sim">Sim</label>
            <input type="radio" id="sim" name="furto" value="true">
            <label for="nao">Não</label>
            <input type="radio" id="nao" name="furto" value="false" checked><br><br>

            <label>Roubo:</label>
            <label for="sim">Sim</label>
            <input type="radio" id="sim" name="roubo" value="true">
            <label for="nao">Não</label>
            <input type="radio" id="nao" name="roubo" value="false" checked><br><br>

            <label>Homicídio:</label>
            <label for="sim">Sim</label>
            <input type="radio" id="sim" name="homicidio" value="true">
            <label for="nao">Não</label>
            <input type="radio" id="nao" name="homicidio" value="false" checked><br><br>

            <label>Latrocínio:</label>
            <label for="sim">Sim</label>
            <input type="radio" id="sim" name="latrocinio" value="true">
            <label for="nao">Não</label>
            <input type="radio" id="nao" name="latrocinio" value="false" checked>
            <label></label>
        </fieldset>

        <fieldset>
            <legend>Escreva seu comentário particular</legend>
            <textarea name="comentario" rows="5" cols="50" maxlength="225" placeholder="Menssagem"></textarea>
        </fieldset>
        
        <input type="submit" value="Enviar avaliação">

        </form>

        </div>
    </div>
    
</body>
</html>