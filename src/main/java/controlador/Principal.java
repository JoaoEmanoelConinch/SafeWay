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

	public static void main(String[] args) throws StringVaziaException, EmailInvalidoException, SenhaPequenaException, StatusInvalidoException, JsonParseException, JsonMappingException, IOException {

		UsuarioDAOImpl usuarioDao = new UsuarioDAOImpl();
		PontoDAOImpl pontoDao = new PontoDAOImpl();
		FormularioDAOImpl formDao = new FormularioDAOImpl();
		PontoAvaliadoDAOImpl pontoAvDao = new PontoAvaliadoDAOImpl();
		PontoFavDAOImpl pontoFavDao = new PontoFavDAOImpl();
		TrajetoDAOImpl trajetoDao = new TrajetoDAOImpl();
		
		
		Ponto p1 = new Ponto(-26.456, 12.212);
		
		pontoDao.inserirPonto(p1);
		
		UsuarioCadastrado usuario = new UsuarioCadastrado("teste", "789654123", "teste12@email.com");
		usuarioDao.inserirUsuario(usuario);
		
		UsuarioCadastrado usuario2 = new UsuarioCadastrado("teste2", "789654123", "teste21@email.com");
		
		usuarioDao.inserirUsuario(usuario2);
		
		usuarioDao.inserirUsuario(usuario);
		
		usuarioDao.inserirUsuario(usuario2);
		
		PontoFavorito pontoFav = new PontoFavorito(-26.456, 12.212 , "ENOIS", usuario);
		
		pontoFavDao.inserirPontoFav(pontoFav);
		
		usuario.addFavorito(pontoFav);
		
		usuario2.addFavorito(pontoFav);
//
//		Formulario form = new Formulario(true, false, false, false, false, "levei um soco :( mas não tinha bloqueio :)", false, p1, usuario);
//		formDao.inserirAvaliacao(form);
//		
//		Formulario form2 = new Formulario(false, true, false, false, false, "furtaram minha bolsa :(", false, p1, usuario2);
//		formDao.inserirAvaliacao(form2);
//		
//		PontoAvaliado pontoAv = new PontoAvaliado(p1);
//		
//	
//		
//		pontoAvDao.adicionarPontoAvaliado(pontoAv);
//		
//		System.out.println(formDao.recuperarAvaliacoes(pontoAv));
		
//		Trajeto trajeto = new Trajeto(p1, p2, MeioDeTransporte.FOOT_WALKING);

//		trajetoDao.inserirTrajeto(trajeto);

	}
}
