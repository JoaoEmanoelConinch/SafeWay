package modelo.entidade.usuario;

import javax.persistence.Entity;

import modelo.excecao.usuario.EmailInvalidoException;
import modelo.excecao.usuario.SenhaPequenaException;
import modelo.excecao.usuario.StringVaziaException;

@Entity
public class UsuarioAnonimo extends Usuario {

	public UsuarioAnonimo() {
		super();
	}

	public UsuarioAnonimo(long id) {
		super(id);
	}

	public UsuarioCadastrado cadastrar(UsuarioAnonimo usuarioAnonimo, String nome, String senha, String email)
			throws StringVaziaException, EmailInvalidoException, SenhaPequenaException {

		UsuarioCadastrado cadastrar = new UsuarioCadastrado(nome, email, senha);

		return cadastrar;

	}

}
