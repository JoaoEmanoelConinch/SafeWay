package controlador;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import modelo.dao.Trajeto.TrajetoDAOImpl;
import modelo.dao.Usuario.UsuarioDAOImpl;
import modelo.enumeracao.mapa.*;
import modelo.entidade.mapa.Trajeto;
import modelo.entidade.usuario.UsuarioCadastrado;
import modelo.excecao.mapa.StatusInvalidoException;
import modelo.excecao.usuario.EmailInvalidoException;
import modelo.excecao.usuario.SenhaPequenaException;
import modelo.excecao.usuario.StringVaziaException;

public class Principal {

	public static void main(String[] args) throws StringVaziaException, EmailInvalidoException, SenhaPequenaException, StatusInvalidoException, JsonParseException, org.codehaus.jackson.map.JsonMappingException, IOException {
		
		UsuarioCadastrado usuario = new UsuarioCadastrado("Jorge", "golfinho456", "JorgeJohnson@gmail.com");
		UsuarioDAOImpl daoUsuario = new UsuarioDAOImpl();
		

		
		Trajeto ponto = new Trajeto("-26.4567", "9.12344", MeioDeTransporte.DRIVING_CAR);
		TrajetoDAOImpl daoTrajeto= new TrajetoDAOImpl();
		
		daoTrajeto.inserirTrajeto(ponto);
		
//		List<Ponto> pontos = daoPonto.recuperarPontos();
//		
//		for(Ponto pontosRecuperados : pontos) {
//			System.out.println("Latitude: "+pontosRecuperados.getLatitude());
//			System.out.println("Longitude: "+pontosRecuperados.getLongitude());
//		}

		
	}

}
