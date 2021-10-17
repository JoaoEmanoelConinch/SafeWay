<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Formulario de avaliação</title>
    
 <link rel="stylesheet" href="<%=request.getContextPath()%>resources/css/estilo.css"/>
  	<style>
    <%@include file="/resources/css/estilo.css"%>
		</style>
  </head>
<body id="bodytelaavaliar">
    <div class="absolute" id="logo">
        <img src="https://lh3.googleusercontent.com/62ENRr4PVgseYwi970BwZy4bML5uf4Y_lpV8tl1BSawXJ6pH6qsW2OeVfk5IdePFnYLgUxglCaf4ulVWk-lohaL0JeT2Uf0E_Kvc5sJTyBxT4GxNnDxprepJT7OLI3PU9bb92j4YhoIStqT_nBet94c_KT8zhUUHgt-qvbz_9KEtSIfsC1tL_67hEFVMPRGQ84iVuoSex2txcoi-0arqyu7ENYUR7OcFleSWyfuDPgK6DOzKFVZF7bVxAsMRHENtGEVOIpt206aJreB3uZ5Cl9uvEZCP38Aw5IsdwdRORBAq4wf-td6DVbapf91LUlQZxNwgHfy3LMVjzNVlyesberjeoF_8gVRbgO-V0WXrlGZXMEcrhF2v-W1v_kCJfuVu0AF45UTCNXVVsbPyaiwdkK6pVvYziFBk2uR4j4N9wLTLT0FH1yF_bbwpbMwXUkgj5AvRL6CxIf-sVsJUuxmLjTHaCEdjkF2kW__3JXAP-w4deLHt-RuNo-IZJFScYC18LMZWuaa4aI4EbMuHSOq1_wzNA9wMzXD5kGkUULU8JMm-xq1ckcj_41nhKiaYfONYtOuDrsGBZtrBJ7gd887fmeIvop2RPbxsLuC3w34Utgqcp_bVnhVLn__HNBpXRm_eDRUg3Z8ZaebxMRqDCdy6TfpqBdOQdl22RF5KlF57jWT5khQEOzhdZffQC4OttMnG6JLqG_vmXHS_GpPYUTpsPh0=w359-h282-no?authuser=0" id="logo"></img>
       </div>
    <div id="Principald">
        
        <form action="inserir-denuncia" method="POST" >
        <input type="hidden" id="ponto" name="idPonto" value="<c:out value="${ponto.idPonto}"/>"/>
        <fieldset id="nonenn">
            <legend id="avaliarponto">Avalie esse ponto</legend>
            <label>Lesão corporal:</label>
            <label for="sim">Sim</label>
            <input  type="radio"  name="lesaoCorporal" value="true" >
            <label for="nao">Não</label>
            <input type="radio" name="lesaoCorporal" value="false" checked><br><br>
            <label>Furto:⠀⠀⠀⠀⠀⠀⠀</label>
            <label for="sim">Sim</label>
            <input type="radio"  name="furto" value="true">
            <label for="nao">Não</label>
            <input type="radio"  name="furto" value="false" checked><br><br>
            <label>Roubo:⠀⠀⠀⠀⠀⠀</label>
            <label for="sim">Sim</label>
            <input type="radio"  name="roubo" value="true">
            <label for="nao">Não</label>
            <input type="radio"  name="roubo" value="false" checked><br><br>
            <label>Homicídio:⠀⠀ ⠀</label>
            <label for="sim">Sim</label>
            <input type="radio" name="homicidio" value="true">
            <label for="nao">Não</label>
            <input type="radio" name="homicidio" value="false" checked><br><br>
            <label>Latrocínio:⠀⠀ ⠀</label>
            <label for="sim">Sim</label>
            <input type="radio"  name="latrocinio" value="true">
            <label for="nao">Não</label>
            <input type="radio"  name="latrocinio" value="false" checked>
            <label></label>
        </fieldset>
        <fieldset>
            <legend id="comentariolegenda">Escreva seu comentário particular</legend>
            <textarea name="comentario" rows="5" cols="50" maxlength="225" placeholder="⠀Menssagem" id="comentario"></textarea>
        </fieldset>
        <input type="submit" value="Enviar avaliação" id="botaoEavaliacao">
        </form>
    </div>
</body>
</html>