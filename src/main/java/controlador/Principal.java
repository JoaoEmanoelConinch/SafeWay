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
import modelo.entidade.mapa.PontoFavorito;
import modelo.entidade.mapa.Trajeto;
import modelo.entidade.usuario.UsuarioCadastrado;
import modelo.enumeracao.mapa.MeioDeTransporte;
import modelo.excecao.mapa.NumeroMenorQueZeroException;
import modelo.excecao.mapa.StatusInvalidoException;
import modelo.excecao.usuario.EmailInvalidoException;
import modelo.excecao.usuario.SenhaPequenaException;
import modelo.excecao.usuario.StringVaziaException;

public class Principal {


	public static void main(String[] args) throws NullPointerException, Throwable{
		
		PontoDAOImpl pontoDAOImpl                 = new PontoDAOImpl();
		UsuarioDAOImpl usuarioDAOImpl             = new UsuarioDAOImpl();
		TrajetoDAOImpl trajetoDAOImpl             = new TrajetoDAOImpl();
		PontoFavDAOImpl pontoFavDAOImpl           = new PontoFavDAOImpl();
		FormularioDAOImpl formularioDAOImpl       = new FormularioDAOImpl();
		PontoAvaliadoDAOImpl pontoAvaliadoDAOImpl = new PontoAvaliadoDAOImpl();
		
		UsuarioCadastrado usuario = new UsuarioCadastrado("Wesley", "123456789", "wesley@email.com");
		
		Ponto p1 = Ponto.informatLocal("Faculdade Senac, Blumenau", 0);
		Ponto p2 = Ponto.informatLocal("Indaial", 0);
		
		PontoFavorito pontoFav = new PontoFavorito(p1, "Senac", usuario);
		
		Formulario form = new Formulario(true, false, true, false, false, "Levei uma pázada", true, p2, usuario);
		
		PontoAvaliado pontoAv = new PontoAvaliado(p2, form);
		
		Trajeto trajeto = new Trajeto(p1, p2, MeioDeTransporte.DRIVING_CAR);
		
		usuario.addFavorito(pontoFav);
		
		usuario.avaliacao(true, true, true, true, true, false, "Deu muito errado, mas não tava bloqueado xD", p1, usuario);
		
		//System.out.println(pontoAv.getAvaliacoes().get(0));
		
		System.out.println(pontoFav.getUsuario());
		
		System.out.println(PontoFavorito.favoritarPontoENomear(p1, "Cursinho", usuario).getNomePonto());
		
		System.out.println(pontoAv.isBloqueio());
		
		Formulario form2 = new Formulario(true, true, true, true, true, "ta de boas xD", false, p2, usuario);
		
		pontoAv.addAvaliacao(form2);
		
		
		for(int i =0; i < trajeto.getPontos().size(); i++) {
			System.out.print(trajeto.getPontos().get(i).getLatitude());
			System.out.print(" ");
			System.out.println(trajeto.getPontos().get(i).getLongitude());
		}
		
		

	}
}
