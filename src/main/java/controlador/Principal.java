package controlador;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import modelo.entidade.mapa.Ponto;
import modelo.entidade.mapa.Trajeto;
import modelo.enumeracao.mapa.MeioDeTransporte;
import modelo.excecao.mapa.StatusInvalidoException;
import modelo.excecao.usuario.EmailInvalidoException;
import modelo.excecao.usuario.SenhaPequenaException;
import modelo.excecao.usuario.StringVaziaException;

public class Principal {

	public static void main(String[] args) throws StringVaziaException, EmailInvalidoException, SenhaPequenaException, StatusInvalidoException, JsonParseException, JsonMappingException, IOException{
		
		Ponto p1 = new Ponto(-26.902611, -49.077709);
		Ponto p2 = new Ponto(-26.911260, -49.081609);

		Trajeto trajeto = new Trajeto(p1,p2, MeioDeTransporte.DRIVING_CAR);

		System.out.println(trajeto.getPontos());
	}
}
