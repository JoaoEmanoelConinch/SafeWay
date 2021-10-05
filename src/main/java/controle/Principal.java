package controle;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import modelo.dao.formulario.FormularioDAO;
import modelo.dao.formulario.FormularioDAOImpl;
import modelo.dao.ponto.PontoDAO;
import modelo.dao.ponto.PontoDAOImpl;
import modelo.dao.usuario.UsuarioDAO;
import modelo.dao.usuario.UsuarioDAOImpl;
import modelo.entidade.formulario.Formulario;
import modelo.entidade.mapa.Ponto;
import modelo.entidade.usuario.UsuarioCadastrado;
import modelo.excecao.mapa.NumeroMaiorQueLimiteException;
import modelo.excecao.mapa.NumeroMenorQueZeroException;
import modelo.excecao.mapa.StatusInvalidoException;
import modelo.excecao.usuario.EmailInvalidoException;
import modelo.excecao.usuario.SenhaPequenaException;
import modelo.excecao.usuario.StringVaziaException;

public class Principal {


	public static void main(String[] args) throws StringVaziaException, EmailInvalidoException, SenhaPequenaException, StatusInvalidoException, JsonParseException, JsonMappingException, IOException, NumeroMenorQueZeroException, NumeroMaiorQueLimiteException{
		
		PontoDAO pontoDao = new PontoDAOImpl();
		UsuarioDAO usuarioDao = new UsuarioDAOImpl();
		FormularioDAO formDao = new FormularioDAOImpl();
		
		Ponto p1 = pontoDao.recuperarPonto(new Ponto(7));
		
		System.out.println(pontoDao.recuperarPontoAvaliacoes(p1));
		
//		System.out.println(formDao.recuperarAvaliacaoId(new Formulario(2)));		
		
//		Ponto p1 = pontoDao.recuperarPontoAvaliacoes(new Ponto(7));
////		p1.setEndereco("Senac, Blumenau, SC, Brazil");
////		pontoDao.inserirPonto(p1);
//		
//		
////		Formulario form = new Formulario(false, true, false, false, false, "fui roubado :(", false, p1, usuarioDao.recuperarUsuarioId(new UsuarioCadastrado(1)));
////		formDao.inserirAvaliacao(form);
//		
//		p1.addAvaliacao(formDao.recuperarAvaliacaoId(new Formulario(2)));
//		p1.addAvaliacao(formDao.recuperarAvaliacaoId(new Formulario(3)));
//		pontoDao.atualizarPonto(p1);
		
	}
}
