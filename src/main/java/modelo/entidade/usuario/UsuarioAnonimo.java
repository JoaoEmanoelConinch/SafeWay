package modelo.entidade.usuario;

import modelo.excecao.usuario.EmailInvalidoException;
import modelo.excecao.usuario.SenhaPequenaException;
import modelo.excecao.usuario.StringVaziaException;

public class UsuarioAnonimo extends Usuario{

	 public UsuarioAnonimo() {
		super();
	}
	 public UsuarioCadastrado cadastrar(UsuarioAnonimo usuarioanonimo, String nome, String senha, String email) throws StringVaziaException, EmailInvalidoException, SenhaPequenaException {
			
		 UsuarioCadastrado cadastrar = new UsuarioCadastrado(nome, email, senha);
			

			 return cadastrar;
			 
		 }
}
