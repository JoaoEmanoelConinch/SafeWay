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

	public static void main(String[] args) throws StringVaziaException, EmailInvalidoException, SenhaPequenaException,
			JsonMappingException, JsonProcessingException, StatusInvalidoException {

				UsuarioDAOImpl daoUsuario = new UsuarioDAOImpl();
				 
						UsuarioCadastrado primeiroUsuario = new UsuarioCadastrado("joao", "golfinho456", "joao@gmail.com");
						daoUsuario.inserirUsuario(primeiroUsuario);
				 
						UsuarioCadastrado segundoUsuario = new UsuarioCadastrado("yuri", "golfinho123", "yuri@gmail.com");
						daoUsuario.inserirUsuario(segundoUsuario);
				
				
	}
}
