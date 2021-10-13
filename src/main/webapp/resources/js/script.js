function validarSenha(){
NovaSenha = document.getElementById('password').value;
CNovaSenha = document.getElementById('passwordconfirmation').value;
if (NovaSenha == CNovaSenha && novaSenha != null ) {
document.formCadastro.submit();
}else{
alert("SENHAS DIFERENTES!\nFAVOR DIGITAR SENHAS IGUAIS");
event.preventDefault ();
}
}