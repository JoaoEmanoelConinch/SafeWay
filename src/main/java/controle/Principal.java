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
import modelo.dao.ponto.PontoDAO;
import modelo.dao.ponto.PontoDAOImpl;
import modelo.dao.trajeto.TrajetoDAO;
import modelo.dao.trajeto.TrajetoDAOImpl;
import modelo.entidade.mapa.Ponto;
import modelo.entidade.mapa.Trajeto;
import modelo.enumeracao.mapa.MeioDeTransporte;
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
		TrajetoDAO trajetoDao = new TrajetoDAOImpl();
		
//		Ponto p1 = Ponto.informarLocal("Rua Paraguai 269, Blumenau, SC, Brasil");
//		Ponto p2 = Ponto.informarLocal("Rua Paraguai 299, Blumenau, SC, Brasil");
//		
//		Trajeto trajeto = new Trajeto(p1, p2, MeioDeTransporte.FOOT_WALKING);
//		 for (int i = 0; i < trajeto.getPontos().size(); i++) {
//				Ponto ponto = trajeto.getPontos().get(i);
//				if (pontoDao.verificarPonto(ponto) == null) {
//					pontoDao.inserirPonto(ponto);
//				}
//				Ponto pontoBD = pontoDao.verificarPonto(ponto);
//				trajeto.getPontos().get(i).setIdPonto(pontoBD.getIdPonto());
//			}
//		trajetoDao.inserirTrajeto(trajeto);
		
		System.out.println(pontoDao.recuperarPontoTrajeto(new Trajeto(1)));
	}
}