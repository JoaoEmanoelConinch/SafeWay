package controlador;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import modelo.dao.Formulario.FormularioDAOImpl;
import modelo.dao.Ponto.PontoDAOImpl;
import modelo.dao.PontoAvaliado.PontoAvaliadoDAOImpl;
import modelo.dao.PontoFavorito.PontoFavDAOImpl;
import modelo.dao.Trajeto.TrajetoDAOImpl;
import modelo.dao.Usuario.UsuarioDAOImpl;
import modelo.entidade.mapa.Ponto;
import modelo.entidade.mapa.PontoFavorito;
import modelo.entidade.usuario.UsuarioCadastrado;
import modelo.excecao.mapa.StatusInvalidoException;
import modelo.excecao.usuario.EmailInvalidoException;
import modelo.excecao.usuario.SenhaPequenaException;
import modelo.excecao.usuario.StringVaziaException;

public class Principal {


	public static void main(String[] args) throws StringVaziaException, EmailInvalidoException, SenhaPequenaException, StatusInvalidoException, JsonParseException, JsonMappingException, IOException{
		
		PontoDAOImpl pontoDao                 = new PontoDAOImpl();
		UsuarioDAOImpl usuarioDao = new UsuarioDAOImpl();
		TrajetoDAOImpl trajetoDao = new TrajetoDAOImpl();
		PontoFavDAOImpl pontoFavDao = new PontoFavDAOImpl();
		FormularioDAOImpl formularioDao = new FormularioDAOImpl();
		PontoAvaliadoDAOImpl pontoAvaliadoDao = new PontoAvaliadoDAOImpl();
		
		

		List<PontoFavorito> pontos = pontoFavDao.recuperarPontoFavoritoUsuario(new UsuarioCadastrado(4));
		
		for(int i = 0; i < pontos.size(); i++) {
			System.out.println(pontos.get(i).getId());
		}
		
	}
}
