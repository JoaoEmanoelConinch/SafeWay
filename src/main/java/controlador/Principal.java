package controlador;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import modelo.dao.Formulario.FormularioDAOImpl;
import modelo.dao.Ponto.PontoDAOImpl;
import modelo.dao.PontoAvaliado.PontoAvaliadoDAOImpl;
import modelo.dao.PontoFavorito.PontoFavDAOImpl;
import modelo.dao.Trajeto.TrajetoDAOImpl;
import modelo.dao.Usuario.UsuarioDAOImpl;
import modelo.entidade.mapa.Ponto;
import modelo.entidade.mapa.PontoFavorito;
import modelo.entidade.mapa.Trajeto;
import modelo.entidade.usuario.UsuarioCadastrado;
import modelo.enumeracao.mapa.MeioDeTransporte;
import modelo.excecao.mapa.StatusInvalidoException;
import modelo.excecao.usuario.EmailInvalidoException;
import modelo.excecao.usuario.SenhaPequenaException;
import modelo.excecao.usuario.StringVaziaException;

public class Principal {

	public static void main(String[] args) throws StringVaziaException, EmailInvalidoException, SenhaPequenaException, StatusInvalidoException, JsonParseException, JsonMappingException, IOException{
		
		PontoDAOImpl pontoDAOImpl = new PontoDAOImpl();
		TrajetoDAOImpl trajetoDAOImpl = new TrajetoDAOImpl();

		Ponto p1 = new Ponto(8.681495,49.41461);
		Ponto p2 = new Ponto(8.687872,49.420318);

		Trajeto t = new Trajeto(p1, p2, MeioDeTransporte.DRIVING_CAR);

		// for (int i = 0; i < t.getPontos().size(); i++){
		// 	Ponto ponto = new Ponto(t.getPontos().get(i).getLatitude(), t.getPontos().get(i).getLongitude());
		// 	pontoDAOImpl.inserirPonto(ponto);
		// }

		 trajetoDAOImpl.inserirTrajeto(t);

	}
}
