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

		UsuarioDAOImpl usuarioDao = new UsuarioDAOImpl();
		PontoDAOImpl pontoDao = new PontoDAOImpl();
		FormularioDAOImpl formDao = new FormularioDAOImpl();
		PontoAvaliadoDAOImpl pontoAvDao = new PontoAvaliadoDAOImpl();
		
		Ponto ponto = new Ponto(-26.456, 9.123);
		pontoDao.inserirPonto(ponto);
		
		UsuarioCadastrado usuario = new UsuarioCadastrado("Mauri", "123456789", "mauri@email.com");
		usuarioDao.inserirUsuario(usuario);
		
		Formulario form = new Formulario(false, false, false, false, false, "sla", false, ponto, usuario);
		formDao.inserirAvaliacao(form);
		
	//	PontoAvaliado pontoAv = new PontoAvaliado(ponto, form);
	//	pontoAvDao.adicionarPontoAvaliado(pontoAv);
		
		
	}
}
