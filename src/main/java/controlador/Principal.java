package controlador;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import modelo.dao.Formulario.FormularioDAOImpl;
import modelo.dao.Ponto.PontoDAOImpl;
import modelo.dao.PontoAvaliado.PontoAvaliadoDAOImpl;
import modelo.dao.PontoFavorito.PontoFavDAOImpl;
import modelo.dao.Usuario.UsuarioDAOImpl;
import modelo.entidade.formulario.Formulario;
import modelo.entidade.mapa.Ponto;
import modelo.entidade.mapa.PontoAvaliado;
import modelo.entidade.mapa.PontoFavorito;
import modelo.entidade.usuario.UsuarioCadastrado;
import modelo.excecao.mapa.StatusInvalidoException;
import modelo.excecao.usuario.EmailInvalidoException;
import modelo.excecao.usuario.SenhaPequenaException;
import modelo.excecao.usuario.StringVaziaException;

public class Principal {

	public static void main(String[] args) throws StringVaziaException, EmailInvalidoException, SenhaPequenaException,
			JsonMappingException, JsonProcessingException, StatusInvalidoException {

		UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
		PontoDAOImpl pontoDAO = new PontoDAOImpl();
		PontoAvaliadoDAOImpl pontoAvaliadoDAO = new PontoAvaliadoDAOImpl();
		PontoFavDAOImpl pontoFavDAO = new PontoFavDAOImpl();
		FormularioDAOImpl formularioDAO = new FormularioDAOImpl();

		UsuarioCadastrado usuarioCadastrado = new UsuarioCadastrado("Ana", "88224646", "anna@email.com");
		usuarioDAO.inserirUsuario(usuarioCadastrado);
		
		Ponto ponto = new Ponto(-12.4, -4.76);
		Ponto ponto2 = new Ponto(-12.90, -5.76);
		pontoDAO.inserirPonto(ponto);
		pontoDAO.inserirPonto(ponto2);

		Formulario formulario = new Formulario(false, false, true, false, false, "não vi o rosto dele", 
			false, ponto, usuarioCadastrado);
		formularioDAO.inserirAvaliacao(formulario);

		// PontoAvaliado pontoAvaliado = new PontoAvaliado(ponto, formulario);
		// pontoAvaliadoDAO.adicionarPontoAvaliado(pontoAvaliado);

		PontoFavorito pontoFavorito = new PontoFavorito(ponto, "Escola", usuarioCadastrado);
		pontoFavDAO.inserirPontoFav(pontoFavorito);

	}
}
