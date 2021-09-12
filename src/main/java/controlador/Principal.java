package controlador;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import modelo.entidade.mapa.Trajeto;
import modelo.enumeracao.mapa.MeioDeTransporte;
import modelo.excecao.mapa.StatusInvalidoException;
import modelo.excecao.usuario.EmailInvalidoException;
import modelo.excecao.usuario.SenhaPequenaException;
import modelo.excecao.usuario.StringVaziaException;

public class Principal {

	public static void main(String[] args) throws StringVaziaException, EmailInvalidoException, SenhaPequenaException, StatusInvalidoException, JsonParseException, JsonMappingException, IOException{
		
		Trajeto trajeto = new Trajeto("rua silvano candido da silva senio, 2491 blumenau", "rua 2 de setembro, 1531 blumenau", MeioDeTransporte.DRIVING_CAR);

		System.out.println(trajeto.getPontos());
	}
}
