package controlador.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import modelo.dao.Formulario.FormularioDAO;
import modelo.dao.Ponto.PontoDAO;
import modelo.dao.PontoAvaliado.PontoAvaliadoDAO;
import modelo.dao.Trajeto.TrajetoDAO;
import modelo.dao.Usuario.UsuarioDAO;
import modelo.entidade.mapa.Ponto;
import modelo.entidade.mapa.Trajeto;
import modelo.entidade.usuario.UsuarioCadastrado;
import modelo.enumeracao.mapa.MeioDeTransporte;
import modelo.excecao.mapa.NumeroMenorQueZeroException;
import modelo.excecao.mapa.StatusInvalidoException;
import modelo.excecao.usuario.EmailInvalidoException;
import modelo.excecao.usuario.SenhaPequenaException;
import modelo.excecao.usuario.StringVaziaException;

@WebServlet("/")
public class Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UsuarioDAO usuarioDAO;
	private PontoDAO pontoDAO;
	private TrajetoDAO trajetoDAO;
	private FormularioDAO formularioDAO;
	private PontoAvaliadoDAO pontoAvaliadoDAO;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();
		
		try {
			
			switch(action) {

				case "/inicio":
					mostrarTelaInicial(request, response);
					break;

				case "/novo-Usuario":
					mostrarFormularioDeCadastroDeUsuario(request, response);
					break;

				case "/inserir-Usuario":
					inserirUsuario(request, response);
					break;

				case "/atualizar-Usuario":
					atualizarUsuario(request, response);
					break;

				case "/editar-Usuario":
					mostrarFormularioEditarUsuario(request, response);
					break;

				case "/deletar-Usuario":
					deletarUsuario(request, response);
					break;
					
				case "/mapa":
					mostrarMapa(request, response);
					break;
//					
//				case "/procurar-ponto":
//					MostrarPontosCompativel(request, response);
//					break;
//
//				case "/selecionar-ponto":
//					selecionarPonto(request, response);
//					break;
					
				case "/criar-trajeto":
					inserirTrajeto(request, response);
					break;
					
				case "/mapa-trajeto":
					
					break;
					
				case "/inserir-Ponto":
					inserirPonto(request, response);
					break;

				case "/deletar-Ponto":
					deletarPonto(request, response);
					break;

				default:
					mostrarTela404(request, response);
					break;
			}
		}
		
		catch (Exception ex) {
			throw new ServletException(ex);
		}	
	}

	private void recuperarUsuario (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		long idUsuario = Long.parseLong(request.getParameter("idUsuario"));
		UsuarioCadastrado usuarioCadastrado = usuarioDAO.recuperarUsuario(new UsuarioCadastrado(idUsuario));
		request.setAttribute("usuario", usuarioCadastrado);
		RequestDispatcher dispatcher = request.getRequestDispatcher("cabecalho.jsp");
		dispatcher.forward(request, response);

	}

	private void mostrarTelaInicial (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	private void mostrarTela404 (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("erro404.jsp");
		dispatcher.forward(request, response);
	}
	
	private void mostrarFormularioDeCadastroDeUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("cadastro.jsp");
		dispatcher.forward(request, response);
	}
	
	private void mostrarMapa (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("mapa.jsp");
		dispatcher.forward(request, response);
	}

	private void mostrarFormularioEditarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		long idUsuario = Long.parseLong(request.getParameter("idUsuario"));
		UsuarioCadastrado usuarioCadastrado = usuarioDAO.recuperarUsuario(new UsuarioCadastrado(idUsuario));
		RequestDispatcher dispatcher = request.getRequestDispatcher("cadastro.jsp");
		request.setAttribute("usuarioCadastrado", usuarioCadastrado);
		dispatcher.forward(request, response);
	}

	private void inserirUsuario(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, StringVaziaException, EmailInvalidoException, SenhaPequenaException{
		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");
		String email = request.getParameter("email");
		usuarioDAO.inserirUsuario(new UsuarioCadastrado(nome, senha, email));
	}

	private void atualizarUsuario (HttpServletRequest request, HttpServletResponse response) throws StringVaziaException, EmailInvalidoException, SenhaPequenaException{
		long idUsuario = Long.parseLong(request.getParameter("idUsuario"));
		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");
		String email = request.getParameter("email");
		usuarioDAO.atualizarUsuario(new UsuarioCadastrado(idUsuario, nome, senha, email));
	}

	private void deletarUsuario (HttpServletRequest request, HttpServletResponse response){
		long idUsuario = Long.parseLong(request.getParameter("idUsuario"));
		UsuarioCadastrado usuarioCadastrado = usuarioDAO.recuperarUsuario(new UsuarioCadastrado(idUsuario));
		usuarioDAO.deletarUsuario(usuarioCadastrado);
	}

	private void inserirPonto (HttpServletRequest request, HttpServletResponse response) throws StatusInvalidoException{
		double latitude = Double.parseDouble(request.getParameter("latitude"));
		double longitude = Double.parseDouble(request.getParameter("longitude"));
		Ponto ponto = new Ponto(latitude, longitude);
		if (pontoDAO.recuperarPontoPorLatLong(ponto) == null) {
			pontoDAO.inserirPonto(new Ponto(latitude, longitude));
		}
	}

	private void deletarPonto (HttpServletRequest request, HttpServletResponse response) {
		long idPonto = Long.parseLong(request.getParameter("idPonto"));
		Ponto ponto = pontoDAO.recuperarPonto(new Ponto(idPonto));		
		pontoDAO.deletarPonto(ponto);
	}

	private void inserirTrajeto (HttpServletRequest request, HttpServletResponse response) throws StatusInvalidoException, NumeroMenorQueZeroException, JsonParseException, JsonMappingException, IOException{
		Ponto partida = (Ponto) request.getAttribute("inicio");
		Ponto chegada = (Ponto) request.getAttribute("chegada");
		MeioDeTransporte meioDeTransporte = (MeioDeTransporte) request.getAttribute("meioDeTransporte");
		Trajeto trajeto = new Trajeto();
		trajeto.setInicio(partida);
		trajeto.setChegada(chegada);
		trajeto.setTransporteUsado(meioDeTransporte);
		//Ponto avaliado!
		for (int i = 0; i < trajeto.getPontos().size(); i++){
				Ponto ponto = trajeto.getPontos().get(i);
				if (pontoDAO.recuperarPontoPorLatLong(ponto) == null){
					pontoDAO.inserirPonto(ponto);
				}
		}
		trajetoDAO.inserirTrajeto(trajeto);
	}

	private void atualizarTrajeto (HttpServletRequest request, HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException{
		long idTrajeto = Long.parseLong(request.getParameter("idTrajeto"));
		Ponto partida = (Ponto) request.getAttribute("inicio");
		Ponto chegada = (Ponto) request.getAttribute("chegada");
		MeioDeTransporte meioDeTransporte = (MeioDeTransporte) request.getAttribute("meioDeTransporte");
		trajetoDAO.atualizarTrajeto(new Trajeto(idTrajeto, partida, chegada, meioDeTransporte));
	}

	private void deletarTrajeto (HttpServletRequest request, HttpServletResponse response){
		long idTrajeto = Long.parseLong(request.getParameter("idTrajeto"));
		Trajeto trajeto = trajetoDAO.recuperarTrajeto(new Trajeto(idTrajeto));
		trajetoDAO.deletarTrajeto(trajeto);
	}

	private void inserirAvaliacao (HttpServletRequest request, HttpServletResponse response){
		
	}

	private void atualizarAvaliacao (HttpServletRequest request, HttpServletResponse response){

	}

	private void deletarAvaliacao (HttpServletRequest request, HttpServletResponse response){

	}

	private void inserirPontoAvaliado (HttpServletRequest request, HttpServletResponse response){

	}

	private void atualizarPontoAvaliado (HttpServletRequest request, HttpServletResponse response){

	}

	private void deletarPontoAvaliado (HttpServletRequest request, HttpServletResponse response){

	}

}