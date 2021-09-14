package controlador;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import modelo.dao.Formulario.FormularioDAOImpl;
import modelo.dao.Ponto.PontoDAOImpl;
import modelo.dao.PontoAvaliado.PontoAvaliadoDAOImpl;
import modelo.dao.PontoFavorito.PontoFavDAOImpl;
import modelo.dao.Usuario.UsuarioDAOImpl;
import modelo.entidade.mapa.Ponto;
import modelo.entidade.mapa.PontoFavorito;
import modelo.entidade.usuario.UsuarioCadastrado;

import modelo.excecao.mapa.StatusInvalidoException;
import modelo.excecao.usuario.EmailInvalidoException;
import modelo.excecao.usuario.SenhaPequenaException;
import modelo.excecao.usuario.StringVaziaException;

public class Principal {

	public static void main(String[] args) throws StringVaziaException, EmailInvalidoException, SenhaPequenaException,

			JsonMappingException, JsonProcessingException, StatusInvalidoException {

		UsuarioDAOImpl usuarioDao = new UsuarioDAOImpl();
		PontoDAOImpl pontoDao = new PontoDAOImpl();
		FormularioDAOImpl formDao = new FormularioDAOImpl();
		PontoAvaliadoDAOImpl pontoAvDao = new PontoAvaliadoDAOImpl();
		PontoFavDAOImpl pontoFavDao = new PontoFavDAOImpl();
		
		
		UsuarioCadastrado usuario = new UsuarioCadastrado(2);
		List<UsuarioCadastrado> usuario1 = usuarioDao.recuperarUsuario(usuario);
		UsuarioCadastrado usuario2 = usuario1.get(0);
		
		usuarioDao.deletarUsuario(usuario2);
		
//		usuarioDao.inserirUsuario(usuario);

		
		
		

	}
}
