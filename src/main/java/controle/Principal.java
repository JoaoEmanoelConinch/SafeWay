package controle;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import modelo.dao.PontoAvaliado.PontoAvaliadoDAOImpl;
import modelo.dao.PontoFavorito.PontoFavDAOImpl;
import modelo.dao.formulario.FormularioDAOImpl;
import modelo.dao.ponto.PontoDAOImpl;
import modelo.dao.trajeto.TrajetoDAOImpl;
import modelo.dao.usuario.UsuarioDAOImpl;
import modelo.entidade.mapa.Ponto;

import modelo.entidade.mapa.PontoAbstrato;
import modelo.entidade.mapa.PontoAvaliado;

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
		

		
		Ponto p1 = new Ponto(-26.456, 12.212);
		
		Ponto p2 = new Ponto(-30.456, 22.212);
		
		pontoDao.inserirPonto(p1);
		
		UsuarioCadastrado usuario = new UsuarioCadastrado("teste", "789654123", "teste12@email.com");
		usuarioDao.inserirUsuario(usuario);
		
		UsuarioCadastrado usuario2 = new UsuarioCadastrado("teste2", "789654123", "teste21@email.com");
		
		usuarioDao.inserirUsuario(usuario2);

		
		PontoFavorito pontoFav = new PontoFavorito(p1 , "asd", usuario);
		
		pontoFavDao.inserirPontoFav(pontoFav);
		
		PontoFavorito pontoFav2 = new PontoFavorito(p2, "asddsadasd" , usuario2);
	
		pontoFavDao.inserirPontoFav(pontoFav2);
	
		

		List<PontoFavorito> pontos = pontoFavDao.recuperarPontoFavoritoUsuario(new UsuarioCadastrado(4));
		

		
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
