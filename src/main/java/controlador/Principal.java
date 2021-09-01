package controlador;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import modelo.dao.Ponto.PontoDAOImpl;
import modelo.dao.Usuario.UsuarioDAOImpl;
import modelo.entidade.mapa.Ponto;
import modelo.entidade.usuario.UsuarioCadastrado;
import modelo.excecao.mapa.StatusInvalidoException;
import modelo.excecao.usuario.EmailInvalidoException;
import modelo.excecao.usuario.SenhaPequenaException;
import modelo.excecao.usuario.StringVaziaException;

public class Principal {

	public static void main(String[] args) throws StringVaziaException, EmailInvalidoException, SenhaPequenaException, JsonMappingException, JsonProcessingException, StatusInvalidoException {
		
//		UsuarioCadastrado usuario = new UsuarioCadastrado("Jorge", "golfinho456", "JorgeJohnson@gmail.com");
//		UsuarioDAOImpl daoUsuario = new UsuarioDAOImpl();
//		daoUsuario.inserirUsuario(usuario);
		
		Ponto ponto = new Ponto(12, -4);
		
		PontoDAOImpl pontoDAOImpl = new PontoDAOImpl();
		
		pontoDAOImpl.inserirPonto(ponto);
		
	}

}
