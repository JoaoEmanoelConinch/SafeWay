package modelo.dao.usuario;

import modelo.entidade.usuario.UsuarioCadastrado;

public interface UsuarioDAO {
	
	void inserirUsuario(UsuarioCadastrado usuario);
	
	void deletarUsuario(UsuarioCadastrado usuario);
	
	void atualizarUsuario(UsuarioCadastrado usuario);
	
	UsuarioCadastrado recuperarUsuarioId(UsuarioCadastrado usuario);
	
	UsuarioCadastrado login(UsuarioCadastrado usuario);
	
	boolean verificarUsuarioNome(UsuarioCadastrado usuario);
	
	boolean verificarUsuarioEmail(UsuarioCadastrado usuario);
	
}