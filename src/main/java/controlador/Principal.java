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
import modelo.entidade.mapa.PontoAbstrato;
import modelo.entidade.mapa.PontoAvaliado;
import modelo.entidade.mapa.PontoFavorito;
import modelo.entidade.mapa.Trajeto;
import modelo.entidade.usuario.UsuarioCadastrado;
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
		
		
		PontoAbstrato p1 = new PontoAbstrato(-26.456, 12.212);
		
		PontoAbstrato p2 = new PontoAbstrato(-30.456, 22.212);
		
		pontoDao.inserirPonto(p1);
		
		UsuarioCadastrado usuario = new UsuarioCadastrado("teste", "789654123", "teste12@email.com");
		usuarioDao.inserirUsuario(usuario);
		
		UsuarioCadastrado usuario2 = new UsuarioCadastrado("teste2", "789654123", "teste21@email.com");
		
		usuarioDao.inserirUsuario(usuario2);
		
		PontoFavorito pontoFav = new PontoFavorito(p1 , "asd", usuario);
		
		pontoFavDao.inserirPontoFav(pontoFav);
		
		PontoFavorito pontoFav2 = new PontoFavorito(p2, "asddsadasd" , usuario2);
	
		pontoFavDao.inserirPontoFav(pontoFav2);
	
		

		Formulario form = new Formulario(true, false, false, false, false, "levei um soco :( mas n�o tinha bloqueio :)", false, p1, usuario);
		formDao.inserirAvaliacao(form);
		
		
		Formulario form2 = new Formulario(false, true, false, false, false, "furtaram minha bolsa :(", false, p1, usuario2);
		formDao.inserirAvaliacao(form2);
		
		
		
    	PontoAvaliado pontoAv = new PontoAvaliado(p2);
		
		pontoAvDao.adicionarPontoAvaliado(pontoAv);
		
		System.out.println(formDao.recuperarAvaliacoes(pontoAv));
		
		
//      	Trajeto trajeto = new Trajeto(p1, p2, MeioDeTransporte.FOOT_WALKING);
//
//		trajetoDao.inserirTrajeto(trajeto);
	

	}
}
