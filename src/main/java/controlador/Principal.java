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
import modelo.entidade.usuario.UsuarioCadastrado;
import modelo.excecao.mapa.StatusInvalidoException;
import modelo.excecao.usuario.EmailInvalidoException;
import modelo.excecao.usuario.SenhaPequenaException;
import modelo.excecao.usuario.StringVaziaException;

public class Principal {

	public static void main(String[] args) throws StringVaziaException, EmailInvalidoException, SenhaPequenaException, StatusInvalidoException, JsonParseException, JsonMappingException, IOException{
		
		UsuarioDAOImpl usuarioDao = new UsuarioDAOImpl();
		PontoDAOImpl pontoDao = new PontoDAOImpl();
		FormularioDAOImpl formDao = new FormularioDAOImpl();
		PontoAvaliadoDAOImpl pontoAvDao = new PontoAvaliadoDAOImpl();
		PontoFavDAOImpl pontoFavDao = new PontoFavDAOImpl();
		TrajetoDAOImpl trajetoDao = new TrajetoDAOImpl();
		
		
		UsuarioCadastrado usuario = new UsuarioCadastrado(1);
			
		Ponto ponto = new Ponto(1);
		Ponto p = pontoDao.recuperarPonto(ponto);
		

		Formulario form = new Formulario(true, false, false, false, false, "levei um soco :(", true, p, usuarioDao.recuperarUsuario(usuario));
		formDao.inserirAvaliacao(form);
		
		
		PontoAvaliado pontoAv = new PontoAvaliado(3);
		PontoAvaliado pAv = pontoAvDao.recuperarPontoAvaId(pontoAv);
		
		pontoAv.addAvaliacao(form);
		
		
		pontoAvDao.adicionarPontoAvaliado(pontoAv);
		
//		PontoAvaliado p = new PontoAvaliado(2);
		
//		List<PontoAvaliado> ponto = pontoAvDao.recuperarPontoAvaliadoMediaIgual(p);
//		PontoAvaliado pAv = pontoAvDao.recuperarPontoAvaId(p);
		
//		System.out.println(pAv.getMediaDeAvaliacao());


	}
}
