<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="pt-br">

<head>
    <title>Tela de SafeWay</title>
  
   <link rel="stylesheet" href="<%=request.getContextPath()%>resources/css/estilo.css"/>
  	<style>
    <%@include file="/resources/css/estilo.css"%>
		</style>
	
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

    </head> 

<jsp:include page="header.jsp" />
<body>


<body>
  <div class="container text-center" >
    <div class="row">
        
        <div class="col-12" >
   <img id="imagenindex" src ="https://lh3.googleusercontent.com/62ENRr4PVgseYwi970BwZy4bML5uf4Y_lpV8tl1BSawXJ6pH6qsW2OeVfk5IdePFnYLgUxglCaf4ulVWk-lohaL0JeT2Uf0E_Kvc5sJTyBxT4GxNnDxprepJT7OLI3PU9bb92j4YhoIStqT_nBet94c_KT8zhUUHgt-qvbz_9KEtSIfsC1tL_67hEFVMPRGQ84iVuoSex2txcoi-0arqyu7ENYUR7OcFleSWyfuDPgK6DOzKFVZF7bVxAsMRHENtGEVOIpt206aJreB3uZ5Cl9uvEZCP38Aw5IsdwdRORBAq4wf-td6DVbapf91LUlQZxNwgHfy3LMVjzNVlyesberjeoF_8gVRbgO-V0WXrlGZXMEcrhF2v-W1v_kCJfuVu0AF45UTCNXVVsbPyaiwdkK6pVvYziFBk2uR4j4N9wLTLT0FH1yF_bbwpbMwXUkgj5AvRL6CxIf-sVsJUuxmLjTHaCEdjkF2kW__3JXAP-w4deLHt-RuNo-IZJFScYC18LMZWuaa4aI4EbMuHSOq1_wzNA9wMzXD5kGkUULU8JMm-xq1ckcj_41nhKiaYfONYtOuDrsGBZtrBJ7gd887fmeIvop2RPbxsLuC3w34Utgqcp_bVnhVLn__HNBpXRm_eDRUg3Z8ZaebxMRqDCdy6TfpqBdOQdl22RF5KlF57jWT5khQEOzhdZffQC4OttMnG6JLqG_vmXHS_GpPYUTpsPh0=w359-h282-no?authuser=0">
        
        
        </div>
        
        </div>

        <div class="row">
    <div class="col"></div>
    <div class="col"><a class="btn btn-primary btn-lg bt-inicial"  href="<%=request.getContextPath()%>/cadastro" >Cadastrar</a></div>
    <div class="col"></div>
            </div>

        <div class="row">
    <div class="col"></div>
    <div class="col"><a class="btn btn-primary btn-lg bt-inicial"  href="<%=request.getContextPath()%>/login" >Logar</a></div>
    <div class="col"></div>
</div>
       
    </div>


<jsp:include page="rodape.jsp" />
</body>

</html>