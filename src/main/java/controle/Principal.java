package controle;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

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


	public static void main(String[] args) 
			throws StringVaziaException, EmailInvalidoException, SenhaPequenaException, StatusInvalidoException, JsonParseException, JsonMappingException, IOException, NumeroMenorQueZeroException, NumeroMaiorQueLimiteException{
		
		PontoDAO pontoDAO = new PontoDAOImpl();
		TrajetoDAO trajetoDAO = new TrajetoDAOImpl();
		
		Ponto ponto1 = pontoDAO.recuperarPonto(new Ponto(4));
		Ponto ponto2 = pontoDAO.recuperarPonto(new Ponto(3));

		Trajeto trajeto = new Trajeto(ponto1, ponto2, MeioDeTransporte.DRIVING_CAR);

		for (int i = 0; i < trajeto.getPontos().size(); i++) {
			Ponto ponto = trajeto.getPontos().get(i);
			if (pontoDAO.verificarPonto(ponto) == null) {
				pontoDAO.inserirPonto(ponto);
			}
			Ponto pontoBD = pontoDAO.verificarPonto(ponto);
			trajeto.getPontos().get(i).setIdPonto(pontoBD.getIdPonto());
		}

		trajetoDAO.inserirTrajeto(trajeto);

	}
}