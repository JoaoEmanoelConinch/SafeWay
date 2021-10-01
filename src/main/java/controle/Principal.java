package controle;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import modelo.dao.ponto.PontoDAO;
import modelo.dao.ponto.PontoDAOImpl;
import modelo.entidade.mapa.Ponto;
import modelo.excecao.mapa.NumeroMaiorQueLimiteException;
import modelo.excecao.mapa.NumeroMenorQueZeroException;
import modelo.excecao.mapa.StatusInvalidoException;
import modelo.excecao.usuario.EmailInvalidoException;
import modelo.excecao.usuario.SenhaPequenaException;
import modelo.excecao.usuario.StringVaziaException;

public class Principal {


	public static void main(String[] args) 
			throws StringVaziaException, EmailInvalidoException, SenhaPequenaException, StatusInvalidoException, JsonParseException, JsonMappingException, IOException, NumeroMenorQueZeroException, NumeroMaiorQueLimiteException{
		
		PontoDAO pontoDao = new PontoDAOImpl();
		
		Ponto p =  Ponto.informarLocal("Senac, Blumenau, SC, Brazil",1);
		
		System.out.println(p.getLatitude());
		System.out.println(p.getLongitude()
				);
		
		p.setEndereco(p.informarLatLong());
		
		System.out.println(p.getEndereco());
		
	}
}
