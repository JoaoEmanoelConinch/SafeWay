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

		UsuarioCadastrado usuarioCadastrado = new UsuarioCadastrado(1);
//		usuarioDAO.inserirUsuario(usuarioCadastrado);
		
		Ponto ponto = new Ponto(-14.4, -8.76);
		Ponto ponto2 = new Ponto(-11.90, -9.76);
//		pontoDAO.inserirPonto(ponto);
//		pontoDAO.inserirPonto(ponto2);

		Formulario formulario = new Formulario(false, false, true, false, false, "não vi o rosto dele", 
			false, ponto, usuarioCadastrado);
//		formularioDAO.inserirAvaliacao(formulario);

		// PontoAvaliado pontoAvaliado = new PontoAvaliado(ponto, formulario);
		// pontoAvaliadoDAO.adicionarPontoAvaliado(pontoAvaliado);

		PontoFavorito pontoFavorito = new PontoFavorito(ponto2, "escola", usuarioCadastrado);
//		pontoFavDAO.inserirPontoFav(pontoFavorito);

		usuarioCadastrado.setNome("Oliver");
		
		usuarioDAO.atualizarUsuario(usuarioCadastrado);
//		pontoDAO.deletarPonto(ponto2);
		
		
	}
}
