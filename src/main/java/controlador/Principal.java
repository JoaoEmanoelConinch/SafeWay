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
import modelo.excecao.mapa.NumeroMenorQueZeroException;
import modelo.excecao.mapa.StatusInvalidoException;
import modelo.excecao.usuario.EmailInvalidoException;
import modelo.excecao.usuario.SenhaPequenaException;
import modelo.excecao.usuario.StringVaziaException;

public class Principal {


	public static void main(String[] args) throws StringVaziaException, EmailInvalidoException, SenhaPequenaException, StatusInvalidoException, JsonParseException, JsonMappingException, IOException, NumeroMenorQueZeroException{
		
		PontoDAOImpl pontoDAOImpl                 = new PontoDAOImpl();
		UsuarioDAOImpl usuarioDAOImpl             = new UsuarioDAOImpl();
		TrajetoDAOImpl trajetoDAOImpl             = new TrajetoDAOImpl();
		PontoFavDAOImpl pontoFavDAOImpl           = new PontoFavDAOImpl();
		FormularioDAOImpl formularioDAOImpl       = new FormularioDAOImpl();
		PontoAvaliadoDAOImpl pontoAvaliadoDAOImpl = new PontoAvaliadoDAOImpl();
		
		UsuarioCadastrado usuario = new UsuarioCadastrado("Wesley", "123456789", "wesley@email.com");
		
		

	}
}
