package controlador;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import modelo.dao.Formulario.FormularioDAOImpl;
import modelo.dao.Ponto.PontoDAOImpl;
import modelo.dao.PontoAvaliado.PontoAvaliadoDAOImpl;
import modelo.dao.PontoFavorito.PontoFavDAO;
import modelo.dao.PontoFavorito.PontoFavDAOImpl;
import modelo.dao.Trajeto.TrajetoDAOImpl;
import modelo.dao.Usuario.UsuarioDAOImpl;
import modelo.entidade.formulario.Formulario;
import modelo.entidade.mapa.Ponto;
import modelo.entidade.mapa.PontoAvaliado;
import modelo.entidade.mapa.Trajeto;
import modelo.entidade.usuario.UsuarioCadastrado;
import modelo.enumeracao.mapa.MeioDeTransporte;

import modelo.excecao.mapa.StatusInvalidoException;
import modelo.excecao.usuario.EmailInvalidoException;
import modelo.excecao.usuario.SenhaPequenaException;
import modelo.excecao.usuario.StringVaziaException;

public class Principal {


	public static void main(String[] args) throws StringVaziaException, EmailInvalidoException, SenhaPequenaException, StatusInvalidoException, JsonParseException, JsonMappingException, IOException{
		
		PontoDAOImpl pontoDAOImpl                 = new PontoDAOImpl();
		UsuarioDAOImpl usuarioDAOImpl             = new UsuarioDAOImpl();
		TrajetoDAOImpl trajetoDAOImpl             = new TrajetoDAOImpl();
		PontoFavDAOImpl pontoFavDAOImpl           = new PontoFavDAOImpl();
		FormularioDAOImpl formularioDAOImpl       = new FormularioDAOImpl();
		PontoAvaliadoDAOImpl pontoAvaliadoDAOImpl = new PontoAvaliadoDAOImpl();
		

		System.out.println();
		Ponto p1 = pontoDAOImpl.recuperarPontos().get(0);
		Ponto p2 = pontoDAOImpl.recuperarPontos().get(1);
		
		UsuarioCadastrado u1 = usuarioDAOImpl.recuperarUsuario(new UsuarioCadastrado(1));
		UsuarioCadastrado u2 = usuarioDAOImpl.recuperarUsuario(new UsuarioCadastrado(2));

		Formulario f = formularioDAOImpl.recuperarAvaliacaoId(new Formulario(1));

		PontoAvaliado pa1 = new PontoAvaliado(p2, f);

		//pontoAvaliadoDAOImpl.adicionarPontoAvaliado(pa1);

	}
}
