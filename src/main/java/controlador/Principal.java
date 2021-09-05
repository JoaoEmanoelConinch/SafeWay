package controlador;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import modelo.dao.Usuario.UsuarioDAOImpl;
import modelo.entidade.usuario.UsuarioCadastrado;
import modelo.excecao.mapa.StatusInvalidoException;
import modelo.excecao.usuario.EmailInvalidoException;
import modelo.excecao.usuario.SenhaPequenaException;
import modelo.excecao.usuario.StringVaziaException;

public class Principal {

	public static void main(String[] args) throws StringVaziaException, EmailInvalidoException, SenhaPequenaException, JsonMappingException, JsonProcessingException, StatusInvalidoException {
		
		UsuarioCadastrado usuario = new UsuarioCadastrado("Jorge", "golfinho456", "JorgeJohnson@gmail.com");
		UsuarioDAOImpl daoUsuario = new UsuarioDAOImpl();
		daoUsuario.inserirUsuario(usuario);
		
<<<<<<< Updated upstream
//		Ponto ponto = new Ponto(12, -4);
		
//		PontoDAOImpl pontoDAOImpl = new PontoDAOImpl();
		
//		pontoDAOImpl.inserirPonto(ponto);
=======
		UsuarioCadastrado usuario = new UsuarioCadastrado("asas", "asdasdasdasd", "asasd@gmail.com");
		
		UsuarioDAOImpl dao= new UsuarioDAOImpl();
		
		dao.inserirUsuario(usuario);
>>>>>>> Stashed changes
		
	}

}
