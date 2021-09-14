package controlador;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import modelo.dao.Formulario.FormularioDAOImpl;
import modelo.dao.Ponto.PontoDAOImpl;
import modelo.dao.PontoAvaliado.PontoAvaliadoDAOImpl;
import modelo.dao.PontoFavorito.PontoFavDAOImpl;
import modelo.dao.Trajeto.TrajetoDAOImpl;
import modelo.dao.Usuario.UsuarioDAOImpl;
import modelo.entidade.mapa.Ponto;
import modelo.entidade.mapa.Trajeto;
import modelo.enumeracao.mapa.MeioDeTransporte;
import modelo.excecao.mapa.StatusInvalidoException;
import modelo.excecao.usuario.EmailInvalidoException;
import modelo.excecao.usuario.SenhaPequenaException;
import modelo.excecao.usuario.StringVaziaException;

public class Principal {

	public static void main(String[] args) throws StringVaziaException, EmailInvalidoException, SenhaPequenaException, StatusInvalidoException, JsonParseException, JsonMappingException, IOException {

		UsuarioDAOImpl usuarioDao = new UsuarioDAOImpl();
		PontoDAOImpl pontoDao = new PontoDAOImpl();
		FormularioDAOImpl formDao = new FormularioDAOImpl();
		PontoAvaliadoDAOImpl pontoAvDao = new PontoAvaliadoDAOImpl();
		PontoFavDAOImpl pontoFavDao = new PontoFavDAOImpl();
		TrajetoDAOImpl trajetoDao = new TrajetoDAOImpl();

		Ponto p1 = new Ponto(-26.456, 12.212);
		Ponto p2 = new Ponto(-26.789, 11.956);

//		pontoDao.inserirPonto(p1);
//		pontoDao.inserirPonto(p2);

		Trajeto trajeto = new Trajeto(p1, p2, MeioDeTransporte.FOOT_WALKING);

//		trajetoDao.inserirTrajeto(trajeto);

	}
}
