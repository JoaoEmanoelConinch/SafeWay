package modelo.dao.Usuario;

import modelo.entidade.usuario.UsuarioCadastrado;

public interface UsuarioDAO {
	
	void inserirUsuario(UsuarioCadastrado usuario);
	
	void deletarUsuario(UsuarioCadastrado usuario);
	
	void atualizarUsuario(UsuarioCadastrado usuario);
	
}