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
import modelo.entidade.mapa.PontoAvaliado;
import modelo.entidade.mapa.PontoFavorito;
import modelo.entidade.usuario.UsuarioCadastrado;                               
import modelo.excecao.mapa.StatusInvalidoException;
import modelo.excecao.usuario.EmailInvalidoException;
import modelo.excecao.usuario.SenhaPequenaException;
import modelo.excecao.usuario.StringVaziaException;

public class Principal {


	public static void main(String[] args) throws StringVaziaException, EmailInvalidoException, SenhaPequenaException, StatusInvalidoException, JsonParseException, JsonMappingException, IOException{
		
		PontoDAOImpl pontoDao = new PontoDAOImpl();
		UsuarioDAOImpl usuarioDao = new UsuarioDAOImpl();
		TrajetoDAOImpl trajetoDao = new TrajetoDAOImpl();
		PontoFavDAOImpl pontoFavDao = new PontoFavDAOImpl();
		FormularioDAOImpl formularioDao = new FormularioDAOImpl();
		PontoAvaliadoDAOImpl pontoAvaliadoDao = new PontoAvaliadoDAOImpl();
		
		
		UsuarioCadastrado usu = new UsuarioCadastrado("Guilherme Arana", "12345678", "garana@gmail.com");
		
		//usuarioDao.inserirUsuario(usu);
		
		UsuarioCadastrado u = usuarioDao.recuperarUsuario(usu);
		
		Ponto p1 = new Ponto(-32.22,21.03);
		
		//pontoDao.inserirPonto(p1);
		
		Ponto p = pontoDao.recuperarPonto(p1);
		
		PontoAvaliado pontoAv = new PontoAvaliado(p);
		
		pontoAvaliadoDao.adicionarPontoAvaliado(pontoAv);
		
		PontoFavorito pontoFav = new PontoFavorito(p , "Queroz" , u);
		
		pontoFavDao.inserirPontoFav(pontoFav);
		
	}
}
